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
import fr.medicapp.medicapp.model.SideEffect
import fr.medicapp.medicapp.model.Treatment
import fr.medicapp.medicapp.ui.sideeffectsdiary.SED
import fr.medicapp.medicapp.ui.sideeffectsdiary.SEDEdit
import fr.medicapp.medicapp.ui.sideeffectsdiary.SEDMainMenu
import fr.medicapp.medicapp.viewModel.SharedSideEffectViewModel

/**
 * Construit le graphique de navigation des effets secondaires.
 * @param navController Le contrôleur de navigation.
 */
@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.sideEffectNavGraph(
    navController: NavHostController
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
            /*val db = AppDatabase.getInstance(LocalContext.current)
            val repositorySideEffect = SideEffectRepository(db.sideEffectDAO())
            val repositoryTreatment = TreatmentRepository(db.treatmentDAO())
            val repositoryMedication = MedicationRepository(db.medicationDAO())

            var result: MutableList<SideEffect> = mutableListOf()
            Thread {
                val sideEffectEntityTmp = repositorySideEffect.getAll()

                val sideEffects = sideEffectEntityTmp.map {
                    val treatmentTmp =
                        repositoryTreatment.getOne(it.medicament).toTreatment(repositoryMedication)
                    val sideEffectTmp = it.toSideEffect()
                    sideEffectTmp.medicament = treatmentTmp
                    sideEffectTmp
                }

                result.clear()
                result.addAll(sideEffects)
            }.start()

            val sideEffect = remember {
                result
            }

            SEDMainMenu(
                sideeffects = sideEffect,
                onSideEffect = { id ->
                    navController.navigate(SideEffectRoute.SideEffect.route.replace("{id}", id))
                },
                addSideEffect = {
                    navController.navigate(SideEffectRoute.AddSideEffect.route)
                },
            )*/
        }

        /**
         * Composable pour l'écran d'un effet secondaire.
         */
        composable(route = SideEffectRoute.SideEffect.route) {
            /*val id = it.arguments?.getString("id") ?: return@composable
            val db = AppDatabase.getInstance(LocalContext.current)
            val repositorySideEffect = SideEffectRepository(db.sideEffectDAO())
            val repositoryTreatment = TreatmentRepository(db.treatmentDAO())
            val repositoryMedication = MedicationRepository(db.medicationDAO())

            var result: MutableList<SideEffect> = mutableListOf()

            Thread {
                result.clear()
                val sideEffectEntityTmp = repositorySideEffect.getOne(id)
                if (sideEffectEntityTmp != null) {
                    val treatmentTmp = repositoryTreatment.getOne(sideEffectEntityTmp.medicament)
                        .toTreatment(repositoryMedication)
                    val sideEffectTmp = sideEffectEntityTmp.toSideEffect()
                    sideEffectTmp.medicament = treatmentTmp
                    result.add(sideEffectTmp)
                }
            }.start()

            val sideEffect = remember {
                result
            }

            if (sideEffect != null) {
                SED(
                    sideeffects = sideEffect,
                    onMedication = { id ->
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
                    },
                    onRemove = {
                        Thread {
                            sideEffect.map { side -> side.toEntity() }.forEach { side ->
                                repositorySideEffect.delete(side)
                            }
                        }.start()
                        navController.navigate(SideEffectRoute.Main.route) {
                            popUpTo(SideEffectRoute.SideEffect.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }*/
        }

        /**
         * Composable pour l'écran d'ajout d'un effet secondaire.
         */
        composable(route = SideEffectRoute.AddSideEffect.route) {
            /*val viewModel =
                it.sharedViewModel<SharedSideEffectViewModel>(navController = navController)
            val state by viewModel.sharedState.collectAsStateWithLifecycle()

            val db = AppDatabase.getInstance(LocalContext.current)
            val repositorySideEffect = SideEffectRepository(db.sideEffectDAO())
            val repositoryTreatment = TreatmentRepository(db.treatmentDAO())
            val repositoryMedication = MedicationRepository(db.medicationDAO())

            var result: MutableList<Treatment> = mutableListOf()

            Thread {
                result.clear()
                result.addAll(
                    repositoryTreatment.getAll().map { it.toTreatment(repositoryMedication) }
                        .toMutableList()
                )
            }.start()

            val treatments = remember {
                result
            }

            SEDEdit(
                sideeffects = state,
                treatments = treatments,
                onConfirm = {
                    Thread {
                        repositorySideEffect.add(state.toEntity())
                    }.start()
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