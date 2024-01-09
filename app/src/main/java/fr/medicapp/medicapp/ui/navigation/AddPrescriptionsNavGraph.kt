package fr.medicapp.medicapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fr.medicapp.medicapp.entity.Doctor
import fr.medicapp.medicapp.ui.prescription.EditPrescription
import fr.medicapp.medicapp.viewModel.SharedAddPrescriptionViewModel

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.addPrescriptionsNavGraph(
    navController: NavHostController
) {
    navigation(
        route = Graph.ADD_PRESCRIPTIONS,
        startDestination = AddPrescriptionsRoute.AddPrescription.route
    ) {
        composable(route = AddPrescriptionsRoute.AddPrescription.route) {
            val viewModel = it.sharedViewModel<SharedAddPrescriptionViewModel>(navController = navController)
            val state by viewModel.sharedState.collectAsStateWithLifecycle()

            EditPrescription(
                prescription = state,
                doctors = listOf(
                    Doctor(
                        lastName = "Doe",
                        firstName = "John",
                    ),
                    Doctor(
                        lastName = "Doe",
                        firstName = "Jane",
                    ),
                ),
                onValidate = {
                    navController.popBackStack()
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

sealed class AddPrescriptionsRoute(
    val route: String,
) {
    object AddPrescription : AddPrescriptionsRoute(
        route = "add_prescription"
    )
}
