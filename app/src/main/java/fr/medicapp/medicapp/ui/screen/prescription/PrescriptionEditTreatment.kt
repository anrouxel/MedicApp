package fr.medicapp.medicapp.ui.screen.prescription

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.medicapp.medicapp.model.Prescription
import fr.medicapp.medicapp.ui.components.card.ReusableElevatedCard
import fr.medicapp.medicapp.ui.components.screen.Edit
import fr.medicapp.medicapp.ui.components.textfield.ReusableOutlinedTextField
import fr.medicapp.medicapp.ui.theme.EUPurpleColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import fr.medicapp.medicapp.viewModel.SharedPrescriptionEditViewModel

@Composable
fun PrescriptionEditTreatment(
    viewModel: SharedPrescriptionEditViewModel,
    onClick: () -> Unit
) {
    Edit(
        title = "Ajouter une prescription",
        bottomText = "Suivant",
        onClick = onClick
    ) {
        /*ReusableElevatedCard {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                ReusableOutlinedTextField(
                    value = "",
                    onValueChange = { },
                    label = {
                        Text("Médicament")
                    }
                )

                Spacer(modifier = Modifier.padding(10.dp))

                ReusableOutlinedTextField(
                    value = prescription.treatment.posology,
                    onValueChange = {
                        prescription.treatment.posology = it
                    },
                    label = {
                        Text("Posologie")
                    }
                )

                Spacer(modifier = Modifier.padding(10.dp))

                ReusableOutlinedTextField(
                    value = prescription.treatment.frequency,
                    onValueChange = {
                        prescription.treatment.frequency = it
                    },
                    label = {
                        Text("Fréquence")
                    }
                )

                Spacer(modifier = Modifier.padding(10.dp))

                ReusableOutlinedTextField(
                    value = "",
                    onValueChange = { },
                    label = {
                        Text("Durée")
                    }
                )
            }
        }*/
    }
}

@Preview(name = "Light Theme")
@Composable
private fun PrescriptionEditTreatmentPreview() {
    val prescription = Prescription()

    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        PrescriptionEditTreatment(
            viewModel = viewModel(),
            onClick = {}
        )
    }
}

@Preview(name = "Dark Theme")
@Composable
private fun PrescriptionEditTreatmentDarkPreview() {
    val prescription = Prescription()

    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        PrescriptionEditTreatment(
            viewModel = viewModel(),
            onClick = {}
        )
    }
}
