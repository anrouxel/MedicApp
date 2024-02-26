package fr.medicapp.medicapp.ui.components.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuOpen
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FloatingActionButtons(buttons: List<Pair<() -> Unit, @Composable () -> Unit >>) {
    var isModalOpen by remember { mutableStateOf(false) }

    if (isModalOpen) {
        Column(
        ) {
            buttons.map {
                FloatingActionButton(
                    onClick = it.first,
                    containerColor = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 10.dp),
                    content = it.second
                )

            }
            FloatingActionButton(
                onClick = { isModalOpen = false },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.MenuOpen,
                    contentDescription = "Bouton pour fermer le menu",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    } else {
        FloatingActionButton(
            onClick = { isModalOpen = true },
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Bouton pour ouvrir le menu",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}
