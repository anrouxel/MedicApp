package fr.medicapp.medicapp.viewModel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import fr.medicapp.medicapp.database.ObjectBox
import fr.medicapp.medicapp.database.entity.SideEffectEntity
import fr.medicapp.medicapp.database.entity.SideEffectEntity_
import fr.medicapp.medicapp.model.SideEffect
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
        val boxStore = ObjectBox.getInstance(context)
        val store = boxStore.boxFor(SideEffectEntity::class.java)
        val sideEffect =
            store.query().equal(SideEffectEntity_.id, id).build().findFirst()?.convert()
        _sharedState.value = sideEffect ?: SideEffect()
    }


    fun removeSideEffect(context: Context) {
        val boxStore = ObjectBox.getInstance(context)
        val store = boxStore.boxFor(SideEffectEntity::class.java)
        val sideEffect = _sharedState.value.convert(context)
        store.remove(sideEffect)
    }
}
