package fr.medicapp.medicapp.ui.screen.prescription

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.medicapp.medicapp.model.Prescription
import fr.medicapp.medicapp.ui.components.button.ReusableOutlinedDateRangePickerButton
import fr.medicapp.medicapp.ui.components.button.ReusableOutlinedTextFieldButton
import fr.medicapp.medicapp.ui.components.card.ReusableElevatedCard
import fr.medicapp.medicapp.ui.components.screen.Edit
import fr.medicapp.medicapp.ui.components.textfield.ReusableOutlinedTextField
import fr.medicapp.medicapp.ui.theme.EUPurpleColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import fr.medicapp.medicapp.viewModel.SharedPrescriptionEditViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PrescriptionEditTreatment(
    viewModel: SharedPrescriptionEditViewModel,
    onClick: () -> Unit
) {
    val state = viewModel.sharedState.collectAsState()

    Edit(
        title = "Ajouter une prescription",
        bottomText = "Suivant",
        onClick = onClick
    ) {
        ReusableElevatedCard {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                ReusableOutlinedTextFieldButton(
                    value = "",
                    label = "Médicament",
                    onClick = {}
                )

                Spacer(modifier = Modifier.padding(10.dp))

                ReusableOutlinedTextField(
                    value = state.value.treatment.posology,
                    onValueChange = {
                        viewModel.updatePosology(it)
                    },
                    label = "Posologie"
                )

                Spacer(modifier = Modifier.padding(10.dp))

                ReusableOutlinedTextField(
                    value = state.value.treatment.frequency,
                    onValueChange = {
                        viewModel.updateFrequency(it)
                    },
                    label = "Fréquence"
                )

                Spacer(modifier = Modifier.padding(10.dp))

                ReusableOutlinedDateRangePickerButton(
                    value = state.value.treatment.duration,
                    label = "Durée",
                    onSelected = {
                        viewModel.updateDuration(it)
                    }
                )
            }
        }
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
