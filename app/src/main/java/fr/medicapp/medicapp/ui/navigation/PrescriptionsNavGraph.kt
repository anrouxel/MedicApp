package fr.medicapp.medicapp.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import fr.medicapp.medicapp.ui.home.Home

fun NavGraphBuilder.prescriptionsNavGraph(
    navController: NavController
) {
    composable(PrescriptionsScreen.Prescriptions.route) {
        Home()
    }
    composable(PrescriptionsScreen.PrescriptionDetailsWithId.route) {
        Home()
    }
}

sealed class PrescriptionsScreen(val route: String) {
    object Prescriptions : PrescriptionsScreen("prescriptions")

    object PrescriptionDetailsWithId : PrescriptionsScreen("prescription_details/{id}")
}
