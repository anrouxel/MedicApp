package fr.medicapp.medicapp.ui.components.modal

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme


@Composable
fun DeleteModal(
    title: String,
    onDismissRequest: () -> Unit = {},
    onConfirm: () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = title
            )
        },
        text = {
            Text(
                text = "Êtes-vous sûr de vouloir procéder avec la suppression ? Ce processus est irréversible."
            )
        },

        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) {
                Text("Supprimer")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissRequest,
            ) {
                Text("Annuler")
            }
        },
    )
}

@Preview
@Composable
fun PreviewDeleteModal() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUYellowColorShema
    ) {
        DeleteModal(title = "Supprimer ce traitement")
    }
}