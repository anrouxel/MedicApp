package fr.medicapp.medicapp.viewModel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import fr.medicapp.medicapp.database.ObjectBox
import fr.medicapp.medicapp.database.entity.NotificationEntity
import fr.medicapp.medicapp.database.entity.PrescriptionEntity
import fr.medicapp.medicapp.database.entity.PrescriptionEntity_
import fr.medicapp.medicapp.model.Prescription
import fr.medicapp.medicapp.notification.NotificationPrescriptionManager
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
        val boxStore = ObjectBox.getInstance(context)
        val store = boxStore.boxFor(PrescriptionEntity::class.java)
        val prescription =
            store.query().equal(PrescriptionEntity_.id, id).build().findFirst()?.convert()
        _sharedState.value = prescription ?: Prescription()
    }

    fun updateNotificationActiveState(index: Int, newActiveState: Boolean) {
        val updatedNotifications = _sharedState.value.notifications.toMutableList()
        val notificationToUpdate = updatedNotifications[index]

        val updatedNotification = notificationToUpdate.copy(active = newActiveState)

        updatedNotifications[index] = updatedNotification
        val updatedPrescription = _sharedState.value.copy(notifications = updatedNotifications)
        _sharedState.value = updatedPrescription
    }

    fun removeNotification(index: Int) {
        val updatedNotifications = _sharedState.value.notifications.toMutableList()
        updatedNotifications.removeAt(index)
        val updatedPrescription = _sharedState.value.copy(notifications = updatedNotifications)
        _sharedState.value = updatedPrescription
    }

    fun save(context: Context) {
        val boxStore = ObjectBox.getInstance(context)
        val store = boxStore.boxFor(PrescriptionEntity::class.java)
        val prescription = _sharedState.value.convert(context)
        val newKey = store.put(prescription)
        prescription.id = newKey
        _sharedState.value =
            store.query().equal(PrescriptionEntity_.id, prescription.id).build().findFirst()
                ?.convert() ?: Prescription()
    }

    fun saveUpdate(context: Context) {
        val boxStore = ObjectBox.getInstance(context)
        val store = boxStore.boxFor(NotificationEntity::class.java)
        val notifications = _sharedState.value.notifications.map { it.convert(context) }
        store.put(notifications)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateNotificationManager(context: Context, index: Int) {
        val notification = _sharedState.value.notifications[index]
        NotificationPrescriptionManager.update(context, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun removeFromNotificationManager(context: Context, index: Int) {
        val notification = _sharedState.value.notifications[index]
        NotificationPrescriptionManager.remove(context, notification)
    }
}
