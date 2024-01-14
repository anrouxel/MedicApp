package fr.medicapp.medicapp.viewModel

import androidx.lifecycle.ViewModel
import fr.medicapp.medicapp.database.AppDatabase
import fr.medicapp.medicapp.model.Notification
import fr.medicapp.medicapp.model.Prescription
import fr.medicapp.medicapp.model.SideEffect
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel partagé pour gérer l'état des notifications.
 * Il contient un état partagé qui est un flux de notifications.
 */
class SharedNotificationViewModel : ViewModel() {

    /**
     * État partagé pour les notifications.
     * C'est un MutableStateFlow qui peut être observé pour des changements.
     */
    private val _sharedState = MutableStateFlow(Notification())

    /**
     * Exposition de l'état partagé comme un flux d'état pour empêcher les modifications externes.
     */
    val sharedState = _sharedState.asStateFlow()
}
