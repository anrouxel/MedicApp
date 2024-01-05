package fr.medicapp.medicapp.ui.prescriptions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.entity.Prescription
import fr.medicapp.medicapp.model.AddPrescription
import fr.medicapp.medicapp.model.OptionButtonContent
import fr.medicapp.medicapp.ui.navigation.AddPrescriptionsRoute
import fr.medicapp.medicapp.ui.theme.EUPurple100

@Composable
fun AddPrescriptionAddTreatmentMedicationScreen(
    treatmentId: Int,
    state: AddPrescription,
    optionContent: List<OptionButtonContent>,
    onNavigate: (String, OptionButtonContent) -> Unit,
) {

    var selectedMedication by remember {
        mutableStateOf<OptionButtonContent?>(state.prescription.treatments[treatmentId].medication)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Votre ordonnance a-t-elle été prescrite par un médecin ?",
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
            optionContent.forEach { content ->
                AddPrescriptionOptionButton(
                    title = content.title,
                    description = content.description,
                    isSelected = selectedMedication == content,
                    onClick = {
                        selectedMedication = content
                    }
                )
                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                onNavigate(AddPrescriptionsRoute.AddTreatmentMedicationDosage.route, selectedMedication!!)
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = EUPurple100,
            ),
            shape = RoundedCornerShape(10.dp),
            enabled = selectedMedication != null
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
    AddPrescriptionAddTreatmentMedicationScreen(
        treatmentId = 0,
        state = AddPrescription(),
        optionContent = listOf(
            OptionButtonContent(
                title = "Oui",
                description = "Votre ordonnance a été prescrite par un médecin"
            ),
            OptionButtonContent(
                title = "Non",
                description = "Votre ordonnance n'a pas été prescrite par un médecin"
            )
        )
    ) { _, _ -> }
}
