package fr.medicapp.medicapp.ui.screen.root

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
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
sealed class RootRoute(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    /**
     * Route de l'écran d'accueil.
     */
    object RootRouteHomeRoute : RootRoute(
        route = "home",
        title = "Accueil",
        icon = Icons.Filled.Home,
    )

    /**
     * Route de l'écran des prescriptions.
     */
    object RootRoutePrescriptionRoute : RootRoute(
        route = "prescription",
        title = "Prescriptions",
        icon = Icons.Filled.Medication,
    )

    /**
     * Route de l'écran des effets secondaires.
     */
    object RootRouteSideEffectRoute : RootRoute(
        route = "sideEffect",
        title = "Effets",
        icon = Icons.Filled.Newspaper,
    )

    /**
     * Route de l'écran des médecins.
     */
    object RootRouteDoctorRoute : RootRoute(
        route = "doctor",
        title = "Médecins",
        icon = Icons.Filled.Person,
    )

    /**
     * Route de l'écran utilisateur //Non utilisé.
     */
    object RootRouteUserRoute : RootRoute(
        route = "user",
        title = "Utilisateur",
        icon = Icons.Filled.Settings,
    )
}
