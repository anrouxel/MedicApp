package fr.medicapp.medicapp.ViewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedAddPrescriptionViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _sharedState = MutableStateFlow(Prescription())

    val sharedState = _sharedState.asStateFlow()

    fun setInstructions(instructions: OptionInstruction) {
        _sharedState.value.setInstructions(instructions)
    }

    fun getInstructions(): OptionInstruction? {
        return _sharedState.value.getInstructions()
    }

    fun setDoctor(doctor: OptionDoctor) {
        _sharedState.value.setDoctor(doctor)
    }

    fun getDoctor(): OptionDoctor? {
        return _sharedState.value.getDoctor()
    }
}
