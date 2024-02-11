package fr.medicapp.medicapp.viewModel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import fr.medicapp.medicapp.database.ObjectBox
import fr.medicapp.medicapp.database.entity.PrescriptionEntity
import fr.medicapp.medicapp.model.Alarm
import fr.medicapp.medicapp.model.Duration
import fr.medicapp.medicapp.model.Notification
import fr.medicapp.medicapp.model.Prescription
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.DayOfWeek
import java.time.LocalDate
import javax.inject.Inject

/**
 * ViewModel partagé pour gérer l'état de l'ajout d'une prescription.
 * Il contient un état partagé qui est un flux de prescriptions.
 */
class SharedPrescriptionEditViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _sharedState: MutableStateFlow<Prescription> = MutableStateFlow(Prescription())
    val sharedState: StateFlow<Prescription> = _sharedState

    fun updateDate(newDate: LocalDate) {
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
        val notificationToUpdate = updatedNotifications[index]
        val updatedNotification = notificationToUpdate.copy(active = newActiveState)
        updatedNotifications[index] = updatedNotification
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
        val updatedNotification = notificationToUpdate.copy(days = notificationToUpdate.days)
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

    fun addAlarm(index: Int) {
        val updatedNotifications = _sharedState.value.notifications.toMutableList()
        val notificationToUpdate = updatedNotifications[index]
        val updatedAlarms = notificationToUpdate.alarms.toMutableList()
        updatedAlarms.add(Alarm())
        val updatedNotification = notificationToUpdate.copy(alarms = updatedAlarms)
        updatedNotifications[index] = updatedNotification
        val updatedPrescription = _sharedState.value.copy(notifications = updatedNotifications)
        _sharedState.value = updatedPrescription
    }

    fun updateAlarmTime(notificationIndex: Int, alarmIndex: Int, alarm: Alarm) {
        val updatedNotifications = _sharedState.value.notifications.toMutableList()
        val notificationToUpdate = updatedNotifications[notificationIndex]
        val updatedAlarms = notificationToUpdate.alarms.toMutableList()
        updatedAlarms[alarmIndex] = alarm
        val updatedNotification = notificationToUpdate.copy(alarms = updatedAlarms)
        updatedNotifications[notificationIndex] = updatedNotification
        val updatedPrescription = _sharedState.value.copy(notifications = updatedNotifications)
        _sharedState.value = updatedPrescription
    }

    fun removeAlarm(notificationIndex: Int, alarmIndex: Int) {
        val updatedNotifications = _sharedState.value.notifications.toMutableList()
        val notificationToUpdate = updatedNotifications[notificationIndex]
        val updatedAlarms = notificationToUpdate.alarms.toMutableList()
        updatedAlarms.removeAt(alarmIndex)
        val updatedNotification = notificationToUpdate.copy(alarms = updatedAlarms)
        updatedNotifications[notificationIndex] = updatedNotification
        val updatedPrescription = _sharedState.value.copy(notifications = updatedNotifications)
        _sharedState.value = updatedPrescription
    }

    fun save(context: Context) {
        val boxStore = ObjectBox.getInstance(context)
        val store = boxStore.boxFor(PrescriptionEntity::class.java)
        Log.d("SharedPrescriptionEditViewModel", "Saving prescription: ${sharedState.value}")
        Log.d("SharedPrescriptionEditViewModel", "Saving prescription: ${sharedState.value.date}")
        val prescription = sharedState.value.convert()
        Log.d("SharedPrescriptionEditViewModel", "Saving prescription: $prescription")
        store.put(prescription)
    }
}
