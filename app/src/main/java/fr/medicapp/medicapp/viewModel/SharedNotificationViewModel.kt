package fr.medicapp.medicapp.viewModel

import androidx.lifecycle.ViewModel
import fr.medicapp.medicapp.entity.NotificationEntity
import fr.medicapp.medicapp.model.Notification
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
    private val _sharedState = MutableStateFlow(NotificationEntity())

    /**
     * Exposition de l'état partagé comme un flux d'état pour empêcher les modifications externes.
     */
    val sharedState = _sharedState.asStateFlow()
}
