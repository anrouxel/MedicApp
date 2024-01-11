package fr.medicapp.medicapp.ui.prescription

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import fr.medicapp.medicapp.model.OptionDialog
import fr.medicapp.medicapp.ui.theme.EUBlue100

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
        title = { Text("Select a medication") },
        text = {
            Column {
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("Search") }
                )
                LazyColumn {
                    items(filteredOptions) { option ->
                        Text(
                            text = option.title,
                            modifier = Modifier.clickable {
                                selectedOption = option
                            },
                            color = if (option == selectedOption) {
                                EUBlue100
                            } else {
                                Color.Unspecified
                            }
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                selectedOption?.let(onValidate)
            }) {
                Text("Validate")
            }
        }
    )
}