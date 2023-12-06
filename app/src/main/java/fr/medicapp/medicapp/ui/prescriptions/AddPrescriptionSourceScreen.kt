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
import fr.medicapp.medicapp.model.OptionInstruction
import fr.medicapp.medicapp.model.OptionButtonContent
import fr.medicapp.medicapp.model.Prescription
import fr.medicapp.medicapp.model.ScreenChooseInstruction
import fr.medicapp.medicapp.model.ScreenSource
import fr.medicapp.medicapp.ui.theme.EUPurple100

@Composable
fun AddPrescriptionSourceScreen(
    state: Prescription,
    onNavigate: (String) -> Unit,
    optionInstructionContent: HashMap<Boolean, OptionButtonContent>
) {
    var selectedInstruction by remember {
        mutableStateOf<Boolean?>(if (state.getDoctor() != null) true else null)
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
            optionInstructionContent.forEach { (instruction, content) ->
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
                    optionInstructionContent[it]?.getNextRoute()?.let { it1 ->
                        onNavigate(
                            it1
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
    AddPrescriptionSourceScreen(
        state = Prescription(),
        optionInstructionContent = ScreenSource.getOptions(),
        onNavigate = { _ -> }
    )
}
