package fr.medicapp.medicapp.ui.screen.sideeffect

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
import fr.medicapp.medicapp.ui.components.button.ReusableOutlinedDatePickerButton
import fr.medicapp.medicapp.ui.components.button.ReusableOutlinedSearchButton
import fr.medicapp.medicapp.ui.components.card.ReusableElevatedCard
import fr.medicapp.medicapp.ui.components.screen.Edit
import fr.medicapp.medicapp.ui.components.textfield.ReusableOutlinedTextField
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
            viewModel.save(context)
            onClick()
        },
        enabled = state.value.prescription != null && state.value.sideEffectInformation.date != null && state.value.sideEffectInformation.description.isNotEmpty()
    ) {
        Column {
            ReusableElevatedCard {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    ReusableOutlinedSearchButton(
                        options = {
                            viewModel.searchPrescription(it, context)
                        },
                        value = state.value.prescription,
                        label = "Prescription"
                    ) { viewModel.updatePrescription(it, context) }

                    Spacer(modifier = Modifier.padding(10.dp))

                    ReusableOutlinedDatePickerButton(
                        value = state.value.sideEffectInformation.date,
                        label = "Date de l'effet secondaire",
                        onSelected = { viewModel.updateDate(it) }
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    ReusableOutlinedTextField(
                        value = state.value.sideEffectInformation.description,
                        label = "Description",
                        warnings = false,
                        onValueChange = { viewModel.updateDescription(it) }
                    )
                }
            }
        }
    }
}
