package fr.medicapp.medicapp.ui.notifications

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.CalendarMonth
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.medicapp.medicapp.ui.sideeffectsdiary.TestSideEffect
import fr.medicapp.medicapp.ui.theme.EUBlue100
import fr.medicapp.medicapp.ui.theme.EURed100
import fr.medicapp.medicapp.ui.theme.EURed80
import fr.medicapp.medicapp.ui.theme.EUYellow100
import fr.medicapp.medicapp.ui.theme.EUYellow110
import fr.medicapp.medicapp.ui.theme.EUYellow120

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsMainMenu(
    notifications : List<TestNotification>
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

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {  },
                containerColor = EUYellow120
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "",
                    tint = Color.White
                )
            }

        }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(10.dp)
        ) {
            if (notifications.isNotEmpty()){
                for (i in notifications) {
                    ElevatedCard(
                        onClick = { /*TODO*/ },
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(height = 105.dp),
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
                                text = i.medicationName,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold

                            )

                            Spacer(modifier = Modifier.height(5.dp))

                            Row() {
                                Icon(
                                    imageVector = Icons.Filled.CalendarMonth,
                                    contentDescription = "",
                                    tint = Color.White
                                )

                                Spacer(modifier = Modifier.width(5.dp))

                                Text(
                                    i.frequency,
                                    fontSize = 15.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(5.dp))

                            Row() {
                                Icon(
                                    imageVector = Icons.Filled.Alarm,
                                    contentDescription = "",
                                    tint = Color.White
                                )

                                Spacer(modifier = Modifier.width(5.dp))

                                Text(
                                    i.hours.toString().replace("[", "").replace("]", ""),
                                    fontSize = 15.sp
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentHeight(align = Alignment.CenterVertically)
                ) {
                    Text(
                        "Vous n'avez pas constaté d'effets secondaires.\nPour en ajouter un, cliquez sur\nle bouton en bas.",
                        color = EUBlue100,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        fontStyle = FontStyle.Italic
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NotificationsMainMenuPreview() {
    var notif = listOf(
        TestNotification(
            "Doliprane",
            "Tous les jours",
            mutableListOf(8, 12, 18),
            mutableListOf(0, 0, 0)
        )
    )
    //var se = listOf<TestSideEffect>() /* TODO */
    NotificationsMainMenu(notif)
}