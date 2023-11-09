package fr.medicapp.medicapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fr.medicapp.medicapp.ui.home.Home
import fr.medicapp.medicapp.ui.home.NavigationDrawerRoute

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = NavigationDrawerRoute.Home.route
    ) {
        composable(NavigationDrawerRoute.Home.route) {
            Home()
        }
        composable(NavigationDrawerRoute.Prescriptions.route) {
            Home()
        }
        composable(NavigationDrawerRoute.Messages.route) {
            Home()
        }
    }
}
