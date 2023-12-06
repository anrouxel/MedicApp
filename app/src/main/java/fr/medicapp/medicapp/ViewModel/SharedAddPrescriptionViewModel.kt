package fr.medicapp.medicapp.ViewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import fr.medicapp.medicapp.model.OptionInstruction
import fr.medicapp.medicapp.model.Prescription
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
}
