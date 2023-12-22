package fr.medicapp.medicapp.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

fun NavGraphBuilder.prescriptionsNavGraph(
    navController: NavHostController
) {
    composable(route = PrescriptionsRoute.Prescriptions.route) {
    }
    composable(route = PrescriptionsRoute.PrescriptionDetailsWithId.route) {
    }
}

sealed class PrescriptionsRoute(val route: String) {
    object Prescriptions : PrescriptionsRoute("prescriptions")

    object PrescriptionDetailsWithId : PrescriptionsRoute("prescription_details/{id}")
}
