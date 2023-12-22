package fr.medicapp.medicapp.ViewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import fr.medicapp.medicapp.entity.Doctor
import fr.medicapp.medicapp.entity.Prescription
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class SharedAddPrescriptionViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _sharedState = MutableStateFlow(Prescription())

    val sharedState = _sharedState.asStateFlow()

    val doctors = listOf(
        Doctor(
            id = UUID.randomUUID(),
            firstName = "Jean",
            lastName = "Dupont",
        ),
        Doctor(
            id = UUID.randomUUID(),
            firstName = "Jeanne",
            lastName = "Dupont",
        ),
        Doctor(
            id = UUID.randomUUID(),
            firstName = "Jean",
            lastName = "Dupond",
        ),
    )

    fun updatePrescription(prescription: Prescription) {
        _sharedState.value = prescription
    }

    fun resetPrescription() {
        _sharedState.value = Prescription()
    }

    fun getPrescription(): Prescription {
        return _sharedState.value
    }

    fun isPrescribedByDoctor(): Boolean {
        return _sharedState.value.isPrescribedByDoctor()
    }

    fun getDoctor(): Doctor? {
        return _sharedState.value.doctor
    }

    fun setDoctor(doctor: Doctor) {
        _sharedState.value = _sharedState.value.copy(doctor = doctor)
    }
}
