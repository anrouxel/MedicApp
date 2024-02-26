package fr.medicapp.medicapp.viewModel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import fr.medicapp.medicapp.database.repositories.prescription.SideEffectRepository
import fr.medicapp.medicapp.model.prescription.relationship.SideEffect
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

/**
 * ViewModel partagé pour gérer l'état de l'ajout d'une prescription.
 * Il contient un état partagé qui est un flux de prescriptions.
 */
class SharedSideEffectDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _sharedState: MutableStateFlow<SideEffect> = MutableStateFlow(SideEffect())
    val sharedState: StateFlow<SideEffect> = _sharedState

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun loadSideEffect(context: Context, id: Long) {
        withContext(Dispatchers.IO) {
            _sharedState.value = SideEffectRepository(context).getById(id)
        }
    }

    suspend fun removeSideEffect(context: Context) {
        withContext(Dispatchers.IO) {
            SideEffectRepository(context).delete(_sharedState.value)
        }
    }
}
