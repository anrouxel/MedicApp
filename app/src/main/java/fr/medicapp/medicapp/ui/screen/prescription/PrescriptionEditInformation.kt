package fr.medicapp.medicapp.ui.screen.prescription

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import fr.medicapp.medicapp.ui.components.card.ReusableElevatedCard
import fr.medicapp.medicapp.ui.components.screen.Edit
import fr.medicapp.medicapp.viewModel.SharedPrescriptionEditViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PrescriptionEditInformation(
    viewModel: SharedPrescriptionEditViewModel,
    onClick: () -> Unit
) {
    val state = viewModel.sharedState.collectAsState()

    Edit(
        title = "Ajouter une prescription",
        bottomText = "Suivant",
        onClick = onClick,
        enabled = false //state.value.date != null
    ) {
        ReusableElevatedCard {
            /*Column(
                modifier = Modifier.padding(10.dp)
            ) {
                ReusableOutlinedTextFieldButton(
                    value = "",
                    label = "Docteur",
                    warnings = false,
                    onClick = {}
                )

                Spacer(modifier = Modifier.padding(10.dp))

                ReusableOutlinedDatePickerButton(
                    value = state.value.date,
                    label = "Date",
                    warnings = state.value.date == null,
                    onSelected = {
                        viewModel.updateDate(it)
                    }
                )
            }*/
        }
    }
}
