package fr.medicapp.medicapp.viewModel

import androidx.lifecycle.ViewModel
import fr.medicapp.medicapp.entity.PrescriptionEntity
import fr.medicapp.medicapp.model.Prescription
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel partagé pour gérer l'état de l'ajout d'une prescription.
 * Il contient un état partagé qui est un flux de prescriptions.
 */
class SharedAddPrescriptionViewModel : ViewModel() {

    /**
     * État partagé pour l'ajout d'une prescription.
     * C'est un MutableStateFlow qui peut être observé pour des changements.
     */
    private val _sharedState = MutableStateFlow(PrescriptionEntity())

    /**
     * Exposition de l'état partagé comme un flux d'état pour empêcher les modifications externes.
     */
    val sharedState = _sharedState.asStateFlow()
}
