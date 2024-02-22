package fr.medicapp.medicapp.ui.screen.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.ui.components.button.ReusableButton
import fr.medicapp.medicapp.ui.components.button.ReusableRadioGroup
import fr.medicapp.medicapp.ui.components.card.ReusableElevatedCard
import fr.medicapp.medicapp.ui.components.screen.Edit
import fr.medicapp.medicapp.ui.components.textfield.ReusableOutlinedTextField
import fr.medicapp.medicapp.viewModel.SharedUserEditViewModel

@Composable
fun UserEditAllergy(
    viewModel: SharedUserEditViewModel,
    onClick: () -> Unit
) {
    val state = viewModel.sharedState.collectAsState()
    val pregnantState = remember { mutableStateOf("Non") }
    Edit(
        title = "Information utilisateur",
        bottomText = "Terminé",
        onClick = onClick
    ) {

        Column {
            ReusableElevatedCard {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    ReusableRadioGroup(
                        options = listOf("Oui", "Non"),
                        selectedOption = "Non",
                        label = "Êtes-vous enceinte ?",
                        onClick = {
                            pregnantState.value = it
                            viewModel.updatePregnant(it == "Oui")
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))

            ReusableButton(
                text = "Ajouter une allergie",
                onClick = {
                    viewModel.addAllergy()
                }
            )
            state.value.allergies?.forEachIndexed { index, _ ->
                ReusableElevatedCard {
                    Column(
                        modifier = Modifier.padding(10.dp)
                    ) {
                        ReusableOutlinedTextField(
                            value = "",
                            onValueChange = {
                                viewModel.updateAllergy(it, index)
                            },
                            label = "Allergie"
                        )
                    }
                }
            }

        }
    }
}

