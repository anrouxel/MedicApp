package fr.medicapp.medicapp.ui.navigation

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fr.medicapp.medicapp.ui.components.screen.Loading
import fr.medicapp.medicapp.ui.screen.user.UserEditAllergy
import fr.medicapp.medicapp.ui.screen.user.UserEditGeneralInformation
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
    isUser: Boolean,
    isDownload: Boolean,
    context: Context
) {
    navigation(
        route = Graph.USER,
        startDestination = if (!isUser) UserRoute.UserGeneralInformationRoute.route else UserRoute.LoadingScreenRoute.route
    ) {
        composable(route = UserRoute.UserGeneralInformationRoute.route) {
            val viewModel =
                it.sharedViewModel<SharedUserEditViewModel>(navController = navController)

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

            UserEditAllergy(
                viewModel = viewModel,
                onClick = {
                    if (isDownload) {
                        navController.navigate(Graph.HOME) {
                            popUpTo(Graph.HOME) {
                                inclusive = true
                            }
                        }
                    } else {
                        navController.navigate(UserRoute.LoadingScreenRoute.route)
                    }
                }
            )
        }

        composable(route = UserRoute.LoadingScreenRoute.route) {
            Loading("Chargement en cours...", "Veuillez patienter quelques instants...")
            LaunchedEffect(Unit) {
                val sharedPreferences =
                    context.getSharedPreferences("medicapp", Context.MODE_PRIVATE)
                snapshotFlow {
                    sharedPreferences.getBoolean("isDataDownloaded", false)
                }.collect {
                    Log.d("Guegueintervention", "ça change !!!!!!!")
                    navController.navigate(Graph.HOME) {
                        popUpTo(Graph.HOME) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
}

sealed class UserRoute(val route: String) {
    object UserGeneralInformationRoute : UserRoute("user_general_information")
    object UserAllergyRoute : UserRoute("user_allergy")
    object LoadingScreenRoute : UserRoute("loading_screen")
}
