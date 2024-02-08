package fr.medicapp.medicapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fr.medicapp.medicapp.ui.home.HomeScreen
import fr.medicapp.medicapp.ui.home.NavigationDrawerRoute
import fr.medicapp.medicapp.ui.theme.EUGreenColorShema
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.ThemeColorScheme

/**
 * Ceci est une classe de navigation pour l'écran d'accueil.
 * Elle contient les routes pour les différents écrans de l'application.
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeNavGraph(navController: NavHostController, onThemeChange: (ThemeColorScheme) -> Unit) {

    /**
     * Construit le graphe de navigation pour l'écran d'accueil.
     *
     * @param navController Le contrôleur de navigation.
     */
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = NavigationDrawerRoute.NavigationDrawerHomeRoute.route
    ) {

        /**
         * Composable pour l'écran d'accueil.
         */
        composable(route = NavigationDrawerRoute.NavigationDrawerHomeRoute.route) {
            onThemeChange(EUGreenColorShema)
            HomeScreen(
                onAddPrescriptionClick = {
                    navController.navigate(PrescriptionRoute.PrescriptionEditRoute.route)
                },
            )
        }

        /**
         * Appelle la fonction prescriptionNavGraph pour construire le graphe de navigation des prescriptions.
         */
        prescriptionNavGraph(
            navController = navController,
            onThemeChange = onThemeChange
        )
    }
}
