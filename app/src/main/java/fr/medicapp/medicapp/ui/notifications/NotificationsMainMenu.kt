package fr.medicapp.medicapp.ui.notifications

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import fr.medicapp.medicapp.model.Notification
import fr.medicapp.medicapp.ui.notifications.NotificationsEdit.getFrenchDayOfWeek
import fr.medicapp.medicapp.ui.theme.EUYellow100
import fr.medicapp.medicapp.ui.theme.EUYellow110
import fr.medicapp.medicapp.ui.theme.EUYellow120

/**
 * Cette fonction affiche le menu principal des notifications.
 *
 * @param notifications La liste des notifications à afficher.
 * @param onNotification La fonction à exécuter lorsque l'utilisateur sélectionne une notification.
 * @param addNotification La fonction à exécuter lorsque l'utilisateur ajoute une notification.
 */
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsMainMenu(
    notifications : MutableList<Notification>,
    onNotification : (String) -> Unit = {},
    addNotification : () -> Unit = {}
) {
    var darkmode : Boolean = isSystemInDarkTheme()
    val context = LocalContext.current
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Unspecified,
                    titleContentColor = if (darkmode) Color.White else Color.Black,
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
                onClick = addNotification,
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
                        onClick = {
                            onNotification(i.id)
                        },
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        colors =
                        CardDefaults.cardColors(
                            containerColor = EUYellow110,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(10.dp),
                        ) {
                            Text(
                                text = i.medicationName!!.medication!!.name,
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
                                var text = ""
                                i.frequency.forEachIndexed { index, dayOfWeek ->
                                    text+= getFrenchDayOfWeek(dayOfWeek)
                                    if (index < i.frequency.size-1) {
                                        text+=", "
                                    }
                                }
                                Text(
                                    text,
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
                                    i.hours.zip(i.minutes).map { (heure, minute) -> "${heure}h${if (minute<9) "0$minute" else minute }" }.toString().replace("[", "").replace("]", ""),
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
                        "Vous n'avez pas créé de notifications.\nPour en créer une, cliquez sur\nle bouton en bas.",
                        color = EUYellow100,
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

/**
 * Cette fonction affiche un aperçu du menu principal des notifications.
 */
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun NotificationsMainMenuPreview() {
    NotificationsMainMenu(mutableListOf()) {}
}