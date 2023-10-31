package fr.medicapp.medicapp.ui.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MedicalInformation
import androidx.compose.material.icons.filled.Message
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import fr.medicapp.medicapp.ui.theme.EUBlue60
import fr.medicapp.medicapp.ui.theme.EUGreen60
import fr.medicapp.medicapp.ui.theme.EUPurple60

sealed class NavigationDrawerRoute(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val color: Color
) {
    object Home : NavigationDrawerRoute(
        route = "home",
        title = "Accueil",
        icon = Icons.Filled.Home,
        color = EUGreen60
    )

    object Prescriptions : NavigationDrawerRoute(
        route = "prescriptions",
        title = "Mes ordonnances",
        icon = Icons.Filled.MedicalInformation,
        color = EUPurple60
    )

    object Messages : NavigationDrawerRoute(
        route = "messages",
        title = "Messagerie",
        icon = Icons.Filled.Message,
        color = EUBlue60
    )
}