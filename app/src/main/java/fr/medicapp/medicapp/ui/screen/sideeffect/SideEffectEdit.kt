package fr.medicapp.medicapp.ui.screen.sideeffect

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import fr.medicapp.medicapp.ui.components.screen.Edit
import fr.medicapp.medicapp.viewModel.SharedSideEffectEditViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SideEffectEdit(
    viewModel: SharedSideEffectEditViewModel,
    onClick: () -> Unit
) {
    val state = viewModel.sharedState.collectAsState()
    val context = LocalContext.current

    Edit(
        title = "Ajouter un effet secondaire",
        bottomText = "Enregistrer",
        onClick = {
            //viewModel.save(context)
            onClick()
        },
        enabled = false //state.value.prescription != null && state.value.date != null && state.value.description.isNotEmpty()
    ) {
        Column {
            /*ReusableElevatedCard {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    ReusableOutlinedSearchButton(
                        options = viewModel.getPrescriptionList(context),
                        value = state.value.prescription,
                        label = "Prescription",
                        onSelected = { viewModel.updatePrescription(it, context) }
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    ReusableOutlinedDatePickerButton(
                        value = state.value.date,
                        label = "Date de l'effet secondaire",
                        onSelected = { viewModel.updateDate(it) }
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    ReusableOutlinedTextField(
                        value = state.value.description,
                        label = "Description",
                        warnings = false,
                        onValueChange = { viewModel.updateDescription(it) }
                    )
                }
            }*/
        }
    }
}
