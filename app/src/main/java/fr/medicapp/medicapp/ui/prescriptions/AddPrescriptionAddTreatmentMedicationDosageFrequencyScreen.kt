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
import fr.medicapp.medicapp.entity.Frequency
import fr.medicapp.medicapp.entity.Prescription
import fr.medicapp.medicapp.model.AddPrescription
import fr.medicapp.medicapp.ui.navigation.AddPrescriptionsRoute
import fr.medicapp.medicapp.ui.theme.EUPurple100
import fr.medicapp.medicapp.ui.theme.EUPurple60

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddPrescriptionAddTreatmentMedicationDosageFrequencyScreen(
    treatmentId: Int,
    state: AddPrescription,
    onNavigate: (String, Int) -> Unit,
) {
    var frequencies by remember { mutableStateOf(state.prescription.treatments[treatmentId].frequencies) }

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
            frequencies.forEachIndexed { index, frequency ->
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 0.dp, 16.dp, 0.dp),
                    text = "Fréquence ${index + 1} jour(s) :",
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    value = frequency.day.toString(),
                    onValueChange = { frequencies[index].day = it.toInt() },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = EUPurple60,
                        unfocusedBorderColor = EUPurple100
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 0.dp, 16.dp, 0.dp),
                    text = "Fréquence ${index + 1} heure(s) :",
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    value = frequency.hour.toString(),
                    onValueChange = { frequencies[index].hour = it.toInt() },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = EUPurple60,
                        unfocusedBorderColor = EUPurple100
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            Button(
                onClick = {
                    frequencies.add(Frequency())
                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = EUPurple100,
                ),
                shape = RoundedCornerShape(10.dp),
                enabled = true
            ) {
                Text(
                    text = "Ajouter une fréquence"
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                onNavigate(AddPrescriptionsRoute.ChooseDoctor.route, treatmentId)
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = EUPurple100,
            ),
            shape = RoundedCornerShape(10.dp),
            enabled = frequencies.isNotEmpty()
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
    AddPrescriptionAddTreatmentMedicationDosageFrequencyScreen(
        treatmentId = 0,
        state = AddPrescription()
    ) { _, _ -> }
}
