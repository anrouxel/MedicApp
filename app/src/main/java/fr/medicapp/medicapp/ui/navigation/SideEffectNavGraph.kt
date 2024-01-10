package fr.medicapp.medicapp.ui.navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fr.medicapp.medicapp.database.AppDatabase
import fr.medicapp.medicapp.entity.DurationEntity
import fr.medicapp.medicapp.entity.PrescriptionWithTreatmentEntity
import fr.medicapp.medicapp.entity.TreatmentEntity
import fr.medicapp.medicapp.model.Doctor
import fr.medicapp.medicapp.repository.PrescriptionRepository
import fr.medicapp.medicapp.repository.PrescriptionWithTreatmentRepository
import fr.medicapp.medicapp.repository.TreatmentRepository
import fr.medicapp.medicapp.ui.prescription.EditPrescription
import fr.medicapp.medicapp.ui.prescription.Prescription
import fr.medicapp.medicapp.ui.prescription.PrescriptionMainMenu
import fr.medicapp.medicapp.ui.prescription.TestConsultation
import fr.medicapp.medicapp.ui.prescription.TestOrdonnance
import fr.medicapp.medicapp.ui.sideeffectsdiary.SEDEdit
import fr.medicapp.medicapp.viewModel.SharedAddPrescriptionViewModel
import fr.medicapp.medicapp.viewModel.SharedSideEffectViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.sideEffectNavGraph(
    navController: NavHostController
) {
    navigation(
        route = Graph.SIDE_EFFECTS,
        startDestination = SideEffectRoute.Main.route,
    ) {
        composable(route = SideEffectRoute.Main.route) {
            val db = AppDatabase.getInstance(LocalContext.current)
            val repository = TreatmentRepository(db.treatmentDAO())

            var result: MutableList<TreatmentEntity> = mutableListOf()
            Thread {
                result.clear()
                result.addAll(repository.getAll().toMutableList())

                result.forEach {
                    Log.d("TAG", it.toString())
                }
            }.start()

            val ordonnance = remember {
                result
            }

            PrescriptionMainMenu(
                ordonnances = ordonnance,
                onPrescription = { id ->
                    navController.navigate(PrescriptionRoute.Prescription.route.replace("{id}", id))
                },
                addPrescription = {
                    navController.navigate(PrescriptionRoute.AddPrescription.route)
                },
            )
        }

        composable(route = SideEffectRoute.SideEffect.route) {
            val id = it.arguments?.getString("id") ?: return@composable
            val db = AppDatabase.getInstance(LocalContext.current)
            val repository = TreatmentRepository(db.treatmentDAO())

            var result: MutableList<TreatmentEntity> = mutableListOf()

            Thread {
                result.clear()
                result.add(repository.getOne(id))
            }.start()

            val prescription = remember {
                result
            }

            Prescription(
                consultation = prescription,
            )
        }

        composable(route = SideEffectRoute.AddSideEffect.route) {
            val viewModel =
                it.sharedViewModel<SharedSideEffectViewModel>(navController = navController)
            val state by viewModel.sharedState.collectAsStateWithLifecycle()

            val db = AppDatabase.getInstance(LocalContext.current)
            val repository = TreatmentRepository(db.treatmentDAO())

            SEDEdit(
                sideeffects = state,
            )
        }
    }
}

sealed class SideEffectRoute(val route: String) {
    object Main : SideEffectRoute(route = "main_side_effects")
    object SideEffect : SideEffectRoute(route = "side_effect/{id}")
    object AddSideEffect : SideEffectRoute(route = "add_side_effect")
}