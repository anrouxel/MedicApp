package fr.medicapp.medicapp.ui.components.modal

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

@Composable
fun AlertModal(
    title: String,
    content: String,
    dismissText: String,
    confirmText: String,
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
                text = content
            )
        },

        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) {
                Text(
                    text = confirmText
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissRequest,
            ) {
                Text(
                    text = dismissText
                )
            }
        },
    )
}

@Preview
@Composable
fun PreviewAlertModal() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUYellowColorShema
    ) {
        AlertModal(
            title = "Supprimer ce traitement",
            content = "Êtes-vous sûr de vouloir procéder avec la suppression ? Ce processus est irréversible.",
            dismissText = "Annuler",
            confirmText = "Supprimer"
        )
    }
}
