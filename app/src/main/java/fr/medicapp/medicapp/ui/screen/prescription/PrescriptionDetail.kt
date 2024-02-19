package fr.medicapp.medicapp.ui.screen.prescription

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.model.Alarm
import fr.medicapp.medicapp.model.Duration
import fr.medicapp.medicapp.model.Notification
import fr.medicapp.medicapp.model.Prescription
import fr.medicapp.medicapp.model.Treatment
import fr.medicapp.medicapp.model.medication.Medication
import fr.medicapp.medicapp.ui.components.button.ReusableButton
import fr.medicapp.medicapp.ui.components.card.ReusableElevatedCard
import fr.medicapp.medicapp.ui.components.modal.DeleteModal
import fr.medicapp.medicapp.ui.components.screen.Detail
import fr.medicapp.medicapp.ui.components.text.ReusableTextMediumCard
import fr.medicapp.medicapp.ui.theme.EUPurpleColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import fr.medicapp.medicapp.viewModel.SharedPrescriptionDetailViewModel
import java.time.DayOfWeek
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PrescriptionDetail(
    viewModel: SharedPrescriptionDetailViewModel,
    onRemovePrescriptionClick: () -> Unit,
) {
    val state = viewModel.sharedState.collectAsState()
    val context = LocalContext.current

    PrescriptionDetailContent(
        state = state.value,
        updateNotificationActiveState = { index, active ->
            viewModel.updateNotificationActiveState(index, active)
            viewModel.saveUpdate(context)
            viewModel.updateNotificationManager(context, index)
        },
        removeNotification = { index ->
            viewModel.removeFromNotificationManager(context, index)
            viewModel.removeNotification(index)
            viewModel.save(context)
        },
        removePrescription = {
            viewModel.removePrescription(context)
            onRemovePrescriptionClick()
        }

    )
}

@Composable
private fun PrescriptionDetailContent(
    state: Prescription,
    updateNotificationActiveState: (Int, Boolean) -> Unit,
    removeNotification: (Int) -> Unit,
    removePrescription: () -> Unit,
) {
    val showDeletePrescriptionModal = remember { mutableStateOf(false) }
    val showDeleteNotificationModal = remember { mutableStateOf(false) }
    Detail(
        title = "Détail de l'ordonnance",
    ) {
        Column {
            ReusableElevatedCard {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    state.doctor?.let {
                        ReusableTextMediumCard(
                            value = "Docteur : $it",
                        )
                    }

                    state.date?.let {
                        Spacer(modifier = Modifier.padding(10.dp))

                        ReusableTextMediumCard(
                            value = "Date de l'ordonnance : $it",
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))

            ReusableElevatedCard {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    state.treatment.medication?.let {
                        ReusableTextMediumCard(
                            value = "Médicament : $it",
                        )
                    }

                    Spacer(modifier = Modifier.padding(10.dp))

                    ReusableTextMediumCard(
                        value = "Posologie : ${state.treatment.posology}",
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    ReusableTextMediumCard(
                        value = "Fréquence : ${state.treatment.frequency}",
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    state.treatment.duration?.let {
                        ReusableTextMediumCard(
                            value = "Durée : $it",
                        )
                    }
                }
            }

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
                                        showDeleteNotificationModal.value = true
                                    },
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Delete,
                                        contentDescription = "",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                                if (showDeleteNotificationModal.value) {
                                    DeleteModal(
                                        title = "Supprimer cette notification",
                                        onDismissRequest = {
                                            showDeleteNotificationModal.value = false
                                        },
                                        onConfirm = {
                                            removeNotification(index)
                                            showDeleteNotificationModal.value = false
                                        }
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.padding(10.dp))

                        ReusableTextMediumCard(
                            value = "Active : ${notification.active}",
                        )

                        Spacer(modifier = Modifier.padding(10.dp))

                        ReusableTextMediumCard(
                            value = "Jours : ${notification.days}",
                        )

                        Spacer(modifier = Modifier.padding(10.dp))

                        ReusableTextMediumCard(
                            value = "Heures : ${notification.alarms.map { it.toString() }}",
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.padding(10.dp))

            ReusableButton(text = "Supprimer", onClick = {
                showDeletePrescriptionModal.value = true
            })
            if (showDeletePrescriptionModal.value) {
                DeleteModal(
                    title = "Supprimer ce traitement",
                    onDismissRequest = { showDeletePrescriptionModal.value = false },
                    onConfirm = {
                        removePrescription()
                        showDeletePrescriptionModal.value = false
                    }
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Light Mode")
@Composable
fun PrescriptionDetailPreview() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        PrescriptionDetailContent(
            state = Prescription(
                id = 1,
                date = LocalDate.now(),
                treatment = Treatment(
                    medication = Medication(
                        name = "Doliprane"
                    ),
                    posology = "1 comprimé",
                    frequency = "3 fois par jour",
                    duration = Duration(
                        startDate = LocalDate.now(),
                        endDate = LocalDate.now().plusDays(7)
                    )
                ),
                notifications = mutableListOf(
                    Notification(
                        active = true,
                        days = mutableListOf(
                            DayOfWeek.MONDAY,
                            DayOfWeek.TUESDAY,
                        ),
                        alarms = mutableListOf(
                            Alarm(
                                hour = 8,
                                minute = 0
                            ),
                            Alarm(
                                hour = 12,
                                minute = 0
                            ),
                        )
                    )
                )
            ),
            updateNotificationActiveState = { _, _ -> },
            removeNotification = { _ -> },
            removePrescription = {},
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Dark Mode")
@Composable
fun PrescriptionDetailDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        PrescriptionDetailContent(
            state = Prescription(
                id = 1,
                date = LocalDate.now(),
                treatment = Treatment(
                    medication = Medication(
                        name = "Doliprane"
                    ),
                    posology = "1 comprimé",
                    frequency = "3 fois par jour",
                    duration = Duration(
                        startDate = LocalDate.now(),
                        endDate = LocalDate.now().plusDays(7)
                    )
                ),
                notifications = mutableListOf(
                    Notification(
                        active = true,
                        days = mutableListOf(
                            DayOfWeek.MONDAY,
                            DayOfWeek.TUESDAY,
                        ),
                        alarms = mutableListOf(
                            Alarm(
                                hour = 8,
                                minute = 0
                            ),
                            Alarm(
                                hour = 12,
                                minute = 0
                            ),
                        )
                    )
                )
            ),
            updateNotificationActiveState = { _, _ -> },
            removeNotification = { _ -> },
            removePrescription = {},
        )
    }
}
