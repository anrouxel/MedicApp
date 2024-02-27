package fr.medicapp.medicapp.ui.navigation

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fr.medicapp.medicapp.ui.screen.root.RootScreen
import fr.medicapp.medicapp.ui.theme.ThemeColorScheme

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
    isUser: Boolean,
    isDownload: Boolean,
    context: Context
) {

    /**
     * Définit le hôte de navigation pour le graphe de navigation racine.
     */

    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = if (isUser && isDownload) Graph.HOME else Graph.USER,
    ) {
        /**
         * Composable pour l'écran d'accueil.
         */
        composable(route = Graph.HOME) {
            RootScreen(
                theme = theme,
            )
        }

        userNavGraph(navController, isUser, isDownload, context)
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

    /**
     * Route pour l'écran de chargement et le téléchargement des données
     */
    const val LOADING = "loading_graph"
}
