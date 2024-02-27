package fr.medicapp.medicapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fr.medicapp.medicapp.database.repositories.prescription.PrescriptionRepository
import fr.medicapp.medicapp.model.prescription.relationship.Prescription
import fr.medicapp.medicapp.ui.screen.home.HomeScreen
import fr.medicapp.medicapp.ui.screen.root.RootRoute

/**
 * Ceci est une classe de navigation pour l'écran d'accueil.
 * Elle contient les routes pour les différents écrans de l'application.
 */
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun HomeNavGraph(
    navController: NavHostController,
) {
    /**
     * Construit le graphe de navigation pour l'écran d'accueil.
     *
     * @param navController Le contrôleur de navigation.
     */
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = RootRoute.RootRouteHomeRoute.route
    ) {
        /**
         * Composable pour l'écran d'accueil.
         */
        composable(route = RootRoute.RootRouteHomeRoute.route) {
            val context = LocalContext.current
            var result: MutableList<Prescription> = mutableListOf()
            Thread {
                result.clear()
                result.addAll(PrescriptionRepository(context).getAll().toMutableList())
            }.start()

            val prescriptions = remember {
                result
            }

            HomeScreen(
                prescriptions = prescriptions
            )
        }

        /**
         * Appelle la fonction prescriptionNavGraph pour construire le graphe de navigation des prescriptions.
         */
        prescriptionNavGraph(
            navController = navController,
        )

        /**
         * Appelle la fonction sideEffectNavGraph pour construire le graphe de navigation des effets secondaires.
         */
        sideEffectNavGraph(
            navController = navController,
        )

        doctorNavGraph(
            navController = navController,
        )
    }
}
