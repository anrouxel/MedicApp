package fr.medicapp.medicapp.ui.prescriptions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.entity.Doctor
import fr.medicapp.medicapp.entity.Prescription
import fr.medicapp.medicapp.model.AddPrescription
import fr.medicapp.medicapp.ui.navigation.AddPrescriptionsRoute
import fr.medicapp.medicapp.ui.theme.EUPurple100
import fr.medicapp.medicapp.ui.theme.EUPurple60

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddPrescriptionAddTreatmentMedicationDosageScreen(
    treatmentId: Int,
    state: AddPrescription,
    onNavigate: (String, Int) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    var dosage by remember { mutableStateOf(state.prescription.treatments[treatmentId].dosage?.toString() ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Qui est le médecin qui a prescrit votre ordonnance ?",
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .verticalScroll(
                    enabled = true,
                    state = rememberScrollState()
                )
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 0.dp, 16.dp, 0.dp),
                text = "Dosage :",
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = dosage,
                onValueChange = { dosage = it },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = EUPurple60,
                    unfocusedBorderColor = EUPurple100
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                onNavigate(AddPrescriptionsRoute.AddTreatmentMedicationDosageFrequency.route, dosage.toInt())
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = EUPurple100,
            ),
            shape = RoundedCornerShape(10.dp),
            enabled = dosage.isNotBlank() && (dosage.toInt() > 0)
        ) {
            Text(
                text = "Suivant"
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun AddPrescriptionScreenPreview() {
    AddPrescriptionAddTreatmentMedicationDosageScreen(
        treatmentId = 0,
        state = AddPrescription()
    ) { _, _ -> }
}
