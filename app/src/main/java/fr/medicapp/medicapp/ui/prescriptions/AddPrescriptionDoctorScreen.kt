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
import fr.medicapp.medicapp.entity.Doctor
import fr.medicapp.medicapp.model.AddPrescription
import fr.medicapp.medicapp.ui.navigation.AddPrescriptionsRoute
import fr.medicapp.medicapp.ui.theme.EUPurple100
import java.util.UUID

@Composable
fun AddPrescriptionDoctorScreen(
    state: AddPrescription,
    optionContent: MutableList<Doctor>,
    onNavigate: (String, Doctor?) -> Unit,
) {
    var selectedDoctor by remember {
        mutableStateOf<Doctor?>(state.prescription.doctor)
    }

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

        Button(
            onClick = {
                onNavigate(AddPrescriptionsRoute.AddDoctor.route, null)
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
                text = "Ajouter un médecin"
            )
        }

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
            optionContent.forEach { doctor ->
                AddPrescriptionOptionButton(
                    title = doctor.getFullName(),
                    isSelected = doctor == selectedDoctor,
                    onClick = {
                        selectedDoctor = doctor
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
                onNavigate(AddPrescriptionsRoute.ChooseDoctor.route, selectedDoctor!!)
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = EUPurple100,
            ),
            shape = RoundedCornerShape(10.dp),
            enabled = selectedDoctor != null
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
    AddPrescriptionDoctorScreen(
        state = AddPrescription(),
        optionContent = mutableListOf<Doctor>(
            Doctor(
                id = UUID.randomUUID(),
                firstName = "Jean",
                lastName = "Dupont",
            ),
            Doctor(
                id = UUID.randomUUID(),
                firstName = "Jean",
                lastName = "Dupont",
            ),
            Doctor(
                id = UUID.randomUUID(),
                firstName = "Jean",
                lastName = "Dupont",
            ),
        ),
    ) { _, _ -> }
}
