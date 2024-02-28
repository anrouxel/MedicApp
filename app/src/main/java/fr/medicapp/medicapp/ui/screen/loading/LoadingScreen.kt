package fr.medicapp.medicapp.ui.screen.loading

import androidx.compose.runtime.Composable
import fr.medicapp.medicapp.ui.components.screen.Loading

@Composable
fun LoadingScreen() {
    Loading(title = "Chargement en cours...", text = "Veuillez patienter quelques instants...")
}
