package fr.medicapp.medicapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fr.medicapp.medicapp.database.repositories.prescription.SideEffectRepository
import fr.medicapp.medicapp.model.prescription.relationship.SideEffect
import fr.medicapp.medicapp.ui.screen.root.RootRoute
import fr.medicapp.medicapp.ui.screen.sideeffect.SideEffectDetail
import fr.medicapp.medicapp.ui.screen.sideeffect.SideEffectEdit
import fr.medicapp.medicapp.ui.screen.sideeffect.SideEffectHome
import fr.medicapp.medicapp.ui.theme.EUPurpleColorShema
import fr.medicapp.medicapp.ui.theme.ThemeColorScheme
import fr.medicapp.medicapp.viewModel.SharedSideEffectDetailViewModel
import fr.medicapp.medicapp.viewModel.SharedSideEffectEditViewModel

/**
 * Cette fonction construit le graphe de navigation pour les prescriptions.
 *
 * @param navController Le contrôleur de navigation.
 */
@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.sideEffectNavGraph(
    navController: NavHostController,
    onThemeChange: (ThemeColorScheme) -> Unit
) {
    /**
     * Définit la navigation pour le graphe de prescription.
     */
    navigation(
        route = RootRoute.RootRouteSideEffectRoute.route,
        startDestination = SideEffectRoute.SideEffectHomeRoute.route
    ) {
        composable(route = SideEffectRoute.SideEffectHomeRoute.route) {
            onThemeChange(EUPurpleColorShema)

            val context = LocalContext.current
            val result: MutableList<SideEffect> = mutableListOf()
            Thread {
                result.clear()
                result.addAll(SideEffectRepository(context).getAll().toMutableList())
            }.start()

            val sideEffects = remember {
                result
            }

            SideEffectHome(
                sideEffects = sideEffects,
                onAddSideEffectClick = {
                    navController.navigate(SideEffectRoute.SideEffectEditRoute.route)
                },
                onSideEffectClick = {
                    navController.navigate(
                        SideEffectRoute.SideEffectDetailRoute.route.replace(
                            "{id}",
                            it.toString()
                        )
                    )
                }
            )
        }

        composable(route = SideEffectRoute.SideEffectDetailRoute.route) {
            val id = it.arguments?.getString("id")?.toLongOrNull()

            val context = LocalContext.current

            val viewModel =
                it.sharedViewModel<SharedSideEffectDetailViewModel>(navController = navController)

            if (id != null) {
                LaunchedEffect(key1 = id) {
                    viewModel.loadSideEffect(context = context, id = id)
                }
            } else {
                navController.popBackStack()
            }

            SideEffectDetail(
                viewModel = viewModel
            )
        }

        composable(route = SideEffectRoute.SideEffectEditRoute.route) {
            onThemeChange(EUPurpleColorShema)

            val viewModel =
                it.sharedViewModel<SharedSideEffectEditViewModel>(navController = navController)

            SideEffectEdit(
                viewModel = viewModel,
                onClick = {
                    navController.navigate(SideEffectRoute.SideEffectHomeRoute.route) {
                        popUpTo(SideEffectRoute.SideEffectHomeRoute.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}

/**
 * Cette classe scellée définit les différentes routes pour les prescriptions.
 */
sealed class SideEffectRoute(val route: String) {
    /**
     * Route pour la page principale des prescriptions.
     */
    object SideEffectHomeRoute : SideEffectRoute(route = "side_effect_home")

    /**
     * Route pour afficher une prescription spécifique.
     */
    object SideEffectDetailRoute : SideEffectRoute(route = "side_effect_detail/{id}")

    /**
     * Route pour ajouter une nouvelle prescription.
     */
    object SideEffectEditRoute : SideEffectRoute(route = "side_effect_edit")
}
