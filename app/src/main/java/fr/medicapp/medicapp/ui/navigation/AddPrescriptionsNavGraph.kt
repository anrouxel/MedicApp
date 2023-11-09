package fr.medicapp.medicapp.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fr.medicapp.medicapp.ui.prescriptions.AddPrescriptionOptionChooseInstruction
import fr.medicapp.medicapp.ui.prescriptions.AddPrescriptionScreen

fun NavGraphBuilder.addPrescriptionsNavGraph(
    navController: NavHostController
) {
    navigation(
        route = Graph.ADD_PRESCRIPTIONS,
        startDestination = AddPrescriptionsRoute.AddPrescriptions.route
    ) {
        composable(route = AddPrescriptionsRoute.AddPrescriptions.route) {
            AddPrescriptionScreen(
                navController = navController,
                title = "Choisissez une option pour ajouter une ordonnance",
                options = listOf(
                    AddPrescriptionOptionChooseInstruction.CAMERA,
                    AddPrescriptionOptionChooseInstruction.GALLERY,
                    AddPrescriptionOptionChooseInstruction.MANUAL,
                )
            )
        }
    }
}

sealed class AddPrescriptionsRoute(val route: String) {
    object AddPrescriptions : AddPrescriptionsRoute(
        route = "add_prescriptions"
    )
}
