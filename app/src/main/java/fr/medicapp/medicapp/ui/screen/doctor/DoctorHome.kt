package fr.medicapp.medicapp.ui.screen.doctor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import fr.medicapp.medicapp.model.Doctor
import fr.medicapp.medicapp.ui.components.button.ReusableElevatedCardButton
import fr.medicapp.medicapp.ui.components.card.CardContent
import fr.medicapp.medicapp.ui.components.screen.Home
import fr.medicapp.medicapp.ui.theme.EURedColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

@Composable
fun DoctorHome(
    doctors: List<Doctor>
) {
    Home(
        title = "Recherche de médecin",
        floatingActionButtonOnClick = { },
        floatActionButtonIcon = Icons.Filled.Search
    ) {
        if (doctors.isEmpty()) {
            NoDoctorFound()
        }
        else {
            DoctorList(
                doctors = doctors
            )
        }
    }
}

@Composable
fun DoctorList(
    doctors: List<Doctor>
) {
    Column {
        doctors.forEachIndexed { index, doctor ->
            DoctorItem(
                doctor = doctor
            )
        }
    }
}

@Composable
fun DoctorItem(
    doctor: Doctor
) {
    ReusableElevatedCardButton(
        onClick = {}
    ) {
        CardContent(
            title = "Dr. ${doctor.firstName} ${doctor.lastName}",
            description = doctor.activitySectorLabel
        )
    }
}

@Composable
private fun NoDoctorFound() {
    Text(
        text = "Aucun médecin trouvé. \n Veuillez chercher un médecin",
        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
        fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
        fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(align = Alignment.CenterVertically)
    )
}

@Preview
@Composable
fun DoctorHomePreview() {
    val doctors = listOf(
        Doctor(
            firstName = "Jean",
            lastName = "Dupont",
        )
    )
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EURedColorShema
    ) {
        DoctorHome(doctors)
    }
}

@Preview
@Composable
fun DoctorHomeDarkPreview() {
    val doctors = listOf<Doctor>()
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EURedColorShema
    ) {
        DoctorHome(doctors)
    }
}