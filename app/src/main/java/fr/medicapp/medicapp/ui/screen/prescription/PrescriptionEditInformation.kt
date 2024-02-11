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
import fr.medicapp.medicapp.ui.components.button.ReusableOutlinedDatePickerButton
import fr.medicapp.medicapp.ui.components.button.ReusableOutlinedTextFieldButton
import fr.medicapp.medicapp.ui.components.card.ReusableElevatedCard
import fr.medicapp.medicapp.ui.components.screen.Edit
import fr.medicapp.medicapp.ui.theme.EUPurpleColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import fr.medicapp.medicapp.viewModel.SharedPrescriptionEditViewModel
import java.time.LocalDate

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
        onClick = onClick
    ) {
        ReusableElevatedCard {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                ReusableOutlinedTextFieldButton(
                    value = "",
                    label = "Docteur",
                    onClick = {}
                )

                Spacer(modifier = Modifier.padding(10.dp))

                ReusableOutlinedDatePickerButton(
                    value = state.value.date,
                    label = "Date",
                    onSelected = {
                        viewModel.updateDate(it)
                    }
                )
            }
        }
    }
}

@Preview(name = "Light Theme")
@Composable
private fun PrescriptionEditInformationPreview() {
    val prescription = Prescription()

    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        PrescriptionEditInformation(
            viewModel = viewModel(),
            onClick = {}
        )
    }
}

@Preview(name = "Dark Theme")
@Composable
private fun PrescriptionEditInformationDarkPreview() {
    val prescription = Prescription()

    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        PrescriptionEditInformation(
            viewModel = viewModel(),
            onClick = {}
        )
    }
}
