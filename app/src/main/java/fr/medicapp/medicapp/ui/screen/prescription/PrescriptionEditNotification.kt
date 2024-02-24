package fr.medicapp.medicapp.ui.screen.prescription

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import fr.medicapp.medicapp.ui.components.screen.Edit
import fr.medicapp.medicapp.viewModel.SharedPrescriptionEditViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PrescriptionEditNotification(
    viewModel: SharedPrescriptionEditViewModel,
    onClick: () -> Unit
) {
    val state = viewModel.sharedState.collectAsState()
    val context = LocalContext.current

    Edit(
        title = "Ajouter une prescription",
        bottomText = "Terminer",
        onClick = {
            //viewModel.save(context)
            onClick()
        },
        enabled = false //state.value.notifications.isNotEmpty() && state.value.notifications.all { it.alarms.isNotEmpty() && it.days.isNotEmpty() }
    ) {
        Column {
            /*ReusableButton(
                text = "Ajouter une notification",
                onClick = {
                    viewModel.addNotification()
                }
            )

            state.value.notifications.forEachIndexed { index, notification ->
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
                                        viewModel.updateNotificationActiveState(index, it)
                                    }
                                )

                                IconButton(
                                    onClick = {
                                        viewModel.removeNotification(index)
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
                                        viewModel.updateNotificationDays(index, dayOfWeek)
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
                                viewModel.addAlarm(index)
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
                                            viewModel.updateAlarmTime(index, alarmIndex, it)
                                        }
                                    )

                                    IconButton(
                                        onClick = {
                                            viewModel.removeAlarm(index, alarmIndex)
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
            }*/
        }
    }
}
