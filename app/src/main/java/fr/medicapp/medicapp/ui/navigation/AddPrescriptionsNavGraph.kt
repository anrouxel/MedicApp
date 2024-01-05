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
import fr.medicapp.medicapp.viewModel.SharedAddPrescriptionViewModel
import fr.medicapp.medicapp.ui.prescriptions.AddPrescriptionAddDoctorScreen
import fr.medicapp.medicapp.ui.prescriptions.AddPrescriptionAddTreatmentMedicationDosageFrequencyScreen
import fr.medicapp.medicapp.ui.prescriptions.AddPrescriptionAddTreatmentMedicationDosageScreen
import fr.medicapp.medicapp.ui.prescriptions.AddPrescriptionAddTreatmentMedicationScreen
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
                optionContent = viewModel.instruction
            ) { route, instruction ->
                viewModel.setInstruction(instruction)
                navController.navigate(route)
            }
        }

        composable(route = AddPrescriptionsRoute.ChooseSource.route) {
            val viewModel = it.sharedViewModel<SharedAddPrescriptionViewModel>(navController = navController)
            val state by viewModel.sharedState.collectAsStateWithLifecycle()
            AddPrescriptionSourceScreen(
                state = state,
                optionContent = viewModel.source
            ) { route, source ->
                viewModel.setSource(source)
                if (!source) {
                    val index = viewModel.addTreatment()
                    route.replace("{treatmentId}", index.toString())
                }
                navController.navigate(route)
            }
        }

        composable(route = AddPrescriptionsRoute.ChooseDoctor.route) {
            val viewModel = it.sharedViewModel<SharedAddPrescriptionViewModel>(navController = navController)
            val state by viewModel.sharedState.collectAsStateWithLifecycle()
            AddPrescriptionDoctorScreen(
                state = state,
                optionContent = viewModel.doctors.value
            ) { route, doctor ->
                if (doctor != null) {
                    viewModel.setDoctor(doctor)
                    val index = viewModel.addTreatment()
                    route.replace("{treatmentId}", index.toString())
                }
                navController.navigate(route)
            }
        }

        composable(route = AddPrescriptionsRoute.AddDoctor.route) {
            val viewModel = it.sharedViewModel<SharedAddPrescriptionViewModel>(navController = navController)
            val state by viewModel.sharedState.collectAsStateWithLifecycle()
            AddPrescriptionAddDoctorScreen(
                state = state
            ) { route, doctor ->
                viewModel.addDoctor(doctor)
                viewModel.setDoctor(doctor)
                navController.navigate(route) {
                    popUpTo(route = route) {
                        inclusive = true
                    }
                }
            }
        }

        composable(route = AddPrescriptionsRoute.AddTreatmentMedication.route) {
            val viewModel = it.sharedViewModel<SharedAddPrescriptionViewModel>(navController = navController)
            val state by viewModel.sharedState.collectAsStateWithLifecycle()
            val treatmentId = it.arguments?.getInt("treatmentId") ?: 0
            AddPrescriptionAddTreatmentMedicationScreen(
                treatmentId = treatmentId,
                state = state,
                optionContent = viewModel.medication
            ) { route, medication ->
                viewModel.setMedication(treatmentId, medication)
                navController.navigate(route)
            }
        }

        composable(route = AddPrescriptionsRoute.AddTreatmentMedicationDosage.route) {
            val viewModel = it.sharedViewModel<SharedAddPrescriptionViewModel>(navController = navController)
            val state by viewModel.sharedState.collectAsStateWithLifecycle()
            val treatmentId = it.arguments?.getInt("treatmentId") ?: 0
            AddPrescriptionAddTreatmentMedicationDosageScreen(
                treatmentId = treatmentId,
                state = state,
            ) { route, dosage ->
                viewModel.setDosage(treatmentId, dosage)
                navController.navigate(route)
            }
        }

        composable(route = AddPrescriptionsRoute.AddTreatmentMedicationDosageFrequency.route) {
            val viewModel = it.sharedViewModel<SharedAddPrescriptionViewModel>(navController = navController)
            val state by viewModel.sharedState.collectAsStateWithLifecycle()
            val treatmentId = it.arguments?.getInt("treatmentId") ?: 0
            AddPrescriptionAddTreatmentMedicationDosageFrequencyScreen(
                treatmentId = treatmentId,
                state = state,
            ) { route, dosage ->
                viewModel.setDosage(treatmentId, dosage)
                navController.navigate(route)
            }
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

    object AddDoctor : AddPrescriptionsRoute(
        route = "add_doctor"
    )

    object AddTreatment : AddPrescriptionsRoute(
        route = "add_treatment/{treatmentId}"
    )

    object AddTreatmentMedication : AddPrescriptionsRoute(
        route = "add_treatment_medication/{treatmentId}"
    )

    object AddTreatmentMedicationDosage : AddPrescriptionsRoute(
        route = "add_treatment_medication_dosage/{treatmentId}"
    )

    object AddTreatmentMedicationDosageFrequency : AddPrescriptionsRoute(
        route = "add_treatment_medication_dosage_frequency/{treatmentId}"
    )

    object AddTreatmentMedicationDosageDuration : AddPrescriptionsRoute(
        route = "add_treatment_medication_dosage_duration/{treatmentId}"
    )
}
