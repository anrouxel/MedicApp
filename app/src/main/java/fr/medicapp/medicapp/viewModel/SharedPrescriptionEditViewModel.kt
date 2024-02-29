package fr.medicapp.medicapp.viewModel

import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import fr.medicapp.medicapp.ai.PrescriptionAI
import fr.medicapp.medicapp.api.address.apiInteractions.DoctorsSearch
import fr.medicapp.medicapp.database.repositories.medication.MedicationRepository
import fr.medicapp.medicapp.database.repositories.prescription.DoctorRepository
import fr.medicapp.medicapp.database.repositories.prescription.PrescriptionRepository
import fr.medicapp.medicapp.model.OptionDialog
import fr.medicapp.medicapp.model.prescription.Alarm
import fr.medicapp.medicapp.model.prescription.Duration
import fr.medicapp.medicapp.model.prescription.relationship.Notification
import fr.medicapp.medicapp.model.prescription.relationship.Prescription
import fr.medicapp.medicapp.notification.NotificationPrescriptionManager
import kotlinx.coroutines.CoroutineDispatcher
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
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    private val _sharedState: MutableStateFlow<MutableList<Prescription>> = MutableStateFlow(
        mutableStateListOf(Prescription())
    )
    val sharedState: StateFlow<MutableList<Prescription>> = _sharedState

    private val _loadings: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loadings: StateFlow<Boolean> = _loadings

    fun load(uri: Uri, context: Context) {
        _loadings.value = true
        Log.d("SharedPrescriptionEditViewModel", "load: $uri")
        PrescriptionAI(context).analyse(uri) { prescriptions ->
            if (prescriptions.isNullOrEmpty()) return@analyse
            _sharedState.value = prescriptions.toMutableStateList()
            _loadings.value = false
        }
    }

    fun updatePosology(newPosology: String) {
        _sharedState.value[0] =
            _sharedState.value[0].copy(
                prescriptionInformation = _sharedState.value[0].prescriptionInformation.copy(
                    posology = newPosology
                )
            )
    }

    fun updateFrequency(newFrequency: String) {
        _sharedState.value[0] =
            _sharedState.value[0].copy(
                prescriptionInformation = _sharedState.value[0].prescriptionInformation.copy(
                    renew = newFrequency
                )
            )
    }

    fun updateDuration(newDuration: Duration) {
        _sharedState.value[0] = _sharedState.value[0].copy(duration = newDuration)
    }

    fun addNotification() {
        val updatedNotifications = _sharedState.value[0].notifications.toMutableList()
        updatedNotifications.add(Notification())
        _sharedState.value[0] = _sharedState.value[0].copy(notifications = updatedNotifications)
    }

    fun updateNotificationActiveState(index: Int, newActiveState: Boolean) {
        val updatedNotifications = _sharedState.value[0].notifications.toMutableList()
        updatedNotifications[index] =
            updatedNotifications[index].copy(
                notificationInformation = updatedNotifications[index].notificationInformation.copy(
                    active = newActiveState
                )
            )
        _sharedState.value[0] = _sharedState.value[0].copy(notifications = updatedNotifications)
    }

    fun updateNotificationDays(index: Int, dayOfWeek: DayOfWeek) {
        val updatedNotifications = _sharedState.value[0].notifications.toMutableList()
        val notificationInformationToUpdate = updatedNotifications[index].notificationInformation
        if (!notificationInformationToUpdate.days.contains(dayOfWeek)) {
            notificationInformationToUpdate.days.add(dayOfWeek)
        } else {
            notificationInformationToUpdate.days.remove(dayOfWeek)
        }
        updatedNotifications[index] =
            updatedNotifications[index].copy(
                notificationInformation = notificationInformationToUpdate.copy(
                    days = notificationInformationToUpdate.days
                )
            )
        _sharedState.value[0] = _sharedState.value[0].copy(notifications = updatedNotifications)
    }

    fun removeNotification(index: Int) {
        val updatedNotifications = _sharedState.value[0].notifications.toMutableList()
        updatedNotifications.removeAt(index)
        _sharedState.value[0] = _sharedState.value[0].copy(notifications = updatedNotifications)
    }

    fun addAlarm(index: Int) {
        val updatedNotifications = _sharedState.value[0].notifications.toMutableList()
        val notificationToUpdate = updatedNotifications[index]
        val updatedAlarms = notificationToUpdate.alarms.toMutableList()
        updatedAlarms.add(Alarm())
        updatedNotifications[index] = notificationToUpdate.copy(alarms = updatedAlarms)
        _sharedState.value[0] = _sharedState.value[0].copy(notifications = updatedNotifications)
    }

    fun updateAlarmTime(notificationIndex: Int, alarmIndex: Int, alarm: Alarm) {
        val updatedNotifications = _sharedState.value[0].notifications.toMutableList()
        val notificationToUpdate = updatedNotifications[notificationIndex]
        val updatedAlarms = notificationToUpdate.alarms.toMutableList()
        updatedAlarms[alarmIndex] = alarm
        updatedNotifications[notificationIndex] = notificationToUpdate.copy(alarms = updatedAlarms)
        _sharedState.value[0] = _sharedState.value[0].copy(notifications = updatedNotifications)
    }

    fun removeAlarm(notificationIndex: Int, alarmIndex: Int) {
        val updatedNotifications = _sharedState.value[0].notifications.toMutableList()
        val notificationToUpdate = updatedNotifications[notificationIndex]
        val updatedAlarms = notificationToUpdate.alarms.toMutableList()
        updatedAlarms.removeAt(alarmIndex)
        updatedNotifications[notificationIndex] = notificationToUpdate.copy(alarms = updatedAlarms)
        _sharedState.value[0] = sharedState.value[0].copy(notifications = updatedNotifications)
    }

    suspend fun updateMedication(newMedication: OptionDialog, context: Context) {
        withContext(dispatcher) {
            _sharedState.value[0] = _sharedState.value[0].copy(
                medication = MedicationRepository(context).getById(newMedication.id)
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun save(context: Context) {
        withContext(dispatcher) {
            _sharedState.value[0] = PrescriptionRepository(context).getById(
                PrescriptionRepository(context).insert(_sharedState.value[0])
            )
            addToNotificationManager(context)
            _sharedState.value.removeAt(0)
            context.getSharedPreferences("medicapp", Context.MODE_PRIVATE).edit()
                .putBoolean("isNewMedicationAdded", true).apply()
        }
    }

    suspend fun searchMedication(search: String, context: Context): List<OptionDialog> {
        return withContext(dispatcher) {
            MedicationRepository(context).search(search)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addToNotificationManager(context: Context) {
        NotificationPrescriptionManager.add(context, _sharedState.value[0].getNextAlarms())
    }

    suspend fun searchDoctor(query: String): List<OptionDialog> {
        return withContext(dispatcher) {
            val doctors = mutableListOf<OptionDialog>()
            DoctorsSearch().searchLittleDoctor(query) {
                doctors.addAll(it.map { it.toOptionDialog() })
            }
            doctors
        }
    }

    suspend fun updateDoctor(doctorOption: OptionDialog, context: Context) {
        withContext(dispatcher) {
            val doctor = DoctorRepository(context).getByNationalId(doctorOption.id)
            if (doctor != null) {
                _sharedState.value[0] = _sharedState.value[0].copy(doctor = doctor)
            } else {
                DoctorsSearch().searchDoctor(doctorOption.id) {
                    val id = DoctorRepository(context).insert(it.first())
                    val newDoctor = DoctorRepository(context).getById(id)
                    _sharedState.value[0] = _sharedState.value[0].copy(doctor = newDoctor)
                }
            }
        }
    }
}
