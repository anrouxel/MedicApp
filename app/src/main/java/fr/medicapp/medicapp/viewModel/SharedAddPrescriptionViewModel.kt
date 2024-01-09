package fr.medicapp.medicapp.viewModel

import androidx.lifecycle.ViewModel
import fr.medicapp.medicapp.entity.Prescription
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedAddPrescriptionViewModel : ViewModel() {
    private val _sharedState = MutableStateFlow(Prescription())

    val sharedState = _sharedState.asStateFlow()

}
