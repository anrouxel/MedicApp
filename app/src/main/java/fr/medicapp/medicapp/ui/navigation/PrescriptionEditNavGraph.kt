package fr.medicapp.medicapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fr.medicapp.medicapp.ui.screen.prescription.PrescriptionEditInformation
import fr.medicapp.medicapp.ui.screen.prescription.PrescriptionEditLoading
import fr.medicapp.medicapp.ui.screen.prescription.PrescriptionEditNotification
import fr.medicapp.medicapp.viewModel.SharedPrescriptionEditViewModel

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.prescriptionEditNavGraph(
    navController: NavHostController,
) {
    navigation(
        route = PrescriptionRoute.PrescriptionEditRoute.route,
        startDestination = PrescriptionEditRoute.PrescriptionEditInformationRoute.route
    ) {
        composable(route = PrescriptionEditRoute.PrescriptionEditInformationRoute.route) {
            val viewModel =
                it.sharedViewModel<SharedPrescriptionEditViewModel>(navController = navController)

            PrescriptionEditInformation(
                viewModel = viewModel,
                onClick = {
                    navController.navigate(PrescriptionEditRoute.PrescriptionEditNotificationRoute.route)
                }
            )
        }

        composable(route = PrescriptionEditRoute.PrescriptionEditLoadingRoute.route) {
            val viewModel =
                it.sharedViewModel<SharedPrescriptionEditViewModel>(navController = navController)

            PrescriptionEditLoading(
                viewModel = viewModel,
                onClick = {
                    navController.navigate(PrescriptionEditRoute.PrescriptionEditInformationRoute.route)
                }
            )
        }

        composable(route = PrescriptionEditRoute.PrescriptionEditNotificationRoute.route) {
            val viewModel =
                it.sharedViewModel<SharedPrescriptionEditViewModel>(navController = navController)

            PrescriptionEditNotification(
                viewModel = viewModel,
                onClick = {
                    val navigation = if (viewModel.sharedState.value.isNotEmpty()) {
                        PrescriptionEditRoute.PrescriptionEditInformationRoute.route
                    } else {
                        PrescriptionRoute.PrescriptionHomeRoute.route
                    }
                    navController.navigate(navigation) {
                        popUpTo(PrescriptionRoute.PrescriptionEditRoute.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}

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
    object PrescriptionEditInformationRoute :
        PrescriptionEditRoute(route = "prescription_edit_information")

    object PrescriptionEditNotificationRoute :
        PrescriptionEditRoute(route = "prescription_edit_notification")

    object PrescriptionEditLoadingRoute :
        PrescriptionEditRoute(route = "prescription_edit_loading")
}
