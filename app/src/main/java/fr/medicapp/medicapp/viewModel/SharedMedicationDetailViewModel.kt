package fr.medicapp.medicapp.viewModel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import fr.medicapp.medicapp.database.repositories.medication.MedicationRepository
import fr.medicapp.medicapp.model.medication.relationship.Medication
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

/**
 * ViewModel partagé pour gérer l'état de l'ajout d'une prescription.
 * Il contient un état partagé qui est un flux de prescriptions.
 */
class SharedMedicationDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    private val _sharedState: MutableStateFlow<Medication?> = MutableStateFlow(null)
    val sharedState: StateFlow<Medication?> = _sharedState

    suspend fun loadMedication(context: Context, id: Long) {
        withContext(dispatcher) {
            _sharedState.value = MedicationRepository(context).getById(id)
            android.util.Log.d("Medication", _sharedState.value.toString())
        }
    }

    fun getMedicationUrl(): Uri {
        return Uri.parse("https://data.ansm.sante.fr/specialite/${_sharedState.value!!.medicationInformation.cisCode}")
    }
}
