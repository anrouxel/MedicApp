package fr.medicapp.medicapp.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fr.medicapp.medicapp.ui.signupSignin.SignIn
import fr.medicapp.medicapp.ui.signupSignin.SignUp1
import fr.medicapp.medicapp.ui.signupSignin.SignUp2

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {
        composable(AuthScreen.Login.route) {
            SignIn(
                login = {
                    navController.popBackStack()
                    navController.navigate(Graph.HOME)
                },
                register = {
                    navController.navigate(AuthScreen.Register.route)
                }
            )
        }
        composable(AuthScreen.Register.route) {
            SignUp2(

            )
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "login")
    object Register : AuthScreen(route = "register")
    object Forgot : AuthScreen(route = "forgot")
}