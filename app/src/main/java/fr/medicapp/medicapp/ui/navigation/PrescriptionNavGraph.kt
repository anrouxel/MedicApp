package fr.medicapp.medicapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import fr.medicapp.medicapp.ui.prescription.PrescriptionMainMenu
import fr.medicapp.medicapp.ui.prescription.TestOrdonnance

var ordonnances = listOf(
    TestOrdonnance(1,
        "Dr. MOTTU",
        "01/01/2023"),
    TestOrdonnance(2,
        "Dr. CAZALAS",
        "02/10/2023"),
    TestOrdonnance(3,
        "Dr. BERDJUGIN",
        "10/06/2023")
)
@Composable
fun PrescriptionNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.PRESCRIPTION,
        startDestination = PrescriptionScreen.Main.route
    ) {
        composable(PrescriptionScreen.Main.route) {
            PrescriptionMainMenu(
                ordonnances,
                prescription = {
                    navController.navigate(Graph.PRESCRIPTION)
                }
            )
        }
    }
}

sealed class PrescriptionScreen(val route: String) {
    object Main : PrescriptionScreen(route = "mainmenu")
    object Prescription : PrescriptionScreen(route = "prescription")
}