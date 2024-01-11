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
import fr.medicapp.medicapp.entity.SideEffectEntity
import fr.medicapp.medicapp.entity.TreatmentEntity
import fr.medicapp.medicapp.model.Doctor
import fr.medicapp.medicapp.model.SideEffect
import fr.medicapp.medicapp.repository.PrescriptionRepository
import fr.medicapp.medicapp.repository.PrescriptionWithTreatmentRepository
import fr.medicapp.medicapp.repository.SideEffectRepository
import fr.medicapp.medicapp.repository.TreatmentRepository
import fr.medicapp.medicapp.ui.notifications.Notifications
import fr.medicapp.medicapp.ui.notifications.NotificationsEdit
import fr.medicapp.medicapp.ui.notifications.NotificationsMainMenu
import fr.medicapp.medicapp.ui.notifications.TestNotification
import fr.medicapp.medicapp.ui.prescription.EditPrescription
import fr.medicapp.medicapp.ui.prescription.Prescription
import fr.medicapp.medicapp.ui.prescription.PrescriptionMainMenu
import fr.medicapp.medicapp.ui.prescription.TestConsultation
import fr.medicapp.medicapp.ui.prescription.TestOrdonnance
import fr.medicapp.medicapp.ui.sideeffectsdiary.SED
import fr.medicapp.medicapp.ui.sideeffectsdiary.SEDEdit
import fr.medicapp.medicapp.ui.sideeffectsdiary.SEDMainMenu
import fr.medicapp.medicapp.viewModel.SharedAddPrescriptionViewModel
import fr.medicapp.medicapp.viewModel.SharedSideEffectViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.S)
fun NavGraphBuilder.notificationNavGraph(
    navController: NavHostController
) {
    navigation(
        route = Graph.NOTIFICATION,
        startDestination = SideEffectRoute.Main.route,
    ) {
        composable(route = NotificationRoute.Main.route) {
            val db = AppDatabase.getInstance(LocalContext.current)
            val repositorySideEffect = SideEffectRepository(db.sideEffectDAO())
            val repositoryTreatment = TreatmentRepository(db.treatmentDAO())

            var result: MutableList<SideEffect> = mutableListOf()
            Thread {
                val sideEffectEntityTmp = repositorySideEffect.getAll()

                val sideEffects = sideEffectEntityTmp.map {
                    val treatmentTmp = repositoryTreatment.getOne(it.medicament)
                    val sideEffectTmp = it.toSideEffect()
                    sideEffectTmp.medicament = treatmentTmp
                    sideEffectTmp
                }

                result.clear()
                result.addAll(sideEffects)

                result.forEach {
                    Log.d("TAG", it.toString())
                }
            }.start()

            val sideEffect = remember {
                result
            }

            NotificationsMainMenu(
                notifications = listOf()
            )
        }

        composable(route = NotificationRoute.ShowNotification.route) {
            val id = it.arguments?.getString("id") ?: return@composable
            val db = AppDatabase.getInstance(LocalContext.current)
            val repositorySideEffect = SideEffectRepository(db.sideEffectDAO())
            val repositoryTreatment = TreatmentRepository(db.treatmentDAO())

            var result: MutableList<SideEffect> = mutableListOf()

            Thread {
                val sideEffectEntityTmp = repositorySideEffect.getOne(id)
                val treatmentTmp = repositoryTreatment.getOne(sideEffectEntityTmp.medicament)
                val sideEffectTmp = sideEffectEntityTmp.toSideEffect()
                sideEffectTmp.medicament = treatmentTmp
                result.clear()
                result.add(sideEffectTmp)
            }.start()

            val sideEffect = remember {
                result
            }

            if (sideEffect != null) {
                Notifications(
                    notifications = TestNotification(
                        "",
                        "",
                        mutableListOf(),
                        mutableListOf()
                    )
                )
            }
        }

        composable(route = NotificationRoute.AddNotification.route) {
            val viewModel =
                it.sharedViewModel<SharedSideEffectViewModel>(navController = navController)
            val state by viewModel.sharedState.collectAsStateWithLifecycle()

            val db = AppDatabase.getInstance(LocalContext.current)
            val repositorySideEffect = SideEffectRepository(db.sideEffectDAO())
            val repositoryTreatment = TreatmentRepository(db.treatmentDAO())

            var result: MutableList<TreatmentEntity> = mutableListOf()

            Thread {
                result.clear()
                result.addAll(repositoryTreatment.getAll())
            }.start()

            val treatments = remember {
                result
            }

            NotificationsEdit(
                notification = TestNotification(
                    "",
                    "",
                    mutableListOf(),
                    mutableListOf()
                ),
            ) {

            }
        }
    }
}

sealed class NotificationRoute(val route: String) {
    object Main : NotificationRoute(route = "main_notification")
    object ShowNotification : NotificationRoute(route = "show_notification")
    object AddNotification : NotificationRoute(route = "add_notification")
}