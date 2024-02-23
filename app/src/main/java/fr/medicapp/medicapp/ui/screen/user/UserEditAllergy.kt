package fr.medicapp.medicapp.ui.screen.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
    val context = LocalContext.current
    Edit(
        title = "Information utilisateur",
        bottomText = "Terminé",
        onClick = {
            viewModel.save(context)
            onClick()
        }
    ) {
        Column {
            if (state.value.gender == "Femme") {
                ReusableElevatedCard {
                    Column(
                        modifier = Modifier.padding(10.dp)
                    ) {
                        ReusableRadioGroup(
                            options = listOf("Oui", "Non"),
                            selectedOption = pregnantState.value,
                            label = "Êtes-vous enceinte ?",
                            onClick = {
                                pregnantState.value = it
                                viewModel.updatePregnant(it == "Oui")
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(10.dp))
            }

            ReusableButton(
                text = "Ajouter une allergie",
                onClick = {
                    viewModel.addAllergy()
                }
            )
            Spacer(modifier = Modifier.padding(10.dp))
            state.value.allergies.forEachIndexed { index, allergie ->
                ReusableElevatedCard {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ReusableOutlinedTextField(
                            modifier = Modifier.width(270.dp),
                            value = allergie,
                            onValueChange = {
                                viewModel.updateAllergy(it, index)
                            },
                            label = "Allergie"
                        )
                        IconButton(
                            onClick = {
                                viewModel.removeAllergy(index)
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }

        }
    }
}

