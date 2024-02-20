package fr.medicapp.medicapp.ui.components.button

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import fr.medicapp.medicapp.ui.components.modal.AlertModal

@Composable
fun ReusableAlertButton(
    text: String,
    onClick: () -> Unit,
    title: String,
    content: String,
    dismissText: String,
    confirmText: String
) {
    var open by remember { mutableStateOf(false) }

    if (open) {
        AlertModal(
            title = title,
            content = content,
            dismissText = dismissText,
            confirmText = confirmText,
            onDismissRequest = { open = false },
            onConfirm = {
                open = false
                onClick()
            }
        )
    }

    ReusableButton(
        text = text,
        onClick = {
            open = true
        }
    )
}
