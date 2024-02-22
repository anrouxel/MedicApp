package fr.medicapp.medicapp.ui.screen.doctor

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.WellKnownTileServer
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.maps.MapView
import fr.medicapp.medicapp.model.Doctor
import fr.medicapp.medicapp.ui.components.card.ReusableElevatedCard
import fr.medicapp.medicapp.ui.components.screen.Detail
import fr.medicapp.medicapp.ui.components.text.ReusableTextMediumCard
import fr.medicapp.medicapp.ui.theme.EURedColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import fr.medicapp.medicapp.viewModel.SharedDoctorDetailViewModel

@Composable
fun DoctorDetail(
    viewModel: SharedDoctorDetailViewModel
) {
    val state = viewModel.sharedState.collectAsState()

    Detail(
        title = "Informations sur le docteur"
    ) {
        Column {
            val structureAdresse = "${state.value.structureStreetNumber} " +
                    "${state.value.structureStreetTypeLabel} " +
                    "${state.value.structureStreetLabel} " +
                    state.value.structureCedexOffice

            ReusableElevatedCard {

                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    ReusableTextMediumCard(
                        value = "${state.value.civilCode} ${state.value.firstName} ${state.value.lastName} \n" +
                                state.value.skillLabel
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    ReusableTextMediumCard(
                        value = "Structure : ${state.value.siteCompanyName}"
                    )
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))

            ReusableElevatedCard {
                Column(
                    Modifier.padding(10.dp)
                ) {
                    ReusableTextMediumCard(
                        value = "Numéro de téléphone : ${state.value.structurePhoneNumber}"
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    ReusableTextMediumCard(
                        value = "Adresse e-mail : ${state.value.structureEmailAddress}"
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    ReusableTextMediumCard(
                        value = "Adresse : $structureAdresse"
                    )
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Box {
                MapLibre(
                    style = "https://rex.dri.utc.fr/map-server/styles/osm-bright/style.json",
                    cameraPosition = CameraPosition.Builder()
                        .target(viewModel.fetch())
                        .zoom(10.0)
                        .build()
                )
            }
        }
    }
}

@Composable
fun MapLibre(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(300.dp),
    style: String,
    cameraPosition: CameraPosition? = null
) {
    AndroidView(
        modifier = modifier,
        factory = {
            Mapbox.getInstance(it, "", WellKnownTileServer.MapLibre)
            MapView(it).apply {
                this.getMapAsync { map ->
                    cameraPosition?.let {
                        map.cameraPosition = it
                    }
                    map.setStyle(style)

                }
            }
        }
    )
}


@Preview
@Composable
fun DoctorDetailPreview() {
    val doctor = Doctor()
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EURedColorShema
    ) {
        DoctorDetail(
            viewModel = viewModel()
        )
    }
}

@Preview
@Composable
fun DoctorDetailDarkPreview() {
    val doctor = Doctor()
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EURedColorShema
    ) {
        DoctorDetail(
            viewModel = viewModel()
        )
    }
}