package fr.medicapp.medicapp.viewModel

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import fr.medicapp.medicapp.model.OptionDialog
import fr.medicapp.medicapp.model.prescription.SideEffectInformation
import fr.medicapp.medicapp.model.prescription.relationship.SideEffect
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate

/**
 * ViewModel partagé pour gérer l'état de l'ajout d'une prescription.
 * Il contient un état partagé qui est un flux de prescriptions.
 */
class SharedSideEffectEditViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _sharedState: MutableStateFlow<SideEffect> = MutableStateFlow(SideEffect())
    val sharedState: StateFlow<SideEffect> = _sharedState

    fun updateDate(date: LocalDate) {
        val updatedSideEffectInformation = _sharedState.value.sideEffectInformation.copy(date = date)
        val updatedSideEffect = _sharedState.value.copy(sideEffectInformation = updatedSideEffectInformation)
        _sharedState.value = updatedSideEffect
    }

    fun updateDescription(description: String) {
        val updatedSideEffectInformation = _sharedState.value.sideEffectInformation.copy(description = description)
        val updatedSideEffect = _sharedState.value.copy(sideEffectInformation = updatedSideEffectInformation)
        _sharedState.value = updatedSideEffect
    }

    fun getPrescriptionList(context: Context): List<OptionDialog> {
        return emptyList()
    }

    fun updatePrescription(prescription: OptionDialog, context: Context) {
    }
}
