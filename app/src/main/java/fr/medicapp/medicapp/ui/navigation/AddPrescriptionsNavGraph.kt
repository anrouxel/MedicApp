package fr.medicapp.medicapp.ui.navigation

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
import fr.medicapp.medicapp.ViewModel.SharedAddPrescriptionViewModel
import fr.medicapp.medicapp.ui.prescriptions.AddPrescriptionDoctorScreen
import fr.medicapp.medicapp.ui.prescriptions.AddPrescriptionInstructionsScreen
import fr.medicapp.medicapp.ui.prescriptions.AddPrescriptionSourceScreen

fun NavGraphBuilder.addPrescriptionsNavGraph(
    navController: NavHostController
) {
    navigation(
        route = Graph.ADD_PRESCRIPTIONS,
        startDestination = AddPrescriptionsRoute.ChooseInstructions.route
    ) {
        composable(route = AddPrescriptionsRoute.ChooseInstructions.route) {
            val viewModel = it.sharedViewModel<SharedAddPrescriptionViewModel>(navController = navController)
            val state by viewModel.sharedState.collectAsStateWithLifecycle()
            AddPrescriptionInstructionsScreen(
                state = state,
                optionContent = ScreenChooseInstruction.getOptions(),
                onNavigate = { route, instruction ->
                    viewModel.setInstructions(instruction)
                    navController.navigate(route)
                }
            )
        }

        composable(route = AddPrescriptionsRoute.ChooseSource.route) {
            val viewModel = it.sharedViewModel<SharedAddPrescriptionViewModel>(navController = navController)
            val state by viewModel.sharedState.collectAsStateWithLifecycle()
            AddPrescriptionSourceScreen(
                state = state,
                optionContent = ScreenSource.getOptions(),
                onNavigate = { route ->
                    navController.navigate(route)
                }
            )
        }

        composable(route = AddPrescriptionsRoute.ChooseDoctor.route) {
            val viewModel = it.sharedViewModel<SharedAddPrescriptionViewModel>(navController = navController)
            val state by viewModel.sharedState.collectAsStateWithLifecycle()
            AddPrescriptionDoctorScreen(
                state = state,
                optionContent = ScreenDoctor.getOptions(),
                onNavigate = { route, doctor ->
                    viewModel.setDoctor(doctor)
                    navController.navigate(route)
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
    object ChooseInstructions : AddPrescriptionsRoute(
        route = "choose_instructions"
    )

    object ChooseSource : AddPrescriptionsRoute(
        route = "choose_source"
    )

    object ChooseDoctor : AddPrescriptionsRoute(
        route = "choose_doctor"
    )
}
