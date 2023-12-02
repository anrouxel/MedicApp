package fr.medicapp.medicapp.ui.prescriptions

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import fr.medicapp.medicapp.ViewModel.AddPrescriptionViewModel
import fr.medicapp.medicapp.model.AddPrescriptionOptionRadioButton
import fr.medicapp.medicapp.model.AddPrescriptionOptionTextField
import fr.medicapp.medicapp.model.AddPrescriptionType
import fr.medicapp.medicapp.ui.theme.EUPurple100
import fr.medicapp.medicapp.ui.theme.EUPurple40

@Composable
fun AddPrescriptionScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: AddPrescriptionViewModel,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = viewModel.getSelectedScreenTitle(),
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
            // Génération des boutons radio
            for (option in viewModel.getSelectedScreenOptions()) {
                when (viewModel.getSelectedScreenType()) {
                    AddPrescriptionType.RadioButtonOption -> {
                        val op = option as AddPrescriptionOptionRadioButton
                        AddPrescriptionOptionButton(
                            title = op.title,
                            description = op.description,
                            isSelected = viewModel.isSelectedScreenOptionRadioButton(op),
                            onClick = {
                                viewModel.setSelectedScreenOptionRadioButton(op)
                            }
                        )
                    }
                    AddPrescriptionType.TextFieldOption -> {
                        val op = option as AddPrescriptionOptionTextField
                        AddPrescriptionTextFieldOption(
                            title = op.title,
                            value = op.value,
                            onValueChange = { op.value = it },
                        )
                    }
                    else -> {}
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate(viewModel.getSelectedScreenNextRoute())
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = EUPurple100,
            ),
            shape = RoundedCornerShape(10.dp),
            enabled = viewModel.getSelectedScreenValidation(),
        ) {
            Text(
                text = "Suivant"
            )
        }
    }
}

@Composable
fun AddPrescriptionOptionButton(
    title: String,
    description: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = if (isSelected) EUPurple100 else EUPurple40,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable { onClick() }
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = description,
                    color = Color.Gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .size(24.dp)
            ) {
                if (isSelected) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = EUPurple100,
                    )
                }
            }
        }
    }
}

@Composable
fun AddPrescriptionTextFieldOption(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
) {

    var text by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Column {
            Text(
                text = title
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = EUPurple100,
                    unfocusedBorderColor = EUPurple40,
                    cursorColor = EUPurple100,
                ),
                shape = RoundedCornerShape(10.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddPrescriptionScreenPreview() {
    AddPrescriptionScreen(
        viewModel = AddPrescriptionViewModel()
    )
}

@Preview
@Composable
private fun AddPrescriptionOptionButtonSelectedPreview() {
    AddPrescriptionOptionButton(
        title = "Ordonnance",
        description = "Ajouter une ordonnance",
        isSelected = true,
        onClick = { }
    )
}

@Preview
@Composable
private fun AddPrescriptionOptionButtonNotSelectedPreview() {
    AddPrescriptionOptionButton(
        title = "Ordonnance",
        description = "Ajouter une ordonnance",
        isSelected = false,
        onClick = { }
    )
}

@Preview
@Composable
private fun AddPrescriptionTextFieldOptionSelectedPreview() {
    AddPrescriptionTextFieldOption(
        title = "Prénom du médecin",
        value = "Jean",
        onValueChange = { }
    )
}
