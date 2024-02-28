package fr.medicapp.medicapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fr.medicapp.medicapp.ui.screen.doctor.DoctorDetail
import fr.medicapp.medicapp.ui.screen.doctor.DoctorHome
import fr.medicapp.medicapp.ui.screen.root.RootRoute
import fr.medicapp.medicapp.viewModel.SharedDoctorDetailViewModel

/**
 * Cette fonction construit le graphe de navigation pour les prescriptions.
 *
 * @param navController Le contrôleur de navigation.
 */
@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.doctorNavGraph(
    navController: NavHostController,
) {
    /**
     * Définit la navigation pour le graphe de prescription.
     */
    navigation(
        route = RootRoute.RootRouteDoctorRoute.route,
        startDestination = DoctorRoute.DoctorHomeRoute.route
    ) {
        composable(route = DoctorRoute.DoctorHomeRoute.route) {
            DoctorHome(
                onDoctorClick = {
                    navController.navigate(
                        DoctorRoute.DoctorDetailRoute.route.replace(
                            "{id}",
                            it.toString()
                        )
                    )
                }
            )
        }

        composable(route = DoctorRoute.DoctorDetailRoute.route) {
            val doctor = it.arguments?.getString("id")?.toLongOrNull()

            val viewModel =
                it.sharedViewModel<SharedDoctorDetailViewModel>(navController = navController)

            if (doctor != null) {
                viewModel.loadDoctor(doctor)
            }

            DoctorDetail(
                viewModel = viewModel
            )
        }
    }
}

/**
 * Cette classe scellée définit les différentes routes pour les prescriptions.
 */
sealed class DoctorRoute(val route: String) {
    /**
     * Route pour la page principale des prescriptions.
     */
    object DoctorHomeRoute : DoctorRoute(route = "doctor_home")

    /**
     * Route pour afficher une prescription spécifique.
     */
    object DoctorDetailRoute : DoctorRoute(route = "doctor_detail/{id}")

    /**
     * Route pour ajouter une nouvelle prescription.
     */
    object DoctorEditRoute : DoctorRoute(route = "doctor_edit")
}
