package fr.medicapp.medicapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.R
import fr.medicapp.medicapp.ui.theme.CaribbeanGreen500
import fr.medicapp.medicapp.ui.theme.CaribbeanGreen600

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home() {
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
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = "Notifications"
                        )
                    };
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "Account"
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Home"
                        )
                    }
                );
                NavigationBarItem(
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Medication,
                            contentDescription = "Ordonnances"
                        )
                    },
                );
                NavigationBarItem(
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.MedicalInformation,
                            contentDescription = "Medicaments"
                        )
                    }
                );
                NavigationBarItem(
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Message,
                            contentDescription = "Messagerie"
                        )
                    }
                )
            }
        }
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
                    containerColor = CaribbeanGreen600,
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
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CaribbeanGreen500
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
                    containerColor = CaribbeanGreen500
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Signaler un effet indesirable")
            }
        }
    }
}

@Preview
@Composable
private fun HomePreview() {
    Home()
}