package fr.medicapp.medicapp.viewModel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import fr.medicapp.medicapp.database.repositories.prescription.NotificationRepository
import fr.medicapp.medicapp.database.repositories.prescription.PrescriptionRepository
import fr.medicapp.medicapp.model.prescription.relationship.Prescription
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * ViewModel partagé pour gérer l'état de l'ajout d'une prescription.
 * Il contient un état partagé qui est un flux de prescriptions.
 */
class SharedPrescriptionDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _sharedState: MutableStateFlow<Prescription> = MutableStateFlow(Prescription())
    val sharedState: StateFlow<Prescription> = _sharedState

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadPrescription(context: Context, id: Long) {
        Thread {
            _sharedState.value = PrescriptionRepository(context).getById(id)
        }.start()
    }

    fun removePrescription(context: Context) {
        Thread {
            val prescription = _sharedState.value
            _sharedState.value = Prescription()
            PrescriptionRepository(context).delete(_sharedState.value)
        }.start()
    }

    fun updatedNotificationState(context: Context, index: Int, active: Boolean) {
        val updatedNotifications = _sharedState.value.notifications.toMutableList()
        val updateNotificationInformation =
            updatedNotifications[index].notificationInformation.copy(active = active)
        updatedNotifications[index] =
            updatedNotifications[index].copy(notificationInformation = updateNotificationInformation)
        val updatedPrescription = _sharedState.value.copy(notifications = updatedNotifications)
        _sharedState.value = updatedPrescription

        Thread {
            NotificationRepository(context).update(updatedNotifications[index])
        }.start()
    }

    fun removeNotification(context: Context, id: Long) {
        val updatedNotifications = _sharedState.value.notifications.toMutableList()
        val notification = updatedNotifications.find { it.notificationInformation.id == id }
        updatedNotifications.remove(notification)
        val updatedPrescription = _sharedState.value.copy(notifications = updatedNotifications)
        _sharedState.value = updatedPrescription
        Thread {
            NotificationRepository(context).delete(notification!!)
        }.start()
    }
}
