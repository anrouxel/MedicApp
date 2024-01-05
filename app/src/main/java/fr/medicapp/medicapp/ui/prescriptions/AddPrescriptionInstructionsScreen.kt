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
fun AddPrescriptionInstructionsScreen(
    state: AddPrescription,
    optionContent: List<OptionButtonContent>,
    onNavigate: (String, OptionButtonContent) -> Unit,
) {
    var selectedInstruction by remember {
        mutableStateOf<OptionButtonContent?>(state.instructions)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Comment souhaitez-vous ajouter votre ordonnance ?",
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
                    isSelected = selectedInstruction == content,
                    onClick = {
                        selectedInstruction = content
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
                onNavigate(selectedInstruction!!.route!!, selectedInstruction!!)
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = EUPurple100,
            ),
            shape = RoundedCornerShape(10.dp),
            enabled = selectedInstruction != null
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
    AddPrescriptionInstructionsScreen(
        state = AddPrescription(),
        optionContent = listOf(
            OptionButtonContent(
                title = "Caméra",
                description = "Prenez une photo de votre ordonnance",
                route = AddPrescriptionsRoute.ChooseSource.route
            ),
            OptionButtonContent(
                title = "Galerie",
                description = "Choisissez une photo de votre ordonnance",
                route = AddPrescriptionsRoute.ChooseSource.route
            ),
            OptionButtonContent(
                title = "Manuellement",
                description = "Entrez manuellement les informations de votre ordonnance",
                route = AddPrescriptionsRoute.ChooseSource.route
            ),
        )
    ) { _, _ -> }
}
