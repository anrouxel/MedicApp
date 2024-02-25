package fr.medicapp.medicapp.viewModel

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import fr.medicapp.medicapp.database.repositories.prescription.PrescriptionRepository
import fr.medicapp.medicapp.database.repositories.prescription.SideEffectRepository
import fr.medicapp.medicapp.model.OptionDialog
import fr.medicapp.medicapp.model.prescription.relationship.SideEffect
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
        return withContext(Dispatchers.IO) {
            PrescriptionRepository(context).search(search)
        }
    }

    fun updatePrescription(prescription: OptionDialog, context: Context) {
        Thread {
            val prescription = PrescriptionRepository(context).getById(prescription.id)
            val updatedSideEffect = _sharedState.value.copy(prescription = prescription)
            _sharedState.value = updatedSideEffect
        }.start()
    }

    fun save(context: Context) {
        Thread {
            val id = SideEffectRepository(context).insert(_sharedState.value)
            _sharedState.value = SideEffectRepository(context).getById(id)
        }.start()
    }
}
