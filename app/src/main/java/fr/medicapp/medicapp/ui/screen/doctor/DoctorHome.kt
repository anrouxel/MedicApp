package fr.medicapp.medicapp.ui.screen.doctor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.medicapp.medicapp.model.prescription.Doctor
import fr.medicapp.medicapp.ui.components.button.ReusableElevatedCardButton
import fr.medicapp.medicapp.ui.components.card.CardContent
import fr.medicapp.medicapp.ui.components.textfield.ReusableOutlinedTextField
import fr.medicapp.medicapp.ui.theme.EURedColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import fr.medicapp.medicapp.viewModel.DoctorViewModel

@Composable
fun DoctorHome(
    viewModel: DoctorViewModel = viewModel(),
    onDoctorClick: (Long) -> Unit
) {
    val doctor = remember { mutableStateOf("") }

    Column {
        ReusableOutlinedTextField(
            value = doctor.value,
            onValueChange = {
                doctor.value = it
                if (doctor.value.length > 3) {
                    viewModel.searchDoctor(doctor.value)
                }
            },
            label = "Rechercher"
        )
        Spacer(modifier = Modifier.padding(10.dp))
        val doctors = viewModel.doctors.value ?: emptyList()
        if (doctors.isEmpty()) {
            NoDoctorFound()
        } else {
            DoctorList(
                doctors = doctors,
                onDoctorClick = onDoctorClick,
            )
        }
    }
}


@Composable
fun DoctorList(
    doctors: List<Doctor>,
    onDoctorClick: (Long) -> Unit
) {
    Column {
        doctors.forEachIndexed { index, doctor ->
            DoctorItem(
                doctor = doctor,
                onDoctorClick = onDoctorClick
            )
            if (index != doctors.size - 1) {
                Spacer(modifier = Modifier.padding(10.dp))
            }
        }
    }
}

@Composable
fun DoctorItem(
    doctor: Doctor,
    onDoctorClick: (Long) -> Unit
) {
    ReusableElevatedCardButton(
        onClick = { onDoctorClick(doctor.id) }
    ) {
        CardContent(
            title = "${doctor.civilCodeEx} ${doctor.firstName} ${doctor.lastName}",
            description = doctor.professionLabel
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
    val doctors = mutableListOf(
        Doctor(
            civilCodeEx = "DR",
            firstName = "Jean",
            lastName = "Mottu",
            professionLabel = "Médecin/Beta Testeur"
        ),
        Doctor(
            civilCodeEx = "DR",
            firstName = "Willy",
            lastName = "Wonka",
            professionLabel = "Fraude"
        )
    )
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EURedColorShema
    ) {
        DoctorHome(
            onDoctorClick = {}
        )
    }
}

@Preview
@Composable
fun DoctorHomeDarkPreview() {
    val doctors = mutableListOf<Doctor>()
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EURedColorShema
    ) {
        DoctorHome(
            onDoctorClick = {}
        )
    }
}