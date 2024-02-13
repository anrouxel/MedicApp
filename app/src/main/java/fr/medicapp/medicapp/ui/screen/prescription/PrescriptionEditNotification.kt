package fr.medicapp.medicapp.ui.screen.prescription

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.medicapp.medicapp.model.Alarm
import fr.medicapp.medicapp.model.Notification
import fr.medicapp.medicapp.model.Prescription
import fr.medicapp.medicapp.ui.components.button.ReusableButton
import fr.medicapp.medicapp.ui.components.button.ReusableOutlinedTimePickerButton
import fr.medicapp.medicapp.ui.components.card.ReusableElevatedCard
import fr.medicapp.medicapp.ui.components.screen.Edit
import fr.medicapp.medicapp.ui.theme.EUPurpleColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import fr.medicapp.medicapp.viewModel.SharedPrescriptionEditViewModel
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PrescriptionEditNotification(
    viewModel: SharedPrescriptionEditViewModel,
    onClick: () -> Unit
) {
    val state = viewModel.sharedState.collectAsState()

    PrescriptionEditNotificationContent(
        state = state.value,
        onClick = onClick,
        save = { context -> viewModel.save(context) },
        addNotification = { viewModel.addNotification() },
        removeNotification = { index -> viewModel.removeNotification(index) },
        updateNotificationActiveState = { index, active -> viewModel.updateNotificationActiveState(index, active) },
        updateNotificationDays = { index, dayOfWeek -> viewModel.updateNotificationDays(index, dayOfWeek) },
        addAlarm = { index -> viewModel.addAlarm(index) },
        updateAlarmTime = { index, alarmIndex, alarm -> viewModel.updateAlarmTime(index, alarmIndex, alarm) },
        removeAlarm = { index, alarmIndex -> viewModel.removeAlarm(index, alarmIndex) }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun PrescriptionEditNotificationContent(
    state: Prescription,
    onClick: () -> Unit,
    save: (Context) -> Unit,
    addNotification: () -> Unit,
    removeNotification: (Int) -> Unit,
    updateNotificationActiveState: (Int, Boolean) -> Unit,
    updateNotificationDays: (Int, DayOfWeek) -> Unit,
    addAlarm: (Int) -> Unit,
    updateAlarmTime: (Int, Int, Alarm) -> Unit,
    removeAlarm: (Int, Int) -> Unit
) {
    val context = LocalContext.current

    Edit(
        title = "Ajouter une prescription",
        bottomText = "Terminer",
        onClick = {
            save(context)
            onClick()
        }
    ) {
        Column {
            ReusableButton(
                text = "Ajouter une notification",
                onClick = {
                    addNotification()
                }
            )

            state.notifications.forEachIndexed { index, notification ->
                Spacer(modifier = Modifier.padding(10.dp))

                ReusableElevatedCard {
                    Column(
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Notification ${index + 1}",
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
                                fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                            )

                            Row {
                                Switch(
                                    checked = notification.active,
                                    onCheckedChange = {
                                        updateNotificationActiveState(index, it)
                                    }
                                )

                                IconButton(
                                    onClick = {
                                        removeNotification(index)
                                    },
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Delete,
                                        contentDescription = "",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.padding(10.dp))

                        Row(
                            Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            DayOfWeek.values().forEach { dayOfWeek ->
                                OutlinedIconToggleButton(
                                    colors = IconButtonDefaults.outlinedIconToggleButtonColors(
                                        containerColor = MaterialTheme.colorScheme.surface,
                                        checkedContainerColor = MaterialTheme.colorScheme.primary
                                    ),
                                    border = BorderStroke(
                                        width = 1.dp,
                                        color = MaterialTheme.colorScheme.primary
                                    ),
                                    checked = notification.days.contains(dayOfWeek),
                                    onCheckedChange = {
                                        updateNotificationDays(index, dayOfWeek)
                                    },
                                    shape = MaterialTheme.shapes.medium
                                ) {
                                    Text(
                                        text = dayOfWeek.getDisplayName(
                                            TextStyle.FULL,
                                            Locale.FRENCH
                                        )
                                            .uppercase(Locale.FRENCH)
                                            .take(2),
                                        color = if (notification.days.contains(dayOfWeek)) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                        fontStyle = MaterialTheme.typography.bodySmall.fontStyle,
                                        fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.padding(10.dp))

                        ReusableButton(
                            text = "Ajouter une alarme",
                            onClick = {
                                addAlarm(index)
                            }
                        )

                        Column {
                            notification.alarms.forEachIndexed { alarmIndex, alarm ->
                                Spacer(modifier = Modifier.padding(10.dp))

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    ReusableOutlinedTimePickerButton(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(1f),
                                        value = alarm,
                                        label = "Heure",
                                        onSelected = {
                                            updateAlarmTime(index, alarmIndex, it)
                                        }
                                    )

                                    IconButton(
                                        onClick = {
                                            removeAlarm(index, alarmIndex)
                                        },
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Delete,
                                            contentDescription = "",
                                            tint = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Light Theme")
@Composable
private fun PrescriptionEditNotificationPreview() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        PrescriptionEditNotificationContent(
            state = Prescription(
                notifications = mutableListOf(
                    Notification(
                        active = true,
                        days = mutableListOf(DayOfWeek.MONDAY, DayOfWeek.TUESDAY),
                        alarms = mutableListOf(
                            Alarm(
                                hour = 12,
                                minute = 30
                            )
                        )
                    )
                )
            ),
            onClick = {},
            save = {},
            addNotification = {},
            removeNotification = {},
            updateNotificationActiveState = { _, _ -> },
            updateNotificationDays = { _, _ -> },
            addAlarm = {},
            updateAlarmTime = { _, _, _ -> },
            removeAlarm = { _, _ -> }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Dark Theme")
@Composable
private fun PrescriptionEditNotificationDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        PrescriptionEditNotificationContent(
            state = Prescription(
                notifications = mutableListOf(
                    Notification(
                        active = true,
                        days = mutableListOf(DayOfWeek.MONDAY, DayOfWeek.TUESDAY),
                        alarms = mutableListOf(
                            Alarm(
                                hour = 12,
                                minute = 30
                            )
                        )
                    )
                )
            ),
            onClick = {},
            save = {},
            addNotification = {},
            removeNotification = {},
            updateNotificationActiveState = { _, _ -> },
            updateNotificationDays = { _, _ -> },
            addAlarm = {},
            updateAlarmTime = { _, _, _ -> },
            removeAlarm = { _, _ -> }
        )
    }
}
