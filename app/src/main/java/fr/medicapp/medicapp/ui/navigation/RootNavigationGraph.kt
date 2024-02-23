package fr.medicapp.medicapp.ui.navigation

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fr.medicapp.medicapp.database.ObjectBox
import fr.medicapp.medicapp.database.entity.UserEntity
import fr.medicapp.medicapp.ui.screen.root.RootScreen
import fr.medicapp.medicapp.ui.theme.ThemeColorScheme

/**
 * Cette fonction construit le graphe de navigation racine.
 *
 * @param navController Le contrôleur de navigation.
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RootNavGraph(
    navController: NavHostController,
    theme: ThemeColorScheme,
    onThemeChange: (ThemeColorScheme) -> Unit,
) {

    /**
     * Définit le hôte de navigation pour le graphe de navigation racine.
     */
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = if (!isUserInDatabase(LocalContext.current)) Graph.USER else Graph.HOME,
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

private fun isUserInDatabase(context: Context): Boolean {
    val boxStore = ObjectBox.getInstance(context)
    val store = boxStore.boxFor(UserEntity::class.java)
    return store.all.isNotEmpty()
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
