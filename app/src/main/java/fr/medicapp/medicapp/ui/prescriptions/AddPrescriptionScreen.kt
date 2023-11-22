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
import fr.medicapp.medicapp.ui.theme.EUPurple100
import fr.medicapp.medicapp.ui.theme.EUPurple40

@Composable
fun AddPrescriptionScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: AddPrescriptionViewModel,
) {
    var selectedOption by remember { mutableStateOf<>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = title,
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
            for (option in options) {
                AddPrescriptionOptionButton(
                    title = option.title,
                    description = option.description,
                    isSelected = selectedOption == option,
                    onClick = { selectedOption = option }
                )

                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                selectedOption.onClick(navController)
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = EUPurple100,
            ),
            shape = RoundedCornerShape(10.dp),
            enabled = selectedOption != AddPrescriptionOption.NONE
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

@Preview(showBackground = true)
@Composable
private fun AddPrescriptionScreenPreview() {
    AddPrescriptionScreen(
        title = "Choisissez une option pour ajouter une ordonnance",
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
