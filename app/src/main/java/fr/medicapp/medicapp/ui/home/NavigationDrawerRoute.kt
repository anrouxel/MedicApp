package fr.medicapp.medicapp.ui.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Medication
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Classe représentant les différentes routes du tiroir de navigation.
 *
 * Chaque route est définie par :
 * - un chemin de route
 * - un titre
 * - une icône
 * - une couleur
 * - un logo
 *
 * @property route Le chemin de la route.
 * @property title Le titre de la route.
 * @property icon L'icône de la route.
 */
sealed class NavigationDrawerRoute(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    /**
     * Route de l'écran d'accueil.
     */
    object NavigationDrawerHomeRoute : NavigationDrawerRoute(
        route = "home",
        title = "Accueil",
        icon = Icons.Filled.Home,
    )

    /**
     * Route de l'écran des prescriptions.
     */
    object NavigationDrawerPrescriptionRoute : NavigationDrawerRoute(
        route = "prescription",
        title = "Mes traitements",
        icon = Icons.Filled.Medication,
    )
}
