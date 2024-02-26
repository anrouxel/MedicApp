package fr.medicapp.medicapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fr.medicapp.medicapp.database.repositories.prescription.PrescriptionRepository
import fr.medicapp.medicapp.model.prescription.relationship.Prescription
import fr.medicapp.medicapp.ui.screen.prescription.PrescriptionDetail
import fr.medicapp.medicapp.ui.screen.prescription.PrescriptionHome
import fr.medicapp.medicapp.ui.screen.root.RootRoute
import fr.medicapp.medicapp.ui.theme.EUPurpleColorShema
import fr.medicapp.medicapp.ui.theme.ThemeColorScheme
import fr.medicapp.medicapp.viewModel.SharedPrescriptionDetailViewModel
import kotlinx.coroutines.launch

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

            val context = LocalContext.current
            var result: MutableList<Prescription> = mutableListOf()
            Thread {
                result.clear()
                result.addAll(PrescriptionRepository(context).getAll().toMutableList())
            }.start()

            val prescriptions = remember {
                result
            }

            PrescriptionHome(
                prescriptions = prescriptions,
                onAddPrescriptionClick = {
                    navController.navigate(PrescriptionRoute.PrescriptionEditRoute.route)
                },
                onPrescriptionClick = {
                    navController.navigate(
                        PrescriptionRoute.PrescriptionDetailRoute.route.replace(
                            "{id}",
                            it.toString()
                        )
                    )
                }
            )
        }

        composable(route = PrescriptionRoute.PrescriptionDetailRoute.route) {
            val id = it.arguments?.getString("id")?.toLongOrNull()

            val viewModel =
                it.sharedViewModel<SharedPrescriptionDetailViewModel>(navController = navController)

            val context = LocalContext.current
            val scope = rememberCoroutineScope()

            if (id != null) {
                scope.launch {
                    viewModel.loadPrescription(context = context, id = id)
                }
            } else {
                navController.popBackStack()
            }

            PrescriptionDetail(
                viewModel = viewModel,
                onRemovePrescriptionClick = {
                    navController.popBackStack()
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
    object PrescriptionDetailRoute : PrescriptionRoute(route = "prescription_detail/{id}")

    /**
     * Route pour ajouter une nouvelle prescription.
     */
    object PrescriptionEditRoute : PrescriptionRoute(route = "prescription_edit")
}
