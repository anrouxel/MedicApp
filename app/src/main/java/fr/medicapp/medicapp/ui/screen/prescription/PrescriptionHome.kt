package fr.medicapp.medicapp.ui.screen.prescription

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import fr.medicapp.medicapp.entity.medication.Medication
import fr.medicapp.medicapp.model.Treatment
import fr.medicapp.medicapp.ui.components.card.CardContent
import fr.medicapp.medicapp.ui.components.card.ReusableElevatedCard
import fr.medicapp.medicapp.ui.components.screen.Home
import fr.medicapp.medicapp.ui.theme.EUPurpleColorShema
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

@Composable
fun PrescriptionHome(
    treatments: List<Treatment>,
    onPrescriptionClick: () -> Unit = {},
    onAddPrescriptionClick: () -> Unit = {}
) {
    Home(
        title = "Prescription",
        floatingActionButtonOnClick = onAddPrescriptionClick,
        floatActionButtonIcon = Icons.Default.DocumentScanner
    ) {
        if (treatments.isEmpty()) {
            NoPrescriptionAvailable()
        } else {
            PrescriptionList(
                treatments = treatments,
                onPrescriptionClick = onPrescriptionClick
            )
        }
    }
}

@Composable
fun PrescriptionList(
    treatments: List<Treatment>,
    onPrescriptionClick: () -> Unit = {}
) {
    treatments.forEach { treatment ->
        PrescriptionItem(
            treatment = treatment,
            onPrescriptionClick = onPrescriptionClick
        )
    }
}

@Composable
fun PrescriptionItem(
    treatment: Treatment,
    onPrescriptionClick: () -> Unit
) {
    ReusableElevatedCard(
        onClick = onPrescriptionClick
    ) {
        CardContent(
            title = treatment.medication?.name ?: "",
            description = treatment.posology
        )
    }
}

@Composable
fun NoPrescriptionAvailable() {
    Text(
        text = "Vous n'avez pas d'ordonnances.\n" + "Pour en ajouter, cliquez sur le bouton en bas",
        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
        fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
        fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxSize().wrapContentHeight(align = Alignment.CenterVertically)
    )
}

@Preview(name = "Light Theme", showSystemUi = true)
@Composable
private fun PrescriptionHomePreview() {
    val treatment = Treatment(
        medication = Medication(name = "Doliprane"),
        posology = "1 comprimé par jour"
    )

    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        PrescriptionHome(
            treatments = listOf(treatment)
        )
    }
}

@Preview(name = "Dark Theme", showSystemUi = true)
@Composable
private fun PrescriptionHomeDarkPreview() {
    val treatment = Treatment(
        medication = Medication(name = "Doliprane"),
        posology = "1 comprimé par jour"
    )

    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        PrescriptionHome(
            treatments = listOf(treatment)
        )
    }
}

@Preview(name = "Light Theme - No Prescription", showSystemUi = true)
@Composable
private fun NoPrescriptionAvailablePreview() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        PrescriptionHome(
            treatments = emptyList()
        )
    }
}

@Preview(name = "Dark Theme - No Prescription", showSystemUi = true)
@Composable
private fun NoPrescriptionAvailableDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        PrescriptionHome(
            treatments = emptyList()
        )
    }
}
