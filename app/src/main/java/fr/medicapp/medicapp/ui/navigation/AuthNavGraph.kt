package fr.medicapp.medicapp.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fr.medicapp.medicapp.ui.signupSignin.SignIn
import fr.medicapp.medicapp.ui.signupSignin.SignUp1
import fr.medicapp.medicapp.ui.signupSignin.SignUp2

/**
 * Construit le graphe de navigation pour l'authentification.
 *
 * @param navController Le contrôleur de navigation.
 */
fun NavGraphBuilder.authNavGraph(navController: NavHostController) {

    /**
     * Définit la navigation pour le graphe de notification.
     */
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {

        /**
         * Composable pour l'écran de connexion.
         */
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

        /**
         * Composable pour l'écran d'enregistrement par mail et mot de passe.
         */
        composable(AuthScreen.RegisterMailPW.route) {
            SignUp1(
                back = {
                    /*navController.navigate("oi") {
                        popUpTo()
                        TODO
                    }*/
                },
                validate = {
                    navController.navigate(AuthScreen.RegisterName.route)
                }
            )
        }

        /**
         * Composable pour l'écran d'enregistrement du nom.
         */
        composable(AuthScreen.RegisterName.route) {
            SignUp2(
                back = {
                    /*navController.navigate("oi") {
                        popUpTo()
                        TODO
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

/**
 * Ceci est une classe d'écran d'authentification.
 * Elle contient les routes pour les différents écrans d'authentification.
 */
sealed class AuthScreen(val route: String) {
    /**
     * Écran de connexion.
     */
    object Login : AuthScreen(route = "login")

    /**
     * Écran d'enregistrement par mail et mot de passe.
     */
    object RegisterMailPW : AuthScreen(route = "registermailpw")

    /**
     * Écran d'enregistrement du nom.
     */
    object RegisterName : AuthScreen(route = "registername")

    /**
     * Écran d'oubli du mot de passe.
     */
    object Forgot : AuthScreen(route = "forgot")
}