package fr.medicapp.medicapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MedicalInformation
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.R
import fr.medicapp.medicapp.ui.theme.EUGreen100
import fr.medicapp.medicapp.ui.theme.EUGreen120
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home() {
    var showBottomSheet by remember { mutableStateOf(false) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = RoundedCornerShape(10.dp)
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Accueil"
                        )
                    },
                    label = {
                        Text(text = "Accueil")
                    },
                    selected = true,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                    shape = RoundedCornerShape(10.dp)
                )
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.MedicalInformation,
                            contentDescription = "Ordonnances"
                        )
                    },
                    label = {
                        Text(text = "Ordonnances")
                    },
                    selected = false,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Medication,
                            contentDescription = "Medicaments"
                        )
                    },
                    label = {
                        Text(text = "Medicaments")
                    },
                    selected = false,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Newspaper,
                            contentDescription = "Effets indesirables"
                        )
                    },
                    label = {
                        Text(text = "Effets indesirables")
                    },
                    selected = false,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Message,
                            contentDescription = "Messages"
                        )
                    },
                    label = {
                        Text(text = "Messages")
                    },
                    selected = false,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = "MedicApp"
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
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Filled.Notifications,
                                contentDescription = "Notifications"
                            )
                        };
                    }
                )
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(10.dp),
            ) {
                ElevatedCard(
                    onClick = { /*TODO*/ },
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = 200.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = EUGreen120,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                    ) {
                        Text(
                            text = "Bonjour, John Doe",
                        )
                    }
                }
                Button(
                    onClick = {
                        showBottomSheet = true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = EUGreen100
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "Ajouter une ordonnance")
                }
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = EUGreen100
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "Signaler un effet indesirable")
                }
            }
            Button(
                onClick = {
                    showBottomSheet = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = EUGreen100
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Ajouter une ordonnance")
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = EUGreen100
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Signaler un effet indesirable")
            }
            if (showBottomSheet) {
                AddPrescriptionModalBottomSheet(
                    showBottomSheet = showBottomSheet,
                    onDismissRequest = { showBottomSheet = false }
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomePreview() {
    Home()
}