package fr.medicapp.medicapp.viewModel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import fr.medicapp.medicapp.database.repositories.prescription.SideEffectRepository
import fr.medicapp.medicapp.model.prescription.relationship.SideEffect
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

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
    fun loadSideEffect(context: Context, id: Long) {
        _sharedState.value = SideEffectRepository(context).getById(id)
    }


    fun removeSideEffect(context: Context) {
        SideEffectRepository(context).remove(_sharedState.value)
    }
}
