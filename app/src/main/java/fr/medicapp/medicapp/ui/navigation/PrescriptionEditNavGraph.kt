package fr.medicapp.medicapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fr.medicapp.medicapp.model.Prescription
import fr.medicapp.medicapp.ui.screen.prescription.PrescriptionEditInformation
import fr.medicapp.medicapp.ui.screen.prescription.PrescriptionEditNotification
import fr.medicapp.medicapp.ui.screen.prescription.PrescriptionEditTreatment
import fr.medicapp.medicapp.ui.theme.EUPurpleColorShema
import fr.medicapp.medicapp.ui.theme.ThemeColorScheme
import fr.medicapp.medicapp.viewModel.SharedPrescriptionEditViewModel

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.prescriptionEditNavGraph(
    navController: NavHostController,
    onThemeChange: (ThemeColorScheme) -> Unit
) {
    navigation(
        route = PrescriptionRoute.PrescriptionEditRoute.route,
        startDestination = PrescriptionEditRoute.PrescriptionEditInformationRoute.route
    ) {
        composable(route = PrescriptionEditRoute.PrescriptionEditInformationRoute.route) {
            val viewModel = hiltViewModel<SharedPrescriptionEditViewModel>()

            onThemeChange(EUPurpleColorShema)

            PrescriptionEditInformation(
                viewModel = viewModel,
                onClick = {
                    navController.navigate(PrescriptionEditRoute.PrescriptionEditTreatmentRoute.route)
                }
            )
        }

        composable(route = PrescriptionEditRoute.PrescriptionEditTreatmentRoute.route) { backStackEntry ->
            val parent = remember(backStackEntry) {
                navController.getBackStackEntry(PrescriptionEditRoute.PrescriptionEditInformationRoute.route)
            }
            val viewModel = hiltViewModel<SharedPrescriptionEditViewModel>(parent)

            onThemeChange(EUPurpleColorShema)

            PrescriptionEditTreatment(
                viewModel = viewModel,
                onClick = {
                    navController.navigate(PrescriptionEditRoute.PrescriptionEditNotificationRoute.route)
                }
            )
        }

        composable(route = PrescriptionEditRoute.PrescriptionEditNotificationRoute.route) { backStackEntry ->
            val parent = remember(backStackEntry) {
                navController.getBackStackEntry(PrescriptionEditRoute.PrescriptionEditTreatmentRoute.route)
            }
            val viewModel = hiltViewModel<SharedPrescriptionEditViewModel>(parent)

            onThemeChange(EUPurpleColorShema)

            PrescriptionEditNotification(
                viewModel = viewModel,
                onClick = {
                    navController.navigate(PrescriptionRoute.PrescriptionHomeRoute.route) {
                        popUpTo(PrescriptionRoute.PrescriptionEditRoute.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}

/**
 * Récupère une instance partagée du ViewModel spécifié.
 * Cette fonction est utile pour partager des données entre plusieurs composables dans le même graphe de navigation.
 *
 * @param navController Le contrôleur de navigation.
 * @return Une instance partagée du ViewModel.
 */
@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController,
): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}

/**
 * Cette classe scellée définit les différentes routes pour les prescriptions.
 */
sealed class PrescriptionEditRoute(val route: String) {
    object PrescriptionEditInformationRoute : PrescriptionEditRoute(route = "prescription_edit_information")

    object PrescriptionEditTreatmentRoute : PrescriptionEditRoute(route = "prescription_edit_treatment")

    object PrescriptionEditNotificationRoute : PrescriptionEditRoute(route = "prescription_edit_notification")
}
