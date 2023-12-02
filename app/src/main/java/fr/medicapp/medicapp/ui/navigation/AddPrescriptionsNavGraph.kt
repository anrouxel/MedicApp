package fr.medicapp.medicapp.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fr.medicapp.medicapp.ViewModel.AddPrescriptionViewModel
import fr.medicapp.medicapp.model.AddPrescriptionOptionChooseInstruction
import fr.medicapp.medicapp.model.AddPrescriptionOptionDoctorInfo
import fr.medicapp.medicapp.model.AddPrescriptionOptionPrescriptionSource
import fr.medicapp.medicapp.ui.prescriptions.AddPrescriptionScreen

fun NavGraphBuilder.addPrescriptionsNavGraph(
    navController: NavHostController
) {
    val viewModel = AddPrescriptionViewModel()

    navigation(
        route = Graph.ADD_PRESCRIPTIONS,
        startDestination = AddPrescriptionOptionChooseInstruction.getRoute()
    ) {
        viewModel.addScreen(AddPrescriptionOptionChooseInstruction)
        viewModel.setSelectedScreen(AddPrescriptionOptionChooseInstruction.getRoute())
        composable(route = AddPrescriptionOptionChooseInstruction.getRoute()) {
            AddPrescriptionScreen(
                viewModel = viewModel,
                navController = navController,
            )
        }

        composable(route = AddPrescriptionOptionPrescriptionSource.getRoute()) {
            viewModel.addScreen(AddPrescriptionOptionPrescriptionSource)
            viewModel.setSelectedScreen(AddPrescriptionOptionPrescriptionSource.getRoute())
            AddPrescriptionScreen(
                navController = navController,
                viewModel = viewModel,
            )
        }

        composable(route = AddPrescriptionOptionDoctorInfo.getRoute()) {
            viewModel.addScreen(AddPrescriptionOptionDoctorInfo)
            viewModel.setSelectedScreen(AddPrescriptionOptionDoctorInfo.getRoute())
            AddPrescriptionScreen(
                navController = navController,
                viewModel = viewModel,
            )
        }
    }
}