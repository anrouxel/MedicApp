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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.model.OptionDialog
import fr.medicapp.medicapp.model.SideEffect
import fr.medicapp.medicapp.ui.components.button.ReusableOutlinedDatePickerButton
import fr.medicapp.medicapp.ui.components.button.ReusableOutlinedSearchButton
import fr.medicapp.medicapp.ui.components.card.ReusableElevatedCard
import fr.medicapp.medicapp.ui.components.screen.Edit
import fr.medicapp.medicapp.ui.components.textfield.ReusableOutlinedTextField
import fr.medicapp.medicapp.ui.theme.EURedColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import fr.medicapp.medicapp.viewModel.SharedSideEffectEditViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SideEffectEdit(
    viewModel: SharedSideEffectEditViewModel,
    onClick: () -> Unit
) {
    val state = viewModel.sharedState.collectAsState()
    val context = LocalContext.current

    SideEffectEditContent(
        state = state.value,
        onClick = onClick,
        getPrescriptionList = {viewModel.getPrescriptionList(context)},
        updatePrescription = { prescription -> viewModel.updatePrescription(prescription, context) },
        updateDate = { date -> viewModel.updateDate(date) },
        updateDescription = { description -> viewModel.updateDescription(description) },
        save = { viewModel.save(context) }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun SideEffectEditContent(
    state: SideEffect,
    onClick: () -> Unit,
    getPrescriptionList: () -> List<OptionDialog>,
    updatePrescription: (prescription: OptionDialog) -> Unit,
    updateDate: (date: LocalDate) -> Unit = {},
    updateDescription: (description: String) -> Unit = {},
    save: () -> Unit = {}
) {
    Edit(
        title = "Ajouter un effet secondaire",
        bottomText = "Enregistrer",
        onClick = {
            save()
            onClick()
        }
    ) {
        Column {
            ReusableElevatedCard {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    ReusableOutlinedSearchButton(
                        options = getPrescriptionList(),
                        value = state.prescription,
                        label = "Prescription",
                        onSelected = { updatePrescription(it) }
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    ReusableOutlinedDatePickerButton(
                        value = state.date,
                        label = "Date de l'effet secondaire",
                        onSelected = { updateDate(it) }
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    ReusableOutlinedTextField(
                        value = state.description,
                        label = "Description",
                        warnings = false,
                        onValueChange = { updateDescription(it) }
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Light Theme", showSystemUi = true)
@Composable
private fun SideEffectEditPreview() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EURedColorShema
    ) {
        SideEffectEditContent(
            state = SideEffect(),
            onClick = {},
            getPrescriptionList = { emptyList() },
            updatePrescription = {},
            updateDate = {},
            updateDescription = {},
            save = {}
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Dark Theme", showSystemUi = true)
@Composable
private fun SideEffectDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EURedColorShema
    ) {
        SideEffectEditContent(
            state = SideEffect(),
            onClick = {},
            getPrescriptionList = { emptyList() },
            updatePrescription = {},
            updateDate = {},
            updateDescription = {},
            save = {}
        )
    }
}
