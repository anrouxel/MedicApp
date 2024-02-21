package fr.medicapp.medicapp.viewModel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mapbox.mapboxsdk.geometry.LatLng
import fr.medicapp.medicapp.database.ObjectBox
import fr.medicapp.medicapp.database.entity.SideEffectEntity
import fr.medicapp.medicapp.database.entity.SideEffectEntity_
import fr.medicapp.medicapp.model.Doctor
import fr.medicapp.medicapp.model.SideEffect
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import mozilla.components.concept.fetch.Request
import mozilla.components.concept.fetch.isSuccess
import mozilla.components.lib.fetch.okhttp.OkHttpClient

/**
 * ViewModel partagé pour gérer l'état de l'ajout d'une prescription.
 * Il contient un état partagé qui est un flux de prescriptions.
 */
class SharedDoctorDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _sharedState: MutableStateFlow<Doctor> = MutableStateFlow(Doctor())
    val sharedState: StateFlow<Doctor> = _sharedState

    fun fetch(): LatLng {
        val request = Request(
            url = "https://api-adresse.data.gouv.fr/search/?q=${sharedState.value.structureStreetNumber} ${sharedState.value.structureStreetTypeLabel} ${sharedState.value.structureStreetLabel} ${sharedState.value.structureCedexOffice}",
        )

        val client = OkHttpClient()
        val response = client.fetch(request)

        if (response.isSuccess) {
            return LatLng(0.0, 0.0)
        }
        return LatLng(0.0, 0.0)
    }
}
