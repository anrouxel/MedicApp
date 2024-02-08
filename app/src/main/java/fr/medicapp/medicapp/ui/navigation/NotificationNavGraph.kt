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
import fr.medicapp.medicapp.ui.screen.notification.NotificationEdit
import fr.medicapp.medicapp.ui.screen.notification.NotificationHome
import fr.medicapp.medicapp.ui.theme.EUPurpleColorShema
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
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
            onThemeChange(EUYellowColorShema)
            val store = ObjectBox.getInstance(LocalContext.current)

            val notificationStore = store.boxFor(NotificationEntity::class.java)

            val notification = remember { notificationStore.all.map { it.convert() } }

            NotificationHome(
                notifications = notification,
                onAddNotificationClick = {
                    navController.navigate(NotificationRoute.AddNotification.route)
                },
            )
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
            onThemeChange(EUYellowColorShema)
            val viewModel =
                it.sharedViewModel<SharedNotificationViewModel>(navController = navController)
            val state by viewModel.sharedState.collectAsStateWithLifecycle()

            var context = LocalContext.current

            val store = ObjectBox.getInstance(context)

            val notificationStore = store.boxFor(NotificationEntity::class.java)

            NotificationEdit()
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
