package fr.medicapp.medicapp.viewModel

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import fr.medicapp.medicapp.database.repositories.medication.MedicationRepository
import fr.medicapp.medicapp.database.repositories.prescription.PrescriptionRepository
import fr.medicapp.medicapp.model.OptionDialog
import fr.medicapp.medicapp.model.prescription.Alarm
import fr.medicapp.medicapp.model.prescription.Duration
import fr.medicapp.medicapp.model.prescription.relationship.Notification
import fr.medicapp.medicapp.model.prescription.relationship.Prescription
import fr.medicapp.medicapp.notification.NotificationPrescriptionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import java.time.DayOfWeek

/**
 * ViewModel partagé pour gérer l'état de l'ajout d'une prescription.
 * Il contient un état partagé qui est un flux de prescriptions.
 */
class SharedPrescriptionEditViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _sharedState: MutableStateFlow<Prescription> = MutableStateFlow(Prescription())
    val sharedState: StateFlow<Prescription> = _sharedState

    fun updatePosology(newPosology: String) {
        val updatedPrescriptionInformation =
            _sharedState.value.prescriptionInformation.copy(posology = newPosology)
        val updatedPrescription =
            _sharedState.value.copy(prescriptionInformation = updatedPrescriptionInformation)
        _sharedState.value = updatedPrescription
    }

    fun updateFrequency(newFrequency: String) {
        val updatedPrescriptionInformation =
            _sharedState.value.prescriptionInformation.copy(frequency = newFrequency)
        val updatedPrescription =
            _sharedState.value.copy(prescriptionInformation = updatedPrescriptionInformation)
        _sharedState.value = updatedPrescription
    }

    fun updateDuration(newDuration: Duration) {
        val updatedPrescription = _sharedState.value.copy(duration = newDuration)
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
        val updatedNotificationInformation =
            updatedNotifications[index].notificationInformation.copy(active = newActiveState)
        updatedNotifications[index] =
            updatedNotifications[index].copy(notificationInformation = updatedNotificationInformation)
        val updatedPrescription = _sharedState.value.copy(notifications = updatedNotifications)
        _sharedState.value = updatedPrescription
    }

    fun updateNotificationDays(index: Int, dayOfWeek: DayOfWeek) {
        val updatedNotifications = _sharedState.value.notifications.toMutableList()
        val notificationInformationToUpdate = updatedNotifications[index].notificationInformation
        if (!notificationInformationToUpdate.days.contains(dayOfWeek)) {
            notificationInformationToUpdate.days.add(dayOfWeek)
        } else {
            notificationInformationToUpdate.days.remove(dayOfWeek)
        }
        val updatedNotificationInformation =
            notificationInformationToUpdate.copy(days = notificationInformationToUpdate.days)
        updatedNotifications[index] =
            updatedNotifications[index].copy(notificationInformation = updatedNotificationInformation)
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
        Thread {
            val medication = MedicationRepository(context).getById(newMedication.id)
            val updatedPrescription = _sharedState.value.copy(medication = medication)
            _sharedState.value = updatedPrescription
        }.start()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun save(context: Context) {
        Log.d("Prescription", _sharedState.value.toString())
        addToNotificationManager(context)

        Thread {
            val id = PrescriptionRepository(context).insert(_sharedState.value)
            _sharedState.value = PrescriptionRepository(context).getById(id)
        }.start()
    }

    suspend fun searchMedication(search: String, context: Context): List<OptionDialog> {
        return withContext(Dispatchers.IO) {
            MedicationRepository(context).search(search)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addToNotificationManager(context: Context) {
        NotificationPrescriptionManager.add(context, _sharedState.value.getNextAlarms())
    }
}
