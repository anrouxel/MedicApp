package fr.medicapp.medicapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fr.medicapp.medicapp.ui.home.HomeScreen
import fr.medicapp.medicapp.ui.home.NavigationDrawerRoute
import fr.medicapp.medicapp.ui.prescription.EditPrescription
import fr.medicapp.medicapp.ui.prescription.TestConsultation
import fr.medicapp.medicapp.ui.prescription.TestMedicament

var tab = TestConsultation(
    "Dr. MOTTU",
    "22/11/2023",
    mutableListOf(
        TestMedicament("Doliprane 1000mg",
            "3 fois par jour",
            3,
            "8 jours",
            true,
            true,
            ""),
        TestMedicament("Cortisone",
            "1 fois par jour",
            0,
            "",
            false,
            false,
            ""),
        TestMedicament("Esoméprazole",
            "2 fois par jour",
            2,
            "8 semaines",
            true,
            false,
            ""),
        TestMedicament("Monoprost",
            "1 fois par jour",
            10,
            "15 jours",
            true,
            true,
            ""),
        TestMedicament("Ibuprofène",
            "2 fois par jour",
            0,
            "8 jours",
            true,
            true,
            "")
    )
)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = NavigationDrawerRoute.Home.route
    ) {
        composable(route = NavigationDrawerRoute.Home.route) {
            HomeScreen(
                onAddPrescriptionClick = {
                    navController.navigate(PrescriptionRoute.AddPrescription.route)
                }
            )
        }
        composable(route = NavigationDrawerRoute.Prescriptions.route) {
        }
        composable(route = NavigationDrawerRoute.Messages.route) {
        }
        prescriptionNavGraph(navController)
    }
}
