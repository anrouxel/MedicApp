package fr.medicapp.medicapp.ui.notifications

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.medicapp.medicapp.entity.SideEffectEntity
import fr.medicapp.medicapp.ui.theme.EURed100
import fr.medicapp.medicapp.ui.theme.EURed80
import fr.medicapp.medicapp.ui.theme.EUYellow110
import fr.medicapp.medicapp.ui.theme.EUYellow120
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Notifications(
    notifications: TestNotification
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                ),
                title = {
                    Text(
                        "Gérer les notifications",
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                        .weight(1f)
                ) {
                    Button(
                        onClick = {
                            //onClose() // à décommenter
                        },
                        shape = RoundedCornerShape(20),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = EURed100,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(3f)
                    ) {
                        Text(
                            text = "Annuler",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.weight(0.3f))

                    Button(
                        onClick = {
                            //TODO
                        },
                        shape = RoundedCornerShape(20),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = EURed100,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(3f)
                    ) {
                        Row() {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "",
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "Supprimer",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            /*FloatingActionButton(
                onClick = { },
                containerColor = EUYellow120
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "",
                    tint = Color.White
                )
            }*/
        }
    ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                ElevatedCard(
                    onClick = { /*TODO*/ },
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = 50.dp),
                    colors =
                    CardDefaults.cardColors(
                        containerColor = EUYellow110,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                    ) {
                        Text(
                            text = "Médicament :",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold

                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            notifications.medicationName,
                            fontSize = 18.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                ElevatedCard(
                    onClick = { /*TODO*/ },
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = 75.dp),
                    colors =
                    CardDefaults.cardColors(
                        containerColor = EUYellow110,
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
                            text = "Fréquence de rappel :",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold

                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            notifications.frequency,
                            fontSize = 18.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                var heuresSize = notifications.hours.size
                var heuresCard = if (heuresSize > 5) 115 else heuresSize * 18

                ElevatedCard(
                    onClick = { /*TODO*/ },
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = 77.dp + (heuresCard).dp),
                    colors =
                    CardDefaults.cardColors(
                        containerColor = EUYellow110,
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
                            text = "Horaires de rappel :",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold

                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        if (heuresSize > 5) {
                            for (i in notifications.hours.slice(0..4)) {
                                Text(
                                    "- $i",
                                    fontSize = 18.sp
                                )
                            }
                            Text(
                                "Et plus...",
                                fontSize = 18.sp
                            )
                        } else {
                            for (i in notifications.hours) {
                                Text(
                                    "- $i",
                                    fontSize = 18.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun NotificationsPreview() {
    var notif = TestNotification(
        "Doliprane",
        "Tous les jours",
        mutableListOf(),
        mutableListOf()
    )
    // var se = listOf<TestSideEffect>()
    Notifications(notif)
}
