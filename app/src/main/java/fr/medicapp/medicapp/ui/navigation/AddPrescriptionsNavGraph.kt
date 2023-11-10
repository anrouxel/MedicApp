package fr.medicapp.medicapp.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fr.medicapp.medicapp.ui.prescriptions.AddPrescriptionOptionChooseInstruction
import fr.medicapp.medicapp.ui.prescriptions.AddPrescriptionOptionFrequency
import fr.medicapp.medicapp.ui.prescriptions.AddPrescriptionOptionPrescriptionSource
import fr.medicapp.medicapp.ui.prescriptions.AddPrescriptionScreen

fun NavGraphBuilder.addPrescriptionsNavGraph(
    navController: NavHostController
) {
    navigation(
        route = Graph.ADD_PRESCRIPTIONS,
        startDestination = AddPrescriptionsRoute.AddPrescriptionsChooseInstruction.route
    ) {
        composable(route = AddPrescriptionsRoute.AddPrescriptionsChooseInstruction.route) {
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

        composable(route = AddPrescriptionsRoute.AddPrescriptionsSource.route) {
            AddPrescriptionScreen(
                navController = navController,
                title = "Votre traitement est-il prescrit par votre médecin ?",
                options = listOf(
                    AddPrescriptionOptionPrescriptionSource.DOCTOR_PRESCRIPTION,
                    AddPrescriptionOptionPrescriptionSource.SELF_PRESCRIPTION
                )
            )
        }

        composable(route = AddPrescriptionsRoute.AddPrescriptionsFrequency.route) {
            AddPrescriptionScreen(
                navController = navController,
                title = "Quelle est la fréquence de ce traitement?",
                options = listOf(
                    AddPrescriptionOptionFrequency.ONCE_DAILY,
                    AddPrescriptionOptionFrequency.TWICE_DAILY,
                    AddPrescriptionOptionFrequency.CUSTOM_FREQUENCY
                )
            )
        }
    }
}

sealed class AddPrescriptionsRoute(val route: String) {
    object AddPrescriptionsChooseInstruction : AddPrescriptionsRoute("add_prescriptions_choose_instruction")
    object AddPrescriptionsSource : AddPrescriptionsRoute("add_prescriptions_source")

    object AddPrescriptionsFrequency : AddPrescriptionsRoute("add_prescriptions_frequency")
}
