package fr.medicapp.medicapp.ui.screen.prescription

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import fr.medicapp.medicapp.ui.components.screen.Detail
import fr.medicapp.medicapp.viewModel.SharedPrescriptionDetailViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PrescriptionDetail(
    viewModel: SharedPrescriptionDetailViewModel,
    onRemovePrescriptionClick: () -> Unit,
) {
    val state = viewModel.sharedState.collectAsState()
    val context = LocalContext.current

    Detail(
        title = "Détail de l'ordonnance",
    ) {
        Column {
            /*ReusableElevatedCard {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    state.value.doctor?.let {
                        ReusableTextMediumCard(
                            value = "Docteur : $it",
                        )
                    }

                    state.value.date?.let {
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
                    state.value.treatment.medication?.let {
                        ReusableTextMediumCard(
                            value = "Médicament : $it",
                        )
                    }

                    Spacer(modifier = Modifier.padding(10.dp))

                    ReusableTextMediumCard(
                        value = "Posologie : ${state.value.treatment.posology}",
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    ReusableTextMediumCard(
                        value = "Fréquence : ${state.value.treatment.frequency}",
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    state.value.treatment.duration?.let {
                        ReusableTextMediumCard(
                            value = "Durée : $it",
                        )
                    }
                }
            }

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
                                        viewModel.updateNotificationActiveState(index, it, context)
                                    }
                                )

                                ReusableAlertIconButton(
                                    onClick = {
                                        viewModel.removeNotification(index, context)
                                    },
                                    icon = Icons.Default.Delete,
                                    title = "Supprimer cette notification",
                                    content = "Êtes-vous sûr de vouloir supprimer cette notification ?",
                                    dismissText = "Annuler",
                                    confirmText = "Supprimer"
                                )
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

            ReusableAlertButton(
                text = "Supprimer l'ordonnance",
                onClick = {
                    viewModel.removePrescription(context)
                    onRemovePrescriptionClick()
                },
                title = "Supprimer cette ordonnance",
                content = "Êtes-vous sûr de vouloir supprimer cette ordonnance ?",
                dismissText = "Annuler",
                confirmText = "Supprimer"
            )*/
        }
    }
}
