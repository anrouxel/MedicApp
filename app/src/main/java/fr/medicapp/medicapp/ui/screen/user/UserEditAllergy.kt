package fr.medicapp.medicapp.ui.screen.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.ui.components.button.ReusableButton
import fr.medicapp.medicapp.ui.components.button.ReusableRadioGroup
import fr.medicapp.medicapp.ui.components.card.ReusableElevatedCard
import fr.medicapp.medicapp.ui.components.screen.Edit
import fr.medicapp.medicapp.ui.components.textfield.ReusableOutlinedTextField
import fr.medicapp.medicapp.ui.theme.EUPurpleColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

@Composable
fun UserEditAllergy(
    //add viewModel
    onClick: () -> Unit
) {
    Edit(
        title = "Information utilisateur",
        bottomText = "Terminé",
        onClick = onClick
    ) {
        val pregnantState = remember { mutableStateOf("Non") }
        Column {
            ReusableElevatedCard {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    ReusableRadioGroup(
                        options = listOf("Oui", "Non"),
                        selectedOption = pregnantState,
                        label = "Êtes-vous enceinte ?"
                    )
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))

            ReusableButton(
                text = "Ajouter une allergie",
                onClick = {}
            )
            ReusableElevatedCard {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    ReusableOutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = "Allergie"
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun UserEditAllergyPreview() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        UserEditAllergy(onClick = {})
    }
}

@Preview
@Composable
fun UserEditAllergyDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        UserEditAllergy(onClick = {})
    }
}