package fr.medicapp.medicapp.ui.screen.root

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import fr.medicapp.medicapp.ui.navigation.HomeNavGraph
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.ThemeColorScheme
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootScreen(
    navController: NavHostController = rememberNavController(),
    theme: ThemeColorScheme,
    onThemeChange: (ThemeColorScheme) -> Unit,
) {
    val scope = rememberCoroutineScope()

    val screens = listOf(
        RootRoute.RootRouteHomeRoute,
        RootRoute.RootRoutePrescriptionRoute,
        RootRoute.RootRouteSideEffectRoute,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Image(
                        painter = painterResource(
                            id = theme.icon
                        ),
                        contentDescription = "MedicApp",
                    )
                },
                navigationIcon = {
                    if (screens.none { it.route == currentDestination?.parent?.route || it.route == currentDestination?.route }) {
                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                },
            )
        },
        bottomBar = {
            NavigationBar {
                screens.forEach { screen ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = screen.icon,
                                contentDescription = screen.title
                            )
                        },
                        label = {
                            Text(text = screen.title)
                        },
                        selected = currentDestination?.hierarchy?.any {
                            it.route == screen.route
                        } == true,
                        onClick = {
                            scope.launch {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }
                            }
                        },
                    )
                }
            }
        }
    ) { innerPadding ->
        Row(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            HomeNavGraph(
                navController = navController,
                onThemeChange = onThemeChange
            )
        }
    }
}

/**
 * Prévisualisation de l'écran avec tiroir de navigation.
 *
 * Cette prévisualisation permet de voir à quoi ressemble l'écran avec tiroir de navigation sans avoir à lancer l'application.
 */
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun RootPreview() {
    RootScreen(theme = EUYellowColorShema, onThemeChange = {})
}
