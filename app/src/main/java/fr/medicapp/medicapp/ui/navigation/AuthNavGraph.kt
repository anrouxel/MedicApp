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
                    navController.navigate(AuthScreen.RegisterMailPW.route)
                }
            )
        }
        composable(AuthScreen.RegisterMailPW.route) {
            SignUp1(
                back = {
                    /*navController.navigate("oi") {
                        popUpTo()
                    }*/
                },
                validate = {
                    navController.navigate(AuthScreen.RegisterName.route)
                }
            )
        }
        composable(AuthScreen.RegisterName.route) {
            SignUp2(
                back = {
                    /*navController.navigate("oi") {
                        popUpTo()
                    }*/
                },
                validate = {
                    navController.popBackStack()
                    navController.navigate(Graph.HOME)
                }
            )
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "login")
    object RegisterMailPW : AuthScreen(route = "registermailpw")
    object RegisterName : AuthScreen(route = "registername")
    object Forgot : AuthScreen(route = "forgot")
}