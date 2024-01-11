package fr.medicapp.medicapp.ui.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MedicalInformation
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.RingVolume
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import fr.medicapp.medicapp.R
import fr.medicapp.medicapp.ui.navigation.Graph
import fr.medicapp.medicapp.ui.navigation.NotificationRoute
import fr.medicapp.medicapp.ui.navigation.PrescriptionRoute
import fr.medicapp.medicapp.ui.navigation.SideEffectRoute
import fr.medicapp.medicapp.ui.theme.EUBlue60
import fr.medicapp.medicapp.ui.theme.EUGreen60
import fr.medicapp.medicapp.ui.theme.EUPurple60
import fr.medicapp.medicapp.ui.theme.EURed60
import fr.medicapp.medicapp.ui.theme.EUYellow60

sealed class NavigationDrawerRoute(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val color: Color,
    val logo: Int
) {
    object Home : NavigationDrawerRoute(
        route = "home",
        title = "Accueil",
        icon = Icons.Filled.Home,
        color = EUGreen60,
        logo = R.drawable.medicapp_eu_green
    )

    object Prescriptions : NavigationDrawerRoute(
        route = PrescriptionRoute.Main.route,
        title = "Mes traitements",
        icon = Icons.Filled.Medication,
        color = EUPurple60,
        logo = R.drawable.medicapp_eu_purple
    )

    object Messages : NavigationDrawerRoute(
        route = SideEffectRoute.Main.route,
        title = "Journal des effets",
        icon = Icons.Filled.Book,
        color = EURed60,
        logo = R.drawable.medicapp_eu_red
    )

    object Notifications : NavigationDrawerRoute(
        route = NotificationRoute.Main.route,
        title = "Gérer notifications",
        icon = Icons.Filled.RingVolume,
        color = EUYellow60,
        logo = R.drawable.medicapp_eu_yellow
    )
}
