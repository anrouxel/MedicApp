package fr.medicapp.medicapp.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fr.medicapp.medicapp.ViewModel.AddPrescriptionViewModel
import fr.medicapp.medicapp.model.AddPrescriptionOptionChooseInstruction
import fr.medicapp.medicapp.model.AddPrescriptionOptionPrescriptionSource
import fr.medicapp.medicapp.ui.prescriptions.AddPrescriptionScreen

fun NavGraphBuilder.addPrescriptionsNavGraph(
    navController: NavHostController
) {
    val viewModel = AddPrescriptionViewModel()

    navigation(
        route = Graph.ADD_PRESCRIPTIONS,
        startDestination = AddPrescriptionOptionChooseInstruction.route
    ) {
        composable(route = AddPrescriptionOptionChooseInstruction.route) {
            AddPrescriptionScreen(
                viewModel = viewModel,
                navController = navController,
            )
        }

        composable(route = AddPrescriptionOptionPrescriptionSource.route) {
            AddPrescriptionScreen(
                viewModel = viewModel,
                navController = navController,
            )
        }

        composable(route = "") {
            AddPrescriptionScreen(
                viewModel = viewModel,
                navController = navController,
            )
        }
    }
}

sealed class AddPrescriptionsRoute(val route: String) {
}
