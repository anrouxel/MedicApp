package fr.medicapp.medicapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fr.medicapp.medicapp.database.ObjectBox
import fr.medicapp.medicapp.entity.SideEffectEntity
import fr.medicapp.medicapp.ui.sideeffect.SideEffectHome
import fr.medicapp.medicapp.ui.theme.EUPurpleColorShema
import fr.medicapp.medicapp.ui.theme.EURedColorShema
import fr.medicapp.medicapp.ui.theme.ThemeColorScheme
import fr.medicapp.medicapp.viewModel.SharedSideEffectViewModel

/**
 * Construit le graphique de navigation des effets secondaires.
 * @param navController Le contrôleur de navigation.
 */
@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.sideEffectNavGraph(
    navController: NavHostController,
    onThemeChange: (ThemeColorScheme) -> Unit
) {

    /**
     * Définit la navigation pour le graphe des effets secondaires.
     */
    navigation(
        route = Graph.SIDE_EFFECTS,
        startDestination = SideEffectRoute.Main.route,
    ) {

        /**
         * Composable pour l'écran principal des effets secondaires.
         */
        composable(route = SideEffectRoute.Main.route) {
            onThemeChange(EURedColorShema)
            val store = ObjectBox.getInstance(LocalContext.current)
            val sideEffectStore = store.boxFor(SideEffectEntity::class.java)

            val sideEffects = remember { sideEffectStore.all.map { it.convert() } }

            SideEffectHome(
                sideEffects = sideEffects,
            )
        }

        /**
         * Composable pour l'écran d'un effet secondaire.
         */
        composable(route = SideEffectRoute.SideEffect.route) {
            val id = it.arguments?.getString("id") ?: return@composable

            val store = ObjectBox.getInstance(LocalContext.current)
            val notificationStore = store.boxFor(SideEffectEntity::class.java)

            val sideEffect = remember { notificationStore.get(id.toLong()) }

            /*if (sideEffect != null) {
                SED(
                    sideeffect = sideEffect,
                    onTreatment = { id ->
                        navController.navigate(
                            PrescriptionRoute.Prescription.route.replace(
                                "{id}",
                                id
                            )
                        )
                    },
                    onClose = {
                        navController.navigate(SideEffectRoute.Main.route) {
                            popUpTo(SideEffectRoute.SideEffect.route) {
                                inclusive = true
                            }
                        }
                    }
                ) {
                    notificationStore.remove(id.toLong())

                    navController.navigate(SideEffectRoute.Main.route) {
                        popUpTo(SideEffectRoute.SideEffect.route) {
                            inclusive = true
                        }
                    }
                }
            }*/
        }

        /**
         * Composable pour l'écran d'ajout d'un effet secondaire.
         */
        composable(route = SideEffectRoute.AddSideEffect.route) {
            val viewModel =
                it.sharedViewModel<SharedSideEffectViewModel>(navController = navController)
            val state by viewModel.sharedState.collectAsStateWithLifecycle()

            val store = ObjectBox.getInstance(LocalContext.current)

            val sideEffectStore = store.boxFor(SideEffectEntity::class.java)

            /*SEDEdit(
                sideeffects = state,
                onConfirm = {
                    sideEffectStore.put(state)

                    navController.navigate(SideEffectRoute.Main.route) {
                        popUpTo(SideEffectRoute.AddSideEffect.route) {
                            inclusive = true
                        }
                    }
                },
                onCancel = {
                    navController.navigate(SideEffectRoute.Main.route) {
                        popUpTo(SideEffectRoute.AddSideEffect.route) {
                            inclusive = true
                        }
                    }
                }
            )*/
        }
    }
}

/**
 * Cette classe représente une route d'effet secondaire.
 * @property route La route sous forme de chaîne de caractères.
 */
sealed class SideEffectRoute(val route: String) {
    /**
     * Représente la route principale des effets secondaires.
     */
    object Main : SideEffectRoute(route = "main_side_effects")

    /**
     * Représente une route spécifique pour afficher un effet secondaire.
     */
    object SideEffect : SideEffectRoute(route = "show_side_effect/{id}")

    /**
     * Représente une route pour ajouter un nouvel effet secondaire.
     */
    object AddSideEffect : SideEffectRoute(route = "add_side_effect")
}