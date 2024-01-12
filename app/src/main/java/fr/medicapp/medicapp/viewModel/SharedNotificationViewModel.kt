package fr.medicapp.medicapp.viewModel

import androidx.lifecycle.ViewModel
import fr.medicapp.medicapp.database.AppDatabase
import fr.medicapp.medicapp.model.Notification
import fr.medicapp.medicapp.model.Prescription
import fr.medicapp.medicapp.model.SideEffect
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedNotificationViewModel : ViewModel() {
    private val _sharedState = MutableStateFlow(Notification())

    val sharedState = _sharedState.asStateFlow()
}
