package fr.medicapp.medicapp.ui.components.modal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.model.OptionDialog
import fr.medicapp.medicapp.ui.components.textfield.ReusableOutlinedTextField
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import kotlinx.coroutines.launch

/**
 * Cette fonction affiche une boîte de dialogue de recherche avec des options spécifiques.
 *
 * @param options La liste des options pour la boîte de dialogue.
 * @param cardColor La couleur des cartes dans la boîte de dialogue.
 * @param selectedCardColor La couleur de la carte sélectionnée dans la boîte de dialogue.
 * @param onDismiss La fonction à exécuter lorsque l'utilisateur ferme la boîte de dialogue.
 * @param onValidate La fonction à exécuter lorsque l'utilisateur valide une option.
 * @param preQuery La requête de recherche pré-remplie.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchModal(
    title: String,
    options: suspend (String) -> List<OptionDialog>,
    onDismissRequest: () -> Unit = {},
    onConfirm: suspend (OptionDialog) -> Unit = {},
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf<OptionDialog?>(null) }
    val searchResults = remember { mutableStateOf<List<OptionDialog>>(emptyList()) }
    val scope = rememberCoroutineScope()

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = title,
            )
        },
        text = {
            Column {
                ReusableOutlinedTextField(
                    value = searchQuery,
                    onValueChange = {
                        scope.launch {
                            searchQuery = it
                            searchResults.value = options(it)
                        }
                    },
                    label = "",
                )
                Spacer(modifier = Modifier.height(10.dp))
                LazyColumn {
                    items(searchResults.value) { option ->
                        ElevatedCard(
                            onClick = {
                                selectedOption = option
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = if (option == selectedOption) {
                                CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.onPrimary
                                )
                            } else {
                                CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surface,
                                    contentColor = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        ) {
                            Text(
                                text = option.title,
                                modifier = Modifier.padding(5.dp),
                            )

                            option.description?.let {
                                Text(
                                    text = it,
                                    modifier = Modifier.padding(5.dp),
                                    color = if (option == selectedOption) {
                                        MaterialTheme.colorScheme.onPrimary
                                    } else {
                                        MaterialTheme.colorScheme.onSurface
                                    }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.padding(10.dp))
                    }
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.surface,
        dismissButton = {
            TextButton(
                onClick = onDismissRequest,
            ) {
                Text("Annuler")
            }
        },
        confirmButton = {
            TextButton(
                enabled = selectedOption != null,
                onClick = {
                    scope.launch {
                        selectedOption?.let { onConfirm(it) }
                    }
                }
            ) {
                Text("OK")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun SearchModalPreview() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUYellowColorShema
    ) {
        SearchModal(
            title = "Recherche",
            options = { listOf() }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchModalDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EUYellowColorShema
    ) {
        SearchModal(
            title = "Recherche",
            options = { listOf() }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchModalWithOptionPreview() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUYellowColorShema
    ) {
        SearchModal(
            title = "Recherche",
            options = {
                listOf(
                    OptionDialog(
                        id = 0L,
                        title = "Option 1",
                        description = "Description de l'option 1"
                    )
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchModalWithOptionDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EUYellowColorShema
    ) {
        SearchModal(
            title = "Recherche",
            options = {
                listOf(
                    OptionDialog(
                        id = 0L,
                        title = "Option 1",
                        description = "Description de l'option 1"
                    )
                )
            }
        )
    }
}
