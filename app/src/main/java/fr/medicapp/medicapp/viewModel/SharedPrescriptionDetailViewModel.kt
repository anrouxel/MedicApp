package fr.medicapp.medicapp.viewModel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import fr.medicapp.medicapp.database.repositories.PrescriptionRepository
import fr.medicapp.medicapp.model.prescription.relationship.Prescription
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
        val prescription = PrescriptionRepository(context).getById(id)
        _sharedState.value = prescription
    }

    fun removePrescription(context: Context) {
        PrescriptionRepository(context).delete(_sharedState.value)
        _sharedState.value = Prescription()
    }

    fun updatedNotificationState(context: Context, id: Long, active: Boolean) {
        val prescription = _sharedState.value
        val notification = prescription.notifications.find { it.notificationInformation!!.id == id }
        notification!!.notificationInformation!!.active = active
        _sharedState.value = prescription
    }
}
