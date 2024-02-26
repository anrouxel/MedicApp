package fr.medicapp.medicapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fr.medicapp.medicapp.ui.screen.user.UserEditAllergy
import fr.medicapp.medicapp.ui.screen.user.UserEditGeneralInformation
import fr.medicapp.medicapp.ui.theme.EUPurpleColorShema
import fr.medicapp.medicapp.ui.theme.ThemeColorScheme
import fr.medicapp.medicapp.viewModel.SharedUserEditViewModel

/**
 * Cette fonction construit le graphe de navigation pour l'écran utilisateur.
 *
 * @param navController Le contrôleur de navigation.
 * @param onThemeChange La fonction de changement de thème.
 */
@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.userNavGraph(
    navController: NavHostController,
    onThemeChange: (ThemeColorScheme) -> Unit
) {
    navigation(
        route = Graph.USER,
        startDestination = UserRoute.UserGeneralInformationRoute.route
    ) {
        composable(route = UserRoute.UserGeneralInformationRoute.route) {
            val viewModel =
                it.sharedViewModel<SharedUserEditViewModel>(navController = navController)

            onThemeChange(EUPurpleColorShema)

            UserEditGeneralInformation(
                viewModel = viewModel,
                onClick = {
                    navController.navigate(UserRoute.UserAllergyRoute.route)
                }
            )
        }

        composable(route = UserRoute.UserAllergyRoute.route) {
            val viewModel =
                it.sharedViewModel<SharedUserEditViewModel>(navController = navController)

            onThemeChange(EUPurpleColorShema)

            UserEditAllergy(
                viewModel = viewModel,
                onClick = {
                    navController.navigate(Graph.HOME) {
                        popUpTo(Graph.HOME) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}

sealed class UserRoute(val route: String) {
    object UserGeneralInformationRoute : UserRoute("user_general_information")
    object UserAllergyRoute : UserRoute("user_allergy")

}
