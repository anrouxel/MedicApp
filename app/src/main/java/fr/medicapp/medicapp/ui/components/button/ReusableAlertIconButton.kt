package fr.medicapp.medicapp.ui.components.button

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import fr.medicapp.medicapp.ui.components.modal.AlertModal

@Composable
fun ReusableAlertIconButton(
    icon: ImageVector,
    contentDescription: String = "",
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

    IconButton(
        onClick = {
            open = true
        }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = MaterialTheme.colorScheme.primary
        )
    }
}
