package fr.medicapp.medicapp.ui.navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fr.medicapp.medicapp.database.ObjectBox
import fr.medicapp.medicapp.database.entity.PrescriptionEntity
import fr.medicapp.medicapp.ui.components.mozilla.Gecko
import fr.medicapp.medicapp.ui.screen.prescription.PrescriptionDetail
import fr.medicapp.medicapp.ui.screen.prescription.PrescriptionHome
import fr.medicapp.medicapp.ui.screen.root.RootRoute
import fr.medicapp.medicapp.ui.theme.EUPurpleColorShema
import fr.medicapp.medicapp.ui.theme.ThemeColorScheme
import fr.medicapp.medicapp.viewModel.SharedPrescriptionDetailViewModel

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
            val boxStore = ObjectBox.getInstance(context)
            val store = boxStore.boxFor(PrescriptionEntity::class.java)

            val prescriptions = store.all.map { it.convert() }

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

            if (id != null) {
                viewModel.loadPrescription(context = LocalContext.current, id = id)
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
