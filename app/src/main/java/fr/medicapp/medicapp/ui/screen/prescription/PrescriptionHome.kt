package fr.medicapp.medicapp.ui.screen.prescription

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.model.prescription.relationship.Prescription
import fr.medicapp.medicapp.ui.components.button.ReusableElevatedCardButton
import fr.medicapp.medicapp.ui.components.card.CardContent
import fr.medicapp.medicapp.ui.components.screen.Home
import fr.medicapp.medicapp.ui.theme.EUPurpleColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

@Composable
fun PrescriptionHome(
    prescriptions: List<Prescription>,
    onPrescriptionClick: (Long) -> Unit = {},
    onAddPrescriptionClick: () -> Unit = {}
) {
    Home(
        title = "Prescription",
        floatingActionButtonOnClick = onAddPrescriptionClick,
        floatActionButtonIcon = Icons.Default.DocumentScanner
    ) {
        if (prescriptions.isEmpty()) {
            NoPrescriptionAvailable()
        } else {
            PrescriptionList(
                prescriptions = prescriptions,
                onPrescriptionClick = onPrescriptionClick
            )
        }
    }
}

@Composable
fun PrescriptionList(
    prescriptions: List<Prescription>,
    onPrescriptionClick: (Long) -> Unit = {}
) {
    Column {
        prescriptions.forEachIndexed { index, prescription ->
            PrescriptionItem(
                prescription = prescription,
                onPrescriptionClick = onPrescriptionClick
            )

            if (index != prescriptions.size - 1) {
                Spacer(modifier = Modifier.padding(10.dp))
            }
        }
    }
}

@Composable
fun PrescriptionItem(
    prescription: Prescription,
    onPrescriptionClick: (Long) -> Unit
) {
    ReusableElevatedCardButton(
        onClick = {} //onPrescriptionClick(prescription.id) }
    ) {
        CardContent(
            title = "", //prescription.treatment.medication?.name ?: "Médicament inconnu",
            description = "" //prescription.date?.toString() ?: "Date inconnue",
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
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(align = Alignment.CenterVertically)
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Light Theme", showSystemUi = true)
@Composable
private fun PrescriptionHomePreview() {
    /*val prescription = Prescription(
        doctor = Doctor(
            firstName = "Mottu"
        ),
        date = LocalDate.now()
    )*/

    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        PrescriptionHome(
            prescriptions = listOf()
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Dark Theme", showSystemUi = true)
@Composable
private fun PrescriptionHomeDarkPreview() {
    /*val prescription = Prescription(
        doctor = Doctor(
            firstName = "Mottu"
        ),
        date = LocalDate.now()
    )*/

    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        PrescriptionHome(
            prescriptions = listOf()
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
            prescriptions = emptyList()
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
            prescriptions = emptyList()
        )
    }
}
