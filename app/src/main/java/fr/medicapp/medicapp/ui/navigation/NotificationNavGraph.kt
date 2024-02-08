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
import fr.medicapp.medicapp.entity.NotificationEntity
import fr.medicapp.medicapp.ui.theme.ThemeColorScheme
import fr.medicapp.medicapp.viewModel.SharedNotificationViewModel

/**
 * Cette fonction construit le graphe de navigation pour les notifications.
 *
 * @param navController Le contrôleur de navigation.
 */
@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.notificationNavGraph(
    navController: NavHostController,
    onThemeChange: (ThemeColorScheme) -> Unit
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
            val store = ObjectBox.getInstance(LocalContext.current)

            val notificationStore = store.boxFor(NotificationEntity::class.java)

            val notification = remember { notificationStore.all }

            /*NotificationsMainMenu(
                notifications = notification,
                onNotification = { id ->
                    navController.navigate(
                        NotificationRoute.ShowNotification.route.replace(
                            "{id}",
                            id
                        )
                    )
                },
            ) {
                navController.navigate(NotificationRoute.AddNotification.route)
            }*/
        }

        /**
         * Composable pour afficher une notification spécifique.
         */
        composable(route = NotificationRoute.ShowNotification.route) {
            val id = it.arguments?.getString("id") ?: return@composable

            val store = ObjectBox.getInstance(LocalContext.current)

            val notificationStore = store.boxFor(NotificationEntity::class.java)

            val notification = remember { notificationStore.get(id.toLong()) }

            var context = LocalContext.current

            /*if (notification != null) {
                Notifications(
                    notification = notification,
                    onClose = {
                        navController.navigate(NotificationRoute.Main.route) {
                            popUpTo(NotificationRoute.ShowNotification.route) {
                                inclusive = true
                            }
                        }
                    }
                ) {
                    notification.alarms.forEach { alarm ->
                        smplrAlarmCancel(context) {
                            requestCode { alarm }
                        }
                    }

                    notificationStore.remove(id.toLong())

                    navController.navigate(NotificationRoute.Main.route) {
                        popUpTo(NotificationRoute.ShowNotification.route) {
                            inclusive = true
                        }
                    }
                }
            }*/
        }

        /**
         * Composable pour ajouter une nouvelle notification.
         */
        composable(route = NotificationRoute.AddNotification.route) {
            val viewModel =
                it.sharedViewModel<SharedNotificationViewModel>(navController = navController)
            val state by viewModel.sharedState.collectAsStateWithLifecycle()

            var context = LocalContext.current

            val store = ObjectBox.getInstance(context)

            val notificationStore = store.boxFor(NotificationEntity::class.java)

            /*NotificationsEdit(
                notification = state,
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
                                    message { "C'est l'heure ! Vous devez prendre ${state.treatment.target.medication.target.name}" }
                                    bigText { "C'est l'heure ! Vous devez prendre ${state.treatment.target.medication.target.name}" }
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

                    notificationStore.put(state)

                    navController.navigate(NotificationRoute.Main.route) {
                        popUpTo(NotificationRoute.AddNotification.route) {
                            inclusive = true
                        }
                    }
                },
            ) {
                navController.navigate(NotificationRoute.Main.route) {
                    popUpTo(NotificationRoute.AddNotification.route) {
                        inclusive = true
                    }
                }
            }*/
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
