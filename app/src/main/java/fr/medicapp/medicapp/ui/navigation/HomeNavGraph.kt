package fr.medicapp.medicapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fr.medicapp.medicapp.ui.home.HomeScreen
import fr.medicapp.medicapp.ui.home.NavigationDrawerRoute

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
                    navController.navigate(Graph.ADD_PRESCRIPTIONS)
                }
            )
        }
        composable(route = NavigationDrawerRoute.Prescriptions.route) {
        }
        composable(route = NavigationDrawerRoute.Messages.route) {
        }
        addPrescriptionsNavGraph(navController)
    }
}
