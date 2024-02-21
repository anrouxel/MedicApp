package fr.medicapp.medicapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fr.medicapp.medicapp.database.ObjectBox
import fr.medicapp.medicapp.database.entity.DoctorEntity
import fr.medicapp.medicapp.database.entity.PrescriptionEntity
import fr.medicapp.medicapp.model.Doctor
import fr.medicapp.medicapp.ui.screen.doctor.DoctorDetail
import fr.medicapp.medicapp.ui.screen.doctor.DoctorHome
import fr.medicapp.medicapp.ui.screen.prescription.PrescriptionDetail
import fr.medicapp.medicapp.ui.screen.prescription.PrescriptionHome
import fr.medicapp.medicapp.ui.screen.root.RootRoute
import fr.medicapp.medicapp.ui.theme.EUPurpleColorShema
import fr.medicapp.medicapp.ui.theme.ThemeColorScheme
import fr.medicapp.medicapp.viewModel.SharedDoctorDetailViewModel
import fr.medicapp.medicapp.viewModel.SharedPrescriptionDetailViewModel

/**
 * Cette fonction construit le graphe de navigation pour les prescriptions.
 *
 * @param navController Le contrôleur de navigation.
 */
@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.doctorNavGraph(
    navController: NavHostController,
    onThemeChange: (ThemeColorScheme) -> Unit
) {
    /**
     * Définit la navigation pour le graphe de prescription.
     */
    navigation(
        route = RootRoute.RootRouteDoctorRoute.route,
        startDestination = DoctorRoute.DoctorDetailRoute.route
    ) {
        composable(route = DoctorRoute.DoctorHomeRoute.route) {
            onThemeChange(EUPurpleColorShema)

            val context = LocalContext.current
            val boxStore = ObjectBox.getInstance(context)
            val store = boxStore.boxFor(DoctorEntity::class.java)

            val doctors = store.all.map { it.convert() }

            DoctorHome(
                doctors = doctors,
                onDoctorClick = {
                    navController.navigate(
                        PrescriptionRoute.PrescriptionDetailRoute.route.replace(
                            "{id}",
                            it.toString()
                        )
                    )
                }
            )
        }

        composable(route = DoctorRoute.DoctorDetailRoute.route) {
            val viewModel =
                it.sharedViewModel<SharedDoctorDetailViewModel>(navController = navController)

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
    object DoctorDetailRoute : DoctorRoute(route = "doctor_detail")

    /**
     * Route pour ajouter une nouvelle prescription.
     */
    object DoctorEditRoute : DoctorRoute(route = "doctor_edit")
}
