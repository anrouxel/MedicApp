package fr.medicapp.medicapp.ui.prescriptions

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.ui.theme.EUGreen100
import fr.medicapp.medicapp.ui.theme.EUPurple100
import fr.medicapp.medicapp.ui.theme.EUPurple40

@Composable
fun AddPrescriptionScreen() {
    var selectedOption by remember { mutableStateOf(AddPrescriptionOption.NONE) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AddPrescriptionOptions(
            options = listOf(
                AddPrescriptionOption.CAMERA,
                AddPrescriptionOption.GALLERY,
                AddPrescriptionOption.MANUAL
            ),
            selectedOption = selectedOption,
            onOptionSelected = { selectedOption = it }
        )
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = EUGreen100
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Suivant"
            )
        }
    }
}

@Composable
fun AddPrescriptionOptions(
    options: List<AddPrescriptionOption>,
    selectedOption: AddPrescriptionOption,
    onOptionSelected: (AddPrescriptionOption) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Choisissez une option pour ajouter une ordonnance", fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        // Génération des boutons radio
        for (option in options) {
            AddPrescriptionOptionButton(
                title = option.title,
                description = option.description,
                isSelected = selectedOption == option,
                onClick = { onOptionSelected(option) }
            )

            Spacer(modifier = Modifier.height(8.dp))
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
            .clickable { onClick() }
            .border(
                width = 1.dp,
                color = if (isSelected) EUPurple100 else EUPurple40,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(16.dp),
    ) {
        Column {
            Text(text = title, fontWeight = FontWeight.Bold)
            Text(text = description, color = Color.Gray)
        }
    }
}

enum class AddPrescriptionOption(
    val title: String,
    val description: String
) {
    CAMERA("Caméra", "Prendre une photo"),
    GALLERY("Galerie", "Sélectionner depuis la galerie"),
    MANUAL("Manuel", "Entrer les détails manuellement"),
    NONE("", "")
}

@Preview(showBackground = true)
@Composable
private fun AddPrescriptionScreenPreview() {
    AddPrescriptionScreen()
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