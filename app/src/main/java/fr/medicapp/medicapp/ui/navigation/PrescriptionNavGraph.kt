package fr.medicapp.medicapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fr.medicapp.medicapp.ui.screen.root.RootRoute
import fr.medicapp.medicapp.ui.screen.prescription.PrescriptionHome
import fr.medicapp.medicapp.ui.theme.EUPurpleColorShema
import fr.medicapp.medicapp.ui.theme.ThemeColorScheme

/**
 * Cette fonction construit le graphe de navigation pour les prescriptions.
 *
 * @param navController Le contrôleur de navigation.
 */
@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.prescriptionNavGraph(
    navController: NavHostController,
    onThemeChange: (ThemeColorScheme) -> Unit
) {
    /**
     * Définit la navigation pour le graphe de prescription.
     */
    navigation(
        route = RootRoute.RootRoutePrescriptionRoute.route,
        startDestination = PrescriptionRoute.PrescriptionHomeRoute.route
    ) {
        composable(route = PrescriptionRoute.PrescriptionHomeRoute.route) {
            onThemeChange(EUPurpleColorShema)
            PrescriptionHome(
                prescriptions = emptyList(),
                onAddPrescriptionClick = {
                    navController.navigate(PrescriptionRoute.PrescriptionEditRoute.route)
                }
            )
        }

        prescriptionEditNavGraph(
            navController = navController,
            onThemeChange = onThemeChange
        )
    }
}

/**
 * Cette classe scellée définit les différentes routes pour les prescriptions.
 */
sealed class PrescriptionRoute(val route: String) {
    /**
     * Route pour la page principale des prescriptions.
     */
    object PrescriptionHomeRoute : PrescriptionRoute(route = "prescription_home")

    /**
     * Route pour afficher une prescription spécifique.
     */
    object PrescriptionDetailRoute : PrescriptionRoute(route = "prescription_detail")

    /**
     * Route pour ajouter une nouvelle prescription.
     */
    object PrescriptionEditRoute : PrescriptionRoute(route = "prescription_edit")
}
