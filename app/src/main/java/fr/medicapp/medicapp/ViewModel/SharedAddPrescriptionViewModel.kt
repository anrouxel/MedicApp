package fr.medicapp.medicapp.ViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import fr.medicapp.medicapp.entity.Doctor
import fr.medicapp.medicapp.entity.Prescription
import fr.medicapp.medicapp.model.AddPrescription
import fr.medicapp.medicapp.model.OptionButtonContent
import fr.medicapp.medicapp.ui.navigation.AddPrescriptionsRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class SharedAddPrescriptionViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _sharedState = MutableStateFlow(AddPrescription())

    val sharedState = _sharedState.asStateFlow()

    val doctors: MutableState<MutableList<Doctor>> = mutableStateOf(mutableListOf<Doctor>(
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
    ))

    val source: HashMap<Boolean, OptionButtonContent> = hashMapOf(
        true to OptionButtonContent(
            title = "Oui",
            route = AddPrescriptionsRoute.ChooseDoctor.route
        ),
        false to OptionButtonContent(
            title = "Non",
            route = AddPrescriptionsRoute.ChooseDoctor.route
        ),
    )

    val instruction: List<OptionButtonContent> = listOf(
        OptionButtonContent(
            title = "Caméra",
            description = "Prenez une photo de votre ordonnance",
            route = AddPrescriptionsRoute.ChooseSource.route
        ),
        OptionButtonContent(
            title = "Galerie",
            description = "Choisissez une photo de votre ordonnance",
            route = AddPrescriptionsRoute.ChooseSource.route
        ),
        OptionButtonContent(
            title = "Manuellement",
            description = "Entrez manuellement les informations de votre ordonnance",
            route = AddPrescriptionsRoute.ChooseSource.route
        ),
    )

    fun updatePrescription(prescription: Prescription) {
        _sharedState.value.prescription = prescription
    }

    fun resetPrescription() {
        _sharedState.value.prescription = Prescription()
    }

    fun getPrescription(): Prescription {
        return _sharedState.value.prescription
    }

    fun isPrescribedByDoctor(): Boolean {
        return _sharedState.value.prescription.isPrescribedByDoctor()
    }

    fun getDoctor(): Doctor? {
        return _sharedState.value.prescription.doctor
    }

    fun setDoctor(doctor: Doctor) {
        _sharedState.value.prescription = _sharedState.value.prescription.copy(doctor = doctor)
    }

    fun addDoctor(doctor: Doctor) {
        doctors.value.add(doctor)
    }

    fun setInstruction(instruction: OptionButtonContent) {
        _sharedState.value = _sharedState.value.copy(instructions = instruction)
    }

    fun setSource(source: OptionButtonContent) {
        _sharedState.value = _sharedState.value.copy(source = source)
    }
}
