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
import fr.medicapp.medicapp.ui.theme.EUPurple100
import fr.medicapp.medicapp.ui.theme.EUPurple20
import fr.medicapp.medicapp.ui.theme.EUPurple40
import fr.medicapp.medicapp.ui.theme.EUPurple80
import fr.medicapp.medicapp.ui.theme.EUYellow110

@Composable
fun SearchDialog(
    options: List<OptionDialog>,
    onDismiss: () -> Unit,
    onValidate: (OptionDialog) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
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
                            modifier = Modifier.fillMaxWidth(),
                            colors = if (option == selectedOption) {
                                CardDefaults.cardColors(
                                    containerColor = EUPurple80,
                                    contentColor = Color.White
                                )
                            } else {
                                CardDefaults.cardColors(
                                    containerColor = EUPurple20,
                                    contentColor = Color.Unspecified
                                )
                            }
                        ) {
                            Text(
                                text = option.title,
                                modifier = Modifier.clickable {
                                    selectedOption = option
                                }.padding(5.dp),

                            )
                        }

                        Spacer(modifier = Modifier.height(7.dp))
                    }
                }
            }
        },
        confirmButton = {
            Button(
                enabled = selectedOption != null,
                colors = ButtonDefaults.buttonColors(
                    disabledContainerColor = EUPurple20,
                    disabledContentColor = Color.White,
                    containerColor = EUPurple100,
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