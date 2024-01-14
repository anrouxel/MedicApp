package fr.medicapp.medicapp.ui.navigation

import android.app.NotificationManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.coldtea.smplr.smplralarm.alarmNotification
import de.coldtea.smplr.smplralarm.channel
import de.coldtea.smplr.smplralarm.smplrAlarmCancel
import de.coldtea.smplr.smplralarm.smplrAlarmSet
import fr.medicapp.medicapp.R
import fr.medicapp.medicapp.database.AppDatabase
import fr.medicapp.medicapp.model.Notification
import fr.medicapp.medicapp.model.Treatment
import fr.medicapp.medicapp.repository.MedicationRepository
import fr.medicapp.medicapp.repository.NotificationRepository
import fr.medicapp.medicapp.repository.TreatmentRepository
import fr.medicapp.medicapp.ui.notifications.Notifications
import fr.medicapp.medicapp.ui.notifications.NotificationsEdit
import fr.medicapp.medicapp.ui.notifications.NotificationsMainMenu
import fr.medicapp.medicapp.viewModel.SharedNotificationViewModel
import java.time.DayOfWeek

/**
 * Cette fonction construit le graphe de navigation pour les notifications.
 *
 * @param navController Le contrôleur de navigation.
 */
@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.notificationNavGraph(
    navController: NavHostController
) {

    /**
     * Définit la navigation pour le graphe de notification.
     */
    navigation(
        route = Graph.NOTIFICATION,
        startDestination = SideEffectRoute.Main.route,
    ) {

        /**
         * Composable pour la route principale de notification.
         */
        composable(route = NotificationRoute.Main.route) {
            val db = AppDatabase.getInstance(LocalContext.current)
            val repositoryNotification = NotificationRepository(db.notificationDAO())
            val repositoryTreatment = TreatmentRepository(db.treatmentDAO())
            val repositoryMedication = MedicationRepository(db.medicationDAO())

            var result: MutableList<Notification> = mutableListOf()
            Thread {
                val notificationEntityTmp = repositoryNotification.getAll()

                val notifications = notificationEntityTmp.map {
                    val treatmentTmp = repositoryTreatment.getOne(it.medicationName)
                        .toTreatment(repositoryMedication)
                    val notificationTmp = it.toNotification()
                    notificationTmp.medicationName = treatmentTmp
                    notificationTmp
                }

                result.clear()
                result.addAll(notifications)

                result.forEach {
                    Log.d("TAG", it.toString())
                }
            }.start()

            val notification = remember {
                result
            }

            NotificationsMainMenu(
                notifications = notification,
                onNotification = { id ->
                    navController.navigate(
                        NotificationRoute.ShowNotification.route.replace(
                            "{id}",
                            id
                        )
                    )
                },
                addNotification = {
                    navController.navigate(NotificationRoute.AddNotification.route)
                },
            )
        }

        /**
         * Composable pour afficher une notification spécifique.
         */
        composable(route = NotificationRoute.ShowNotification.route) {
            val id = it.arguments?.getString("id") ?: return@composable
            val db = AppDatabase.getInstance(LocalContext.current)
            val repositoryNotification = NotificationRepository(db.notificationDAO())
            val repositoryTreatment = TreatmentRepository(db.treatmentDAO())
            val repositoryMedication = MedicationRepository(db.medicationDAO())

            var result: MutableList<Notification> = mutableListOf()

            Thread {
                result.clear()
                val notificationEntityTmp = repositoryNotification.getOne(id)
                if (notificationEntityTmp != null) {
                    val treatmentTmp =
                        repositoryTreatment.getOne(notificationEntityTmp.medicationName)
                            .toTreatment(repositoryMedication)
                    val notificationTmp = notificationEntityTmp.toNotification()
                    notificationTmp.medicationName = treatmentTmp
                    result.add(notificationTmp)
                }
            }.start()

            val notification = remember {
                result
            }

            var context = LocalContext.current

            if (notification != null) {
                Notifications(
                    notifications = notification,
                    onClose = {
                        navController.navigate(NotificationRoute.Main.route) {
                            popUpTo(NotificationRoute.ShowNotification.route) {
                                inclusive = true
                            }
                        }
                    },
                    onRemove = {
                        notification.forEach {
                            it.alarms.forEach { alarm ->
                                smplrAlarmCancel(context) {
                                    requestCode { alarm }
                                }
                            }
                        }

                        Thread {
                            notification.map { side -> side.toEntity() }.forEach { side ->
                                repositoryNotification.delete(side)
                            }
                        }.start()
                        navController.navigate(NotificationRoute.Main.route) {
                            popUpTo(NotificationRoute.ShowNotification.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }

        /**
         * Composable pour ajouter une nouvelle notification.
         */
        composable(route = NotificationRoute.AddNotification.route) {
            val viewModel =
                it.sharedViewModel<SharedNotificationViewModel>(navController = navController)
            val state by viewModel.sharedState.collectAsStateWithLifecycle()

            val db = AppDatabase.getInstance(LocalContext.current)
            val repository = NotificationRepository(db.notificationDAO())
            val repositoryTreatment = TreatmentRepository(db.treatmentDAO())
            val repositoryMedication = MedicationRepository(db.medicationDAO())

            var result: MutableList<Treatment> = mutableListOf()

            Thread {
                result.clear()
                result.addAll(
                    repositoryTreatment.getWithNotification()
                        .map { it.toTreatment(repositoryMedication) }.toMutableList()
                )
            }.start()

            val treatments = remember {
                result
            }

            var context = LocalContext.current

            NotificationsEdit(
                notification = state,
                treatments = treatments,
                onConfirm = {
                    for (i in 0 until state.hours.size) {
                        val uuid: Int = smplrAlarmSet(context) {
                            //isActive { state.medicationName!!.notification!! }
                            hour { state.hours[i] }
                            min { state.minutes[i] }
                            weekdays {
                                state.frequency.forEach { dayOfWeek ->
                                    when (dayOfWeek) {
                                        DayOfWeek.MONDAY -> monday()
                                        DayOfWeek.TUESDAY -> tuesday()
                                        DayOfWeek.WEDNESDAY -> wednesday()
                                        DayOfWeek.THURSDAY -> thursday()
                                        DayOfWeek.FRIDAY -> friday()
                                        DayOfWeek.SATURDAY -> saturday()
                                        DayOfWeek.SUNDAY -> sunday()
                                    }
                                }
                            }
                            notification {
                                alarmNotification {
                                    smallIcon { R.drawable.medicapp_eu_blue }
                                    title { "Rappel de prise de médicament" }
                                    message { "C'est l'heure ! Vous devez prendre ${state.medicationName!!.medication!!.name}" }
                                    bigText { "C'est l'heure ! Vous devez prendre ${state.medicationName!!.medication!!.name}" }
                                    autoCancel { false }
                                }
                            }
                            notificationChannel {
                                channel {
                                    importance { NotificationManager.IMPORTANCE_HIGH }
                                    showBadge { true }
                                    name { "Canal de rappel de médicaments" }
                                    description { "Ce canal de notification est utilisé pour les rappels" }
                                }
                            }
                        }

                        state.alarms.add(uuid)
                    }
                    Thread {
                        repository.add(state.toEntity())
                    }.start()
                    navController.navigate(NotificationRoute.Main.route) {
                        popUpTo(NotificationRoute.AddNotification.route) {
                            inclusive = true
                        }
                    }
                },
                onCancel = {
                    navController.navigate(NotificationRoute.Main.route) {
                        popUpTo(NotificationRoute.AddNotification.route) {
                            inclusive = true
                        }
                    }
                },
            )
        }
    }
}

/**
 * Cette classe scellée définit les différentes routes pour les notifications.
 */
sealed class NotificationRoute(val route: String) {
    /**
     * Route pour la page principale des notifications.
     */
    object Main : NotificationRoute(route = "main_notification")

    /**
     * Route pour afficher une notification spécifique.
     */
    object ShowNotification : NotificationRoute(route = "show_notification/{id}")

    /**
     * Route pour ajouter une nouvelle notification.
     */
    object AddNotification : NotificationRoute(route = "add_notification")
}
