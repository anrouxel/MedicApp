package fr.medicapp.medicapp.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.medicapp.medicapp.entity.NotificationEntity
import fr.medicapp.medicapp.entity.PrescriptionEntity
import fr.medicapp.medicapp.model.Prescription
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * ViewModel partagé pour gérer l'état de l'ajout d'une prescription.
 * Il contient un état partagé qui est un flux de prescriptions.
 */
@HiltViewModel
class SharedPrescriptionEditViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val sharedState = MutableStateFlow(Prescription())
}
