package fr.medicapp.medicapp.ui.screen.prescription

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.model.Duration
import fr.medicapp.medicapp.model.OptionDialog
import fr.medicapp.medicapp.model.Prescription
import fr.medicapp.medicapp.ui.components.button.ReusableOutlinedDateRangePickerButton
import fr.medicapp.medicapp.ui.components.button.ReusableOutlinedSearchButton
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
    val context = LocalContext.current


    PrescriptionEditTreatmentContent(
        state = state.value,
        onClick = onClick,
        getMedicationList = { viewModel.getMedicationList(context) },
        updateMedication = { medication -> viewModel.updateMedication(medication, context) },
        updatePosology = { posology -> viewModel.updatePosology(posology) },
        updateFrequency = { frequency -> viewModel.updateFrequency(frequency) },
        updateDuration = { duration -> viewModel.updateDuration(duration) }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun PrescriptionEditTreatmentContent(
    state: Prescription,
    onClick: () -> Unit,
    getMedicationList: () -> List<OptionDialog>,
    updateMedication: (medication: OptionDialog) -> Unit,
    updatePosology: (posology: String) -> Unit,
    updateFrequency: (frequency: String) -> Unit,
    updateDuration: (duration: Duration) -> Unit
) {
    Edit(
        title = "Ajouter une prescription",
        bottomText = "Suivant",
        onClick = onClick
    ) {
        ReusableElevatedCard {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                ReusableOutlinedSearchButton(
                    options = getMedicationList(),
                    value = state.treatment.medication,
                    label = "Médicament",
                    warnings = state.treatment.medication == null,
                    onSelected = {
                        updateMedication(it)
                    }
                )

                Spacer(modifier = Modifier.padding(10.dp))

                ReusableOutlinedTextField(
                    value = state.treatment.posology,
                    onValueChange = {
                        updatePosology(it)
                    },
                    warnings = state.treatment.posology.isEmpty(),
                    label = "Posologie"
                )

                Spacer(modifier = Modifier.padding(10.dp))

                ReusableOutlinedTextField(
                    value = state.treatment.frequency,
                    onValueChange = {
                        updateFrequency(it)
                    },
                    warnings = state.treatment.frequency.isEmpty(),
                    label = "Fréquence"
                )

                Spacer(modifier = Modifier.padding(10.dp))

                ReusableOutlinedDateRangePickerButton(
                    value = state.treatment.duration,
                    label = "Durée",
                    warnings = state.treatment.duration == null,
                    onSelected = {
                        updateDuration(it)
                    }
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Light Theme")
@Composable
private fun PrescriptionEditTreatmentPreview() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        PrescriptionEditTreatmentContent(
            state = Prescription(),
            onClick = {},
            getMedicationList = { emptyList() },
            updateMedication = { _ -> },
            updatePosology = {},
            updateFrequency = {},
            updateDuration = {}
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Dark Theme")
@Composable
private fun PrescriptionEditTreatmentDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        PrescriptionEditTreatmentContent(
            state = Prescription(),
            onClick = {},
            getMedicationList = { emptyList() },
            updateMedication = { _ -> },
            updatePosology = {},
            updateFrequency = {},
            updateDuration = {}
        )
    }
}
