package fr.medicapp.medicapp.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
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

    /*@RequiresApi(Build.VERSION_CODES.O)
    fun loadPrescription(context: Context, id: Long) {
        val boxStore = RoomDB.getInstance(context)
        val store = boxStore.boxFor(PrescriptionEntity::class.java)
        val prescription =
            store.query().equal(PrescriptionEntity_.id, id).build().findFirst()?.convert()
        _sharedState.value = prescription ?: Prescription()
    }

    fun removePrescription(context: Context) {
        val boxStore = RoomDB.getInstance(context)
        val sideEffectStore = boxStore.boxFor(SideEffectEntity::class.java)
        val prescriptionStore = boxStore.boxFor(PrescriptionEntity::class.java)
        val prescription = _sharedState.value.convert(context)

        prescription.sideEffects.forEach { sideEffect ->
            sideEffectStore.remove(sideEffect)
        }

        prescriptionStore.remove(prescription)

        _sharedState.value = Prescription()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateNotificationActiveState(index: Int, newActiveState: Boolean, context: Context) {
        val updatedNotifications = _sharedState.value.notifications.toMutableList()
        updatedNotifications[index] = updatedNotifications[index].copy(active = newActiveState)
        val updatedPrescription = _sharedState.value.copy(notifications = updatedNotifications)
        _sharedState.value = updatedPrescription

        if (newActiveState) {
            updateNotificationManager(context, index)
        } else {
            removeFromNotificationManager(context, index)
        }

        saveUpdate(context)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun removeNotification(index: Int, context: Context) {
        removeFromNotificationManager(context, index)

        val updatedNotifications = _sharedState.value.notifications.toMutableList()
        updatedNotifications.removeAt(index)
        val updatedPrescription = _sharedState.value.copy(notifications = updatedNotifications)
        _sharedState.value = updatedPrescription

        save(context)
    }

    fun save(context: Context) {
        val boxStore = RoomDB.getInstance(context)
        val store = boxStore.boxFor(PrescriptionEntity::class.java)
        val prescription = _sharedState.value.convert(context)
        val newKey = store.put(prescription)
        prescription.id = newKey
        _sharedState.value =
            store.query().equal(PrescriptionEntity_.id, prescription.id).build().findFirst()
                ?.convert() ?: Prescription()
    }

    fun saveUpdate(context: Context) {
        val boxStore = RoomDB.getInstance(context)
        val store = boxStore.boxFor(NotificationEntity::class.java)
        val notifications = _sharedState.value.notifications.map { it.convert(context) }
        store.put(notifications)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateNotificationManager(context: Context, index: Int) {
        val enabled = _sharedState.value.notifications[index].active
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun removeFromNotificationManager(context: Context, index: Int) {
        val id = _sharedState.value.notifications[index].id
        NotificationPrescriptionManager.remove(context, _sharedState.value.getNotificationAlarms(id))
    }*/
}
