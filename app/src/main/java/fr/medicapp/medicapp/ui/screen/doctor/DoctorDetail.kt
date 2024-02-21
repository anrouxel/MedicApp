package fr.medicapp.medicapp.ui.screen.doctor

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.mapbox.geojson.FeatureCollection
import com.mapbox.geojson.GeoJson
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.WellKnownTileServer
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import fr.medicapp.medicapp.model.Doctor
import fr.medicapp.medicapp.ui.components.card.ReusableElevatedCard
import fr.medicapp.medicapp.ui.components.screen.Detail
import fr.medicapp.medicapp.ui.components.text.ReusableTextMediumCard
import fr.medicapp.medicapp.ui.theme.EURedColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

@Composable
fun DoctorDetail(
    doctor: Doctor
) {
    Detail(
        title = "Informations sur le docteur"
    ) {
        Column {
            val structureAdresse = "${doctor.structureStreetNumber} " +
                    "${doctor.structureStreetTypeLabel} " +
                    "${doctor.structureStreetLabel} " +
                    doctor.structureCedexOffice

            ReusableElevatedCard {

                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    ReusableTextMediumCard(
                        value = "${doctor.civilCode} ${doctor.firstName} ${doctor.lastName} \n" +
                                doctor.skillLabel
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    ReusableTextMediumCard(
                        value = "Structure : ${doctor.siteCompanyName}"
                    )
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))

            ReusableElevatedCard {
                Column(
                    Modifier.padding(10.dp)
                ) {
                    ReusableTextMediumCard(
                        value = "Numéro de téléphone : ${doctor.structurePhoneNumber}"
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    ReusableTextMediumCard(
                        value = "Adresse e-mail : ${doctor.structureEmailAddress}"
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
                        .target(LatLng(52.39, 9.72))
                        .zoom(8.0)
                        .build()
                )
            }
        }
    }
}

@Composable
fun MapLibre(
    modifier: Modifier = Modifier,
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

fun fetch(url: String): GeoJson {
    val link = URL(url)
    val httpURLConnection = link.openConnection() as HttpURLConnection
    httpURLConnection.requestMethod = "GET"
    val inputStream = httpURLConnection.inputStream
    val reader = BufferedReader(InputStreamReader(inputStream))
    val response = reader.readText()
    return FeatureCollection.fromJson(response)
}


@Preview
@Composable
fun DoctorDetailPreview() {
    val doctor = Doctor(

    )
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EURedColorShema
    ) {
        DoctorDetail(doctor = doctor)
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
        DoctorDetail(doctor = doctor)
    }
}