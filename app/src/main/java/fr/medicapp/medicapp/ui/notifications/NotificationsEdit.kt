package fr.medicapp.medicapp.ui.notifications

import android.app.AlarmManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.medicapp.medicapp.model.Notification
import fr.medicapp.medicapp.model.Treatment
import fr.medicapp.medicapp.ui.notifications.NotificationsEdit.getFrenchDayOfWeek
import fr.medicapp.medicapp.ui.prescription.EditPrescription.AddButton
import fr.medicapp.medicapp.ui.prescription.SearchDialog
import fr.medicapp.medicapp.ui.prescription.TimePickerModal
import fr.medicapp.medicapp.ui.theme.EUGreen100
import fr.medicapp.medicapp.ui.theme.EUGreen40
import fr.medicapp.medicapp.ui.theme.EURed100
import fr.medicapp.medicapp.ui.theme.EUYellow100
import fr.medicapp.medicapp.ui.theme.EUYellow110
import fr.medicapp.medicapp.ui.theme.EUYellow120
import fr.medicapp.medicapp.ui.theme.EUYellow140
import fr.medicapp.medicapp.ui.theme.EUYellow20
import fr.medicapp.medicapp.ui.theme.EUYellow40
import java.time.DayOfWeek

/**
 * Cette fonction permet de modifier les notifications pour un médicament spécifique.
 *
 * @param notification La notification à modifier.
 * @param onConfirm La fonction à exécuter lorsque l'utilisateur confirme les modifications.
 * @param onCancel La fonction à exécuter lorsque l'utilisateur annule les modifications.
 * @param treatments La liste des traitements disponibles pour la notification.
 */
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsEdit(
    notification: Notification,
    onConfirm: () -> Unit,
    onCancel: () -> Unit = {},
    treatments: MutableList<Treatment> = mutableListOf()
) {
    var darkmode: Boolean = isSystemInDarkTheme()
    val context = LocalContext.current
    val alarmManager = context.getSystemService(AlarmManager::class.java)

    var medicationName by remember {
        mutableStateOf(
            notification.medicationName?.medication?.name ?: ""
        )
    }
    var frequency by remember { mutableStateOf(notification.frequency) }

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
                containerColor = Color.Unspecified
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                        .weight(1f)
                ) {
                    Button(
                        onClick = {
                            onCancel()
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
                            if (medicationName != "" && frequency.size > 0 && notification.hours.size > 0 && notification.hours.all { it != null } && notification.minutes.size > 0 && notification.minutes.all { it != null }) {
                                onConfirm()
                            } else {
                                errorDialogOpen.value = true
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

        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            ElevatedCard(
                onClick = { /*TODO*/ },
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
                        .fillMaxSize()
                        .padding(10.dp),
                ) {
                    var treatmentOpen by remember { mutableStateOf(false) }

                    if (treatmentOpen) {
                        SearchDialog(
                            options = treatments.map { it.toOptionDialog() },
                            cardColor = EUYellow40,
                            selectedCardColor = EUYellow100,
                            onDismiss = {
                                treatmentOpen = false
                            },
                            onValidate = { option ->
                                notification.medicationName = treatments.find { it.id == option.id }
                                medicationName = option.title
                                treatmentOpen = false
                            }
                        )
                    }

                    OutlinedTextField(
                        enabled = false,
                        value = medicationName,
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        ),
                        onValueChange = {},
                        label = { Text("Nom du médicament") },
                        shape = RoundedCornerShape(20),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedLabelColor = Color.White,
                            unfocusedLabelColor = Color.White,
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White,
                            disabledBorderColor = Color.White,
                            disabledLabelColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                treatmentOpen = true
                            }
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
                                val checked = frequency.contains(dayOfWeek)

                                val tint by animateColorAsState(if (checked) EUYellow120 else EUYellow100)
                                val textColor = if (checked) Color.White else EUYellow140
                                IconToggleButton(
                                    checked = checked,
                                    onCheckedChange = { checked ->
                                        if (checked) {
                                            frequency.add(dayOfWeek)
                                        } else {
                                            frequency.remove(dayOfWeek)
                                        }
                                    },
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .border(1.dp, EUYellow120, CircleShape)
                                        .background(tint)

                                ) {
                                    Text(getFrenchDayOfWeek(dayOfWeek).take(2), color = textColor)
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            ElevatedCard(
                onClick = {  },
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 115.dp + (notification.hours.size * 63 + notification.hours.size).dp),
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
                        text = "Horaires de rappel (${notification.hours.size}) :",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    for (i in 0 until notification.hours.size) {
                        var hours = remember { mutableStateOf(notification.hours[i]) }
                        var minutes = remember { mutableStateOf(notification.minutes[i]) }

                        LaunchedEffect(notification.hours[i]) {
                            hours.value = notification.hours[i]
                            minutes.value = notification.minutes[i]
                        }

                        var frequencyTimeOpen = remember { mutableStateOf(false) }
                        var frequencyTimeState = rememberTimePickerState(
                            is24Hour = true,
                        )

                        if (frequencyTimeOpen.value) {
                            TimePickerModal(
                                state = frequencyTimeState,
                                clockBackgroundColor = EUYellow20,
                                selectorColor = EUYellow100,
                                timeSelectorSelectedContainerColor = EUYellow40,
                                timeSelectorUnselectedContainerColor = EUYellow20,
                                onDismissRequest = {
                                    frequencyTimeOpen.value = false
                                },
                                onConfirm = {
                                    notification.hours[i] = frequencyTimeState.hour
                                    notification.minutes[i] = frequencyTimeState.minute
                                    frequencyTimeOpen.value = false
                                }
                            )
                        }

                        OutlinedTextField(
                            modifier = Modifier
                                .clickable {
                                    frequencyTimeOpen.value = true
                                }
                                .fillMaxWidth(),
                            enabled = false,
                            value = if (notification.hours[i] != null && notification.minutes[i] != null) "${notification.hours[i]}h${if (notification.minutes[i] < 9) "0" + notification.minutes[i] else notification.minutes[i]}" else "",
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                                color = Color.White
                            ),
                            onValueChange = { },
                            shape = RoundedCornerShape(20),
                            trailingIcon = {
                                IconButton(onClick = {
                                    notification.hours.removeAt(i)
                                    notification.minutes.removeAt(i)
                                }) {
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
                                disabledBorderColor = Color.White,
                                disabledLabelColor = Color.White,
                            )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    AddButton(
                        text = "Ajouter un horaire",
                        icone = Icons.Filled.Add,
                        color = EUYellow100,
                        onClick = {
                            notification.hours.add(0)
                            notification.minutes.add(0)
                        }
                    )
                }
            }
        }
    }
}

/**
 * Cette fonction affiche un aperçu de la page de modification des notifications.
 */
@RequiresApi(Build.VERSION_CODES.S)
@Preview(showBackground = true)
@Composable
private fun NotificationsEditPreview() {
    var notif = Notification(
        "Rappel doliprane",
        null,
        mutableListOf(),
        mutableListOf(5, 10, 15, 20),
        mutableListOf(0, 15, 30, 45)
    )
    NotificationsEdit(notif, {})
}