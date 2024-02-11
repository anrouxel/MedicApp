package fr.medicapp.medicapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fr.medicapp.medicapp.ui.screen.root.RootRoute
import fr.medicapp.medicapp.ui.screen.prescription.PrescriptionHome
import fr.medicapp.medicapp.ui.screen.sideeffect.SideEffectEdit
import fr.medicapp.medicapp.ui.screen.sideeffect.SideEffectHome
import fr.medicapp.medicapp.ui.theme.EUPurpleColorShema
import fr.medicapp.medicapp.ui.theme.ThemeColorScheme

/**
 * Cette fonction construit le graphe de navigation pour les prescriptions.
 *
 * @param navController Le contrôleur de navigation.
 */
@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.sideEffectNavGraph(
    navController: NavHostController,
    onThemeChange: (ThemeColorScheme) -> Unit
) {
    /**
     * Définit la navigation pour le graphe de prescription.
     */
    navigation(
        route = RootRoute.RootRouteSideEffectRoute.route,
        startDestination = SideEffectRoute.SideEffectHomeRoute.route
    ) {
        composable(route = SideEffectRoute.SideEffectHomeRoute.route) {
            onThemeChange(EUPurpleColorShema)
            SideEffectHome(
                sideEffects = emptyList(),
                onAddSideEffectClick = {
                    navController.navigate(SideEffectRoute.SideEffectEditRoute.route)
                }
            )
        }

        composable(route = SideEffectRoute.SideEffectEditRoute.route) {
            onThemeChange(EUPurpleColorShema)
            SideEffectEdit()
        }
    }
}

/**
 * Cette classe scellée définit les différentes routes pour les prescriptions.
 */
sealed class SideEffectRoute(val route: String) {
    /**
     * Route pour la page principale des prescriptions.
     */
    object SideEffectHomeRoute : SideEffectRoute(route = "side_effect_home")

    /**
     * Route pour afficher une prescription spécifique.
     */
    object SideEffectDetailRoute : SideEffectRoute(route = "side_effect_detail")

    /**
     * Route pour ajouter une nouvelle prescription.
     */
    object SideEffectEditRoute : SideEffectRoute(route = "side_effect_edit")
}
