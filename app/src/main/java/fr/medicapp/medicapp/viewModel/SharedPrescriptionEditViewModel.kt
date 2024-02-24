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
class SharedPrescriptionEditViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _sharedState: MutableStateFlow<Prescription> = MutableStateFlow(Prescription())
    val sharedState: StateFlow<Prescription> = _sharedState

    /*fun updateDate(newDate: LocalDate) {
        val updatedPrescription = _sharedState.value.copy(date = newDate)
        _sharedState.value = updatedPrescription
    }

    fun updatePosology(newPosology: String) {
        val updatedTreatment = _sharedState.value.treatment.copy(posology = newPosology)
        val updatedPrescription = _sharedState.value.copy(treatment = updatedTreatment)
        _sharedState.value = updatedPrescription
    }

    fun updateFrequency(newFrequency: String) {
        val updatedTreatment = _sharedState.value.treatment.copy(frequency = newFrequency)
        val updatedPrescription = _sharedState.value.copy(treatment = updatedTreatment)
        _sharedState.value = updatedPrescription
    }

    fun updateDuration(newDuration: Duration) {
        val updatedTreatment = _sharedState.value.treatment.copy(duration = newDuration)
        val updatedPrescription = _sharedState.value.copy(treatment = updatedTreatment)
        _sharedState.value = updatedPrescription
    }

    fun addNotification() {
        val updatedNotifications = _sharedState.value.notifications.toMutableList()
        updatedNotifications.add(Notification())
        val updatedPrescription = _sharedState.value.copy(notifications = updatedNotifications)
        _sharedState.value = updatedPrescription
    }

    fun updateNotificationActiveState(index: Int, newActiveState: Boolean) {
        val updatedNotifications = _sharedState.value.notifications.toMutableList()
        updatedNotifications[index] = updatedNotifications[index].copy(active = newActiveState)
        val updatedPrescription = _sharedState.value.copy(notifications = updatedNotifications)
        _sharedState.value = updatedPrescription
    }

    fun updateNotificationDays(index: Int, dayOfWeek: DayOfWeek) {
        val updatedNotifications = _sharedState.value.notifications.toMutableList()
        val notificationToUpdate = updatedNotifications[index]
        if (!notificationToUpdate.days.contains(dayOfWeek)) {
            notificationToUpdate.days.add(dayOfWeek)
        } else {
            notificationToUpdate.days.remove(dayOfWeek)
        }
        updatedNotifications[index] = notificationToUpdate.copy(days = notificationToUpdate.days)
        val updatedPrescription = _sharedState.value.copy(notifications = updatedNotifications)
        _sharedState.value = updatedPrescription
    }

    fun removeNotification(index: Int) {
        val updatedNotifications = _sharedState.value.notifications.toMutableList()
        updatedNotifications.removeAt(index)
        val updatedPrescription = _sharedState.value.copy(notifications = updatedNotifications)
        _sharedState.value = updatedPrescription
    }

    fun addAlarm(index: Int) {
        val updatedNotifications = _sharedState.value.notifications.toMutableList()
        val notificationToUpdate = updatedNotifications[index]
        val updatedAlarms = notificationToUpdate.alarms.toMutableList()
        updatedAlarms.add(Alarm())
        updatedNotifications[index] = notificationToUpdate.copy(alarms = updatedAlarms)
        val updatedPrescription = _sharedState.value.copy(notifications = updatedNotifications)
        _sharedState.value = updatedPrescription
    }

    fun updateAlarmTime(notificationIndex: Int, alarmIndex: Int, alarm: Alarm) {
        val updatedNotifications = _sharedState.value.notifications.toMutableList()
        val notificationToUpdate = updatedNotifications[notificationIndex]
        val updatedAlarms = notificationToUpdate.alarms.toMutableList()
        updatedAlarms[alarmIndex] = alarm
        updatedNotifications[notificationIndex] = notificationToUpdate.copy(alarms = updatedAlarms)
        val updatedPrescription = _sharedState.value.copy(notifications = updatedNotifications)
        _sharedState.value = updatedPrescription
    }

    fun removeAlarm(notificationIndex: Int, alarmIndex: Int) {
        val updatedNotifications = _sharedState.value.notifications.toMutableList()
        val notificationToUpdate = updatedNotifications[notificationIndex]
        val updatedAlarms = notificationToUpdate.alarms.toMutableList()
        updatedAlarms.removeAt(alarmIndex)
        updatedNotifications[notificationIndex] = notificationToUpdate.copy(alarms = updatedAlarms)
        val updatedPrescription = _sharedState.value.copy(notifications = updatedNotifications)
        _sharedState.value = updatedPrescription
    }

    fun updateMedication(newMedication: OptionDialog, context: Context) {
        val boxStore = RoomDB.getInstance(context)
        val store = boxStore.boxFor(MedicationEntity::class.java)
        val medication =
            store.query().equal(MedicationEntity_.id, newMedication.id).build().findFirst()
                ?.convert()
        val updatedTreatment = _sharedState.value.treatment.copy(medication = medication)
        val updatedPrescription = _sharedState.value.copy(treatment = updatedTreatment)
        _sharedState.value = updatedPrescription
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun save(context: Context) {
        addToNotificationManager(context)

        val boxStore = RoomDB.getInstance(context)
        val store = boxStore.boxFor(PrescriptionEntity::class.java)
        val prescription = _sharedState.value.convert(context)
        store.put(prescription)
        _sharedState.value = Prescription()
    }

    fun getMedicationList(context: Context): List<OptionDialog> {
        val boxStore = RoomDB.getInstance(context)
        val store = boxStore.boxFor(MedicationEntity::class.java)
        return store.all.map { it.convert().toOptionDialog() }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addToNotificationManager(context: Context) {
        NotificationPrescriptionManager.add(context, _sharedState.value.getNextAlarms())
    }*/
}
