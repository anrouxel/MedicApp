package fr.medicapp.medicapp.ui.notifications

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.medicapp.medicapp.entity.NotificationEntity
import fr.medicapp.medicapp.ui.notifications.NotificationsEdit.getFrenchDayOfWeek
import fr.medicapp.medicapp.ui.theme.EURed100
import fr.medicapp.medicapp.ui.theme.EUYellow100
import fr.medicapp.medicapp.ui.theme.EUYellow110
import fr.medicapp.medicapp.ui.theme.EUYellow120
import fr.medicapp.medicapp.ui.theme.EUYellow140
import java.time.DayOfWeek

/**
 * Cette fonction affiche les notifications pour un médicament spécifique.
 *
 * @param notifications La liste des notifications à afficher.
 * @param onClose La fonction à exécuter lorsque l'utilisateur ferme les notifications.
 * @param onRemove La fonction à exécuter lorsque l'utilisateur supprime une notification.
 */
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Notifications(
    notification: NotificationEntity,
    onClose: () -> Unit = {},
    onRemove: () -> Unit = {}
) {
    var darkmode: Boolean = isSystemInDarkTheme()
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
                            onClose()
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
                            onRemove()
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
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(10.dp)
        ) {
            ElevatedCard(
                onClick = { },
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(10.dp),
                ) {
                    Text(
                        text = "Médicament :",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold

                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        notification.treatment.target.medication.target.name,
                        fontSize = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            ElevatedCard(
                onClick = { },
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
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()

                    ) {

                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            DayOfWeek.values().forEachIndexed { index, dayOfWeek ->
                                val checked = notification.frequency.contains(dayOfWeek)

                                val tint by animateColorAsState(if (checked) EUYellow120 else EUYellow100)
                                val textColor = if (checked) Color.White else EUYellow140
                                IconToggleButton(
                                    checked = checked,
                                    onCheckedChange = {},
                                    enabled = false,
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .border(1.dp, EUYellow120, CircleShape)
                                        .background(tint)

                                ) {
                                    Text(
                                        getFrenchDayOfWeek(dayOfWeek).take(2),
                                        color = textColor
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            ElevatedCard(
                onClick = { },
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
                        .fillMaxWidth()
                        .padding(10.dp),
                ) {
                    Text(
                        text = "Horaires de rappel :",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold

                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Log.d("heuresSize", notification.toString())
                    for (i in notification.hours.indices) {
                        Text(
                            "- ${notification.hours[i]}h${if (notification.minutes[i] < 9) "0" + notification.minutes[i] else notification.minutes[i]}",
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}

/**
 * Cette fonction affiche un aperçu des notifications.
 */
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun NotificationsPreview() {
    Notifications(NotificationEntity())
}
