package fr.medicapp.medicapp.ui.prescription

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.model.OptionDialog
import fr.medicapp.medicapp.ui.theme.EUBlue100
import fr.medicapp.medicapp.ui.theme.EUPurple10
import fr.medicapp.medicapp.ui.theme.EUPurple100
import fr.medicapp.medicapp.ui.theme.EUPurple20
import fr.medicapp.medicapp.ui.theme.EUPurple40
import fr.medicapp.medicapp.ui.theme.EUPurple80
import fr.medicapp.medicapp.ui.theme.EUYellow110

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
fun SearchDialog(
    options: List<OptionDialog>,
    cardColor : Color,
    selectedCardColor : Color,
    onDismiss: () -> Unit,
    onValidate: (OptionDialog) -> Unit,
    preQuery: String = ""
) {
    var searchQuery by remember { mutableStateOf(preQuery) }
    var selectedOption by remember { mutableStateOf<OptionDialog?>(null) }
    val filteredOptions = options.filter {
        it.title.contains(searchQuery, ignoreCase = true)
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Choisissez un médicament") },
        text = {
            Column {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("Recherche") }
                )
                Spacer(modifier = Modifier.height(10.dp))
                LazyColumn {
                    items(filteredOptions) { option ->
                        ElevatedCard(
                            onClick = {
                                selectedOption = option
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = if (option == selectedOption) {
                                CardDefaults.cardColors(
                                    containerColor = selectedCardColor,
                                    contentColor = Color.White
                                )
                            } else {
                                CardDefaults.cardColors(
                                    containerColor = cardColor,
                                    contentColor = Color.Unspecified
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
                                    color = if (option == selectedOption) Color.White else Color.Black
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(7.dp))
                    }
                }
            }
        },
        containerColor = Color.White,
        confirmButton = {
            Button(
                enabled = selectedOption != null,
                colors = ButtonDefaults.buttonColors(
                    disabledContainerColor = cardColor,
                    disabledContentColor = Color.White,
                    containerColor = selectedCardColor,
                    contentColor = Color.White,
                ),
                onClick = {
                selectedOption?.let(onValidate)
            }) {
                Text("Valider")
            }
        }
    )
}