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
import fr.medicapp.medicapp.model.OptionButtonContent
import fr.medicapp.medicapp.model.OptionDoctor
import fr.medicapp.medicapp.model.Prescription
import fr.medicapp.medicapp.model.ScreenDoctor
import fr.medicapp.medicapp.ui.theme.EUPurple100

@Composable
fun AddPrescriptionDoctorScreen(
    state: Prescription,
    onNavigate: (String, OptionDoctor) -> Unit,
    optionContent: HashMap<OptionDoctor, OptionButtonContent>
) {
    var selectedInstruction by remember {
        mutableStateOf<OptionDoctor?>(state.getDoctor())
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .verticalScroll(
                    enabled = true,
                    state = rememberScrollState()
                )
        ) {
            optionContent.forEach { (instruction, content) ->
                AddPrescriptionOptionButton(
                    title = content.getTitle(),
                    description = content.getDescription(),
                    isSelected = selectedInstruction == instruction,
                    onClick = {
                        selectedInstruction = instruction
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
                selectedInstruction?.let {
                    optionContent[it]?.getNextRoute()?.let { it1 ->
                        onNavigate(
                            it1,
                            it
                        )
                    }
                }
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
    AddPrescriptionDoctorScreen(
        state = Prescription(),
        optionContent = ScreenDoctor.getOptions(),
        onNavigate = { _, _ -> }
    )
}
