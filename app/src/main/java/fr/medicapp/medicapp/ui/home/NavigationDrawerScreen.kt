package fr.medicapp.medicapp.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import fr.medicapp.medicapp.R
import fr.medicapp.medicapp.ui.navigation.HomeNavGraph
import fr.medicapp.medicapp.ui.theme.ThemeColorScheme
import kotlinx.coroutines.launch

/**
 * Écran avec tiroir de navigation de l'application MedicApp.
 *
 * Cet écran contient un tiroir de navigation qui permet à l'utilisateur de naviguer entre les différents écrans de l'application.
 * Le tiroir de navigation contient les éléments suivants :
 * - Accueil
 * - Mes traitements
 * - Journal des effets
 * - Gérer notifications
 *
 * @param navController Le contrôleur de navigation pour naviguer entre les différents écrans.
 */
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerScreen(
    navController: NavHostController = rememberNavController(),
    onThemeChange: (ThemeColorScheme) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val screens = listOf(
        NavigationDrawerRoute.Home,
        NavigationDrawerRoute.Prescriptions,
        NavigationDrawerRoute.Messages,
        NavigationDrawerRoute.Notifications
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val navigationDrawerDestination = screens.find { it.route == currentDestination?.route }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = RoundedCornerShape(10.dp)
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                screens.forEach { screen ->
                    NavigationDrawerItem(
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = screen.color.copy(alpha = 0.4f),
                        ),
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
                                drawerState.close()
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }
                            }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                        shape = RoundedCornerShape(10.dp)
                    )
                }
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Image(
                            painter = painterResource(
                                id = navigationDrawerDestination?.logo
                                    ?: R.drawable.medicapp_eu_green
                                //id = R.drawable.medicapp_eu_purple
                            ),
                            contentDescription = "MedicApp",
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    },
                    /*actions = {
                        IconButton(onClick = {  }) {
                            Icon(
                                imageVector = Icons.Filled.Notifications,
                                contentDescription = "Notifications"
                            )
                        }
                    }*/
                )
            },
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
}

/**
 * Prévisualisation de l'écran avec tiroir de navigation.
 *
 * Cette prévisualisation permet de voir à quoi ressemble l'écran avec tiroir de navigation sans avoir à lancer l'application.
 */
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun NavigationDrawerScreenPreview() {
    NavigationDrawerScreen(onThemeChange = {})
}
