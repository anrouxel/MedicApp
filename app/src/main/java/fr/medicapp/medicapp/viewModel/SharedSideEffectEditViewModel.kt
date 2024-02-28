package fr.medicapp.medicapp.viewModel

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import fr.medicapp.medicapp.database.repositories.prescription.PrescriptionRepository
import fr.medicapp.medicapp.database.repositories.prescription.SideEffectRepository
import fr.medicapp.medicapp.model.OptionDialog
import fr.medicapp.medicapp.model.prescription.relationship.SideEffect
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import java.time.LocalDate

/**
 * ViewModel partagé pour gérer l'état de l'ajout d'une prescription.
 * Il contient un état partagé qui est un flux de prescriptions.
 */
class SharedSideEffectEditViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    private val _sharedState: MutableStateFlow<SideEffect> = MutableStateFlow(SideEffect())
    val sharedState: StateFlow<SideEffect> = _sharedState

    fun updateDate(date: LocalDate) {
        val updatedSideEffectInformation =
            _sharedState.value.sideEffectInformation.copy(date = date)
        val updatedSideEffect =
            _sharedState.value.copy(sideEffectInformation = updatedSideEffectInformation)
        _sharedState.value = updatedSideEffect
    }

    fun updateDescription(description: String) {
        val updatedSideEffectInformation =
            _sharedState.value.sideEffectInformation.copy(description = description)
        val updatedSideEffect =
            _sharedState.value.copy(sideEffectInformation = updatedSideEffectInformation)
        _sharedState.value = updatedSideEffect
    }

    suspend fun searchPrescription(search: String, context: Context): List<OptionDialog> {
        return withContext(dispatcher) {
            PrescriptionRepository(context).search(search)
        }
    }

    suspend fun updatePrescription(prescription: OptionDialog, context: Context) {
        withContext(dispatcher) {
            val prescription = PrescriptionRepository(context).getById(prescription.id)
            val updatedSideEffect = _sharedState.value.copy(prescription = prescription)
            _sharedState.value = updatedSideEffect
        }
    }

    suspend fun save(context: Context) {
        withContext(dispatcher) {
            val id = SideEffectRepository(context).insert(_sharedState.value)
            _sharedState.value = SideEffectRepository(context).getById(id)
        }
    }
}
