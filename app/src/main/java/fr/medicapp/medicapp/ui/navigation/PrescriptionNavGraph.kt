package fr.medicapp.medicapp.ui.navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fr.medicapp.medicapp.database.AppDatabase
import fr.medicapp.medicapp.ui.prescription.Prescription
import fr.medicapp.medicapp.ui.prescription.PrescriptionMainMenu
import fr.medicapp.medicapp.ui.prescription.TestConsultation
import fr.medicapp.medicapp.ui.prescription.TestOrdonnance

var ordonnances = listOf(
    TestOrdonnance(
        1,
        "Dr. MOTTU",
        "01/01/2023"
    ),
    TestOrdonnance(
        2,
        "Dr. CAZALAS",
        "02/10/2023"
    ),
    TestOrdonnance(
        3,
        "Dr. BERDJUGIN",
        "10/06/2023"
    )
)

var consultation = TestConsultation(
    "Dr. MOTTU",
    "22/11/2023",
    mutableListOf()
)

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.prescriptionNavGraph(
    navController: NavHostController
) {
    navigation(
        route = Graph.PRESCRIPTION,
        startDestination = PrescriptionRoute.Main.route,
    ) {
        composable(route = PrescriptionRoute.Main.route) {
            /*val db = AppDatabase.getInstance(LocalContext.current)
            val repository = PrescriptionRepository(db.prescriptionDAO())

            val test = repository.getPrescriptionAll()

            Log.d("test", test.toString())
            
            PrescriptionMainMenu(
                ordonnances = ordonnances,
                prescription = {}
            )*/
        }

        composable(route = PrescriptionRoute.Prescription.route) {
            /*Prescription(
                consultation = consultation,
            )*/
        }

        composable(route = PrescriptionRoute.AddPrescription.route) {
            /*val viewModel =
                it.sharedViewModel<SharedAddPrescriptionViewModel>(navController = navController)
            val state by viewModel.sharedState.collectAsStateWithLifecycle()

            val db = AppDatabase.getInstance(LocalContext.current)
            val repository = PrescriptionRepository(db.prescriptionDAO())

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
                onCancel = {
                    navController.popBackStack()
                },
                onConfirm = {
                    repository.addPrescription(state)
                    navController.popBackStack()
                },
            )*/
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

sealed class PrescriptionRoute(val route: String) {
    object Main : PrescriptionRoute(route = "mainmenu")
    object Prescription : PrescriptionRoute(route = "prescription")
    object AddPrescription : PrescriptionRoute(route = "add_prescriptions")
}