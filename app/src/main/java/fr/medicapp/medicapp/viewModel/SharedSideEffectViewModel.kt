package fr.medicapp.medicapp.viewModel

import androidx.lifecycle.ViewModel
import fr.medicapp.medicapp.entity.SideEffectEntity
import fr.medicapp.medicapp.model.SideEffect
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel partagé pour gérer l'état des effets secondaires.
 * Il contient un état partagé qui est un flux d'effets secondaires.
 */
class SharedSideEffectViewModel : ViewModel() {

    /**
     * État partagé pour les effets secondaires.
     * C'est un MutableStateFlow qui peut être observé pour des changements.
     */
    private val _sharedState = MutableStateFlow(SideEffectEntity())

    /**
     * Exposition de l'état partagé comme un flux d'état pour empêcher les modifications externes.
     */
    val sharedState = _sharedState.asStateFlow()
}
