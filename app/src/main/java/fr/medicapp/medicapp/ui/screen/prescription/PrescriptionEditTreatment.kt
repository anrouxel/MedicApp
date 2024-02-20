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
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.ui.components.button.ReusableOutlinedDateRangePickerButton
import fr.medicapp.medicapp.ui.components.button.ReusableOutlinedSearchButton
import fr.medicapp.medicapp.ui.components.card.ReusableElevatedCard
import fr.medicapp.medicapp.ui.components.screen.Edit
import fr.medicapp.medicapp.ui.components.textfield.ReusableOutlinedTextField
import fr.medicapp.medicapp.viewModel.SharedPrescriptionEditViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PrescriptionEditTreatment(
    viewModel: SharedPrescriptionEditViewModel,
    onClick: () -> Unit
) {
    val state = viewModel.sharedState.collectAsState()
    val context = LocalContext.current

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
                    options = viewModel.getMedicationList(context),
                    value = state.value.treatment.medication,
                    label = "Médicament",
                    warnings = state.value.treatment.medication == null,
                    onSelected = {
                        viewModel.updateMedication(it, context)
                    }
                )

                Spacer(modifier = Modifier.padding(10.dp))

                ReusableOutlinedTextField(
                    value = state.value.treatment.posology,
                    onValueChange = {
                        viewModel.updatePosology(it)
                    },
                    warnings = state.value.treatment.posology.isEmpty(),
                    label = "Posologie"
                )

                Spacer(modifier = Modifier.padding(10.dp))

                ReusableOutlinedTextField(
                    value = state.value.treatment.frequency,
                    onValueChange = {
                        viewModel.updateFrequency(it)
                    },
                    warnings = state.value.treatment.frequency.isEmpty(),
                    label = "Fréquence"
                )

                Spacer(modifier = Modifier.padding(10.dp))

                ReusableOutlinedDateRangePickerButton(
                    value = state.value.treatment.duration,
                    label = "Durée",
                    warnings = state.value.treatment.duration == null,
                    onSelected = {
                        viewModel.updateDuration(it)
                    }
                )
            }
        }
    }
}
