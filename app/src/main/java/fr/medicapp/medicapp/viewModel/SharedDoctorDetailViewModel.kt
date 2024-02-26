package fr.medicapp.medicapp.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mapbox.mapboxsdk.geometry.LatLng
import fr.medicapp.medicapp.api.address.APIAddressClient
import fr.medicapp.medicapp.model.prescription.Doctor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.runBlocking

/**
 * ViewModel partagé pour gérer l'état de l'ajout d'une prescription.
 * Il contient un état partagé qui est un flux de prescriptions.
 */
class SharedDoctorDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _sharedState: MutableStateFlow<Doctor> = MutableStateFlow(
        Doctor(
            structureStreetNumber = "3",
            structureStreetTypeLabel = "Rue",
            structureStreetLabel = "Maréchal Joffre",
            structureCedexOffice = "44000"
        )
    )
    val sharedState: StateFlow<Doctor> = _sharedState

    fun fetch(): LatLng {
        return runBlocking {
            APIAddressClient().apiService.getPosition(
                q = "${_sharedState.value.structureStreetNumber}+" +
                    "${_sharedState.value.structureStreetTypeLabel}+" +
                    "${_sharedState.value.structureStreetLabel}+" +
                    _sharedState.value.structureCedexOffice
            ).body()?.features?.first()?.geometry?.coordinates?.let {
                return@runBlocking LatLng(it[1], it[0])
            } ?: LatLng(0.0, 0.0)
        }
    }
}
