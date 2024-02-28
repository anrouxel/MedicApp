package fr.medicapp.medicapp.viewModel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import fr.medicapp.medicapp.database.repositories.prescription.NotificationRepository
import fr.medicapp.medicapp.database.repositories.prescription.PrescriptionRepository
import fr.medicapp.medicapp.model.prescription.relationship.Prescription
import fr.medicapp.medicapp.notification.NotificationPrescriptionManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

/**
 * ViewModel partagé pour gérer l'état de l'ajout d'une prescription.
 * Il contient un état partagé qui est un flux de prescriptions.
 */
class SharedPrescriptionDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _sharedState: MutableStateFlow<Prescription> = MutableStateFlow(Prescription())
    val sharedState: StateFlow<Prescription> = _sharedState

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun loadPrescription(context: Context, id: Long) {
        withContext(dispatcher) {
            _sharedState.value = PrescriptionRepository(context).getById(id)
        }
    }

    suspend fun removePrescription(context: Context) {
        withContext(dispatcher) {
            val prescription = _sharedState.value
            _sharedState.value = Prescription()
            PrescriptionRepository(context).delete(prescription)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun updatedNotificationState(context: Context, index: Int, active: Boolean) {
        withContext(dispatcher) {
            val updatedNotifications = _sharedState.value.notifications.toMutableList()
            val updateNotificationInformation =
                updatedNotifications[index].notificationInformation.copy(active = active)
            updatedNotifications[index] =
                updatedNotifications[index].copy(notificationInformation = updateNotificationInformation)
            val updatedPrescription = _sharedState.value.copy(notifications = updatedNotifications)
            _sharedState.value = updatedPrescription

            if (!active) {
                NotificationPrescriptionManager.remove(
                    context,
                    _sharedState.value.getNotificationAlarms(updateNotificationInformation.id)
                )
            } else {
                NotificationPrescriptionManager.add(
                    context,
                    _sharedState.value.getNotificationAlarms(updateNotificationInformation.id)
                )
            }
            NotificationRepository(context).update(updatedNotifications[index])
        }
    }

    suspend fun removeNotification(context: Context, id: Long) {
        withContext(dispatcher) {
            val updatedNotifications = _sharedState.value.notifications.toMutableList()
            val notification = updatedNotifications.find { it.notificationInformation.id == id }
            updatedNotifications.remove(notification)
            val updatedPrescription = _sharedState.value.copy(notifications = updatedNotifications)
            _sharedState.value = updatedPrescription
            NotificationRepository(context).delete(notification!!)
        }
    }
}
