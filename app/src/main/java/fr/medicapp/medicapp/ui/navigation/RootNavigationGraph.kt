package fr.medicapp.medicapp.ui.navigation

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fr.medicapp.medicapp.database.repositories.UserRepository
import fr.medicapp.medicapp.database.repositories.medication.MedicationRepository
import fr.medicapp.medicapp.ui.screen.root.RootScreen
import fr.medicapp.medicapp.ui.theme.ThemeColorScheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Cette fonction construit le graphe de navigation racine.
 *
 * @param navController Le contrôleur de navigation.
 */
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun RootNavGraph(
    navController: NavHostController,
    theme: ThemeColorScheme,
    onThemeChange: (ThemeColorScheme) -> Unit,
    isUser: Boolean
) {

    /**
     * Définit le hôte de navigation pour le graphe de navigation racine.
     */

    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = if (isUser) Graph.HOME else Graph.USER,
    ) {
        /**
         * Composable pour l'écran d'accueil.
         */
        composable(route = Graph.HOME) {
            RootScreen(
                theme = theme,
                onThemeChange = onThemeChange
            )
        }

        userNavGraph(navController, onThemeChange)
    }
}

/**
 * Cette classe scellée définit les différentes routes pour le graphe de navigation racine.
 */
object Graph {
    /**
     * Route pour le graphe de navigation racine.
     */
    const val ROOT = "root_graph"

    /**
     * Route pour le graphe de navigation d'authentification.
     */
    const val USER = "user_graph"

    /**
     * Route pour l'écran d'accueil.
     */
    const val HOME = "home_graph"
}
