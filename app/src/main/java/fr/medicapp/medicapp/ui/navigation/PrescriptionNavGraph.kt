package fr.medicapp.medicapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController

@Composable
fun PrescriptionNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.PRESCRIPTION,
        startDestination = Graph.PRESCRIPTION
    ) {

    }
}