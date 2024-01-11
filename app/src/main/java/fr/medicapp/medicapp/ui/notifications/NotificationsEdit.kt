package fr.medicapp.medicapp.ui.notifications

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getSystemService
import fr.medicapp.medicapp.MainActivity
import fr.medicapp.medicapp.ui.prescription.EditPrescription.AddButton
import fr.medicapp.medicapp.ui.theme.EUGreen100
import fr.medicapp.medicapp.ui.theme.EUGreen40
import fr.medicapp.medicapp.ui.theme.EURed100
import fr.medicapp.medicapp.ui.theme.EUYellow100
import fr.medicapp.medicapp.ui.theme.EUYellow110

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsEdit(
    notification: TestNotification,
    onConfirm: () -> Unit
) {
    val context = LocalContext.current
    val alarmManager = context.getSystemService(AlarmManager::class.java)

    var nomMedicament by remember { mutableStateOf(notification.nomMedicament) }

    var errorDialogOpen = remember { mutableStateOf(false) }

    if (errorDialogOpen.value) {
        AlertDialog(
            onDismissRequest = {
                errorDialogOpen.value = false
            },
            title = {
                Text("Erreur")
            },
            text = {
                Text("Veuillez remplir tous les champs")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        errorDialogOpen.value = false
                    }
                ) {
                    Text("OK")
                }
            }
        )
    }

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
                            //onCancel()
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
                            /*if (prescription.treatments.size > 0 && prescription.treatments.all { it.medication != "" && it.posology != "" && it.quantity != "" && it.renew != "" && it.duration != null }) {
                                onConfirm()
                            } else {
                                errorDialogOpen.value = true
                            }*/
                            if (alarmManager.canScheduleExactAlarms()){
                                val dateFormat = SimpleDateFormat("hhmm")
                                for (heure in notification.heures){
                                    val date = dateFormat.parse(heure)
                                    alarmManager.setExactAndAllowWhileIdle(
                                        AlarmManager.RTC_WAKEUP,
                                        date.toInstant().toEpochMilli(),
                                        TaskStackBuilder.create(context).run {
                                            addNextIntentWithParentStack(Intent(context,MainActivity::class.java))
                                            getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
                                        }
                                    )
                                }
                            }
                        },
                        enabled = true,
                        shape = RoundedCornerShape(20),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = EUGreen100,
                            contentColor = Color.White,
                            disabledContainerColor = EUGreen40,
                            disabledContentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(3f)
                    ) {
                        Text(
                            text = "Valider",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

        },
        floatingActionButton = {
            /*FloatingActionButton(
                onClick = {
                    // Vérification des champs
                    if (nomMedicament != "" && sideeffects.date != null && sideeffects.hour != null && sideeffects.minute != null && sideeffects.effetsConstates.size > 0 && sideeffects.effetsConstates.all { it != "" }) {
                        onConfirm()
                    } else {
                        errorDialogOpen.value = true
                    }
                },
                containerColor = EURed100
            ) {
                Icon(
                    imageVector = Icons.Filled.Save,
                    contentDescription = "",
                    tint = Color.White
                )
            }*/
        }
    ) {innerPadding ->
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
                    .height(height = 85.dp),
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
                    OutlinedTextField(
                        value = nomMedicament,
                        textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color= Color.White),
                        onValueChange = {
                            /*nomMedicament = it
                            sideeffects.medicament = it*/
                        },
                        label = { Text("Nom du médicament") },
                        shape = RoundedCornerShape(20),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedLabelColor = Color.White,
                            unfocusedLabelColor = Color.White,
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White,
                        ),
                        modifier = Modifier.fillMaxWidth()
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
                    .height(height = 115.dp),
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
                        .padding(10.dp)
                ) {
                    Text(
                        text = "Fréquence de rappel :",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(5.dp))


                    OutlinedTextField(
                        /*modifier = Modifier.clickable{
                            //
                        },*/
                        enabled = false,
                        value = "",
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            color = Color.White
                        ),
                        onValueChange = { },
                        label = { Text("Fréquence de rappel") },
                        shape = RoundedCornerShape(20),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedLabelColor = Color.White,
                            unfocusedLabelColor = Color.White,
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White,
                        )
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
                    .height(height = 115.dp + (notification.heures.size * 63 + notification.heures.size).dp),
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
                        text = "Horaires de rappel (${notification.heures.size}) :",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    for (i in 0 until notification.heures.size) {
                        var effetsConstates = remember { mutableStateOf(notification.heures[i]) }

                        LaunchedEffect(notification.heures[i]) {
                            effetsConstates.value = notification.heures[i]
                        }

                        OutlinedTextField(
                            value = effetsConstates.value,
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                                color = Color.White
                            ),
                            onValueChange = {
                                effetsConstates.value = it
                                notification.heures[i] = it
                            },
                            shape = RoundedCornerShape(20),
                            trailingIcon = {
                                IconButton(onClick = { notification.heures.drop(i) }) {
                                    Icon(
                                        imageVector = Icons.Filled.DeleteForever,
                                        contentDescription = "",
                                        tint = Color.White
                                    )
                                }
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedLabelColor = Color.White,
                                unfocusedLabelColor = Color.White,
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = Color.White,
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    AddButton(
                        text = "Ajouter un horaire",
                        icone = Icons.Filled.Add,
                        color = EUYellow100,
                        onClick = {
                            //sideeffects.effetsConstates.add("")
                        }
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.S)
@Preview(showBackground = true)
@Composable
private fun NotificationsEditPreview() {
    var notif = TestNotification(
        "Doliprane",
        "Tous les jours",
        mutableListOf("5h00", "10h00", "15h00")
    )
    //var se = listOf<TestSideEffect>()
    NotificationsEdit(notif, {})
}