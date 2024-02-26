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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.ui.components.button.ReusableAlertButton
import fr.medicapp.medicapp.ui.components.button.ReusableAlertIconButton
import fr.medicapp.medicapp.ui.components.card.ReusableElevatedCard
import fr.medicapp.medicapp.ui.components.screen.Detail
import fr.medicapp.medicapp.ui.components.text.ReusableTextMediumCard
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
            ReusableElevatedCard {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    state.value.doctor?.let {
                        ReusableTextMediumCard(
                            value = "Docteur : $it",
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))

            ReusableElevatedCard {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    state.value.medication?.let {
                        ReusableTextMediumCard(
                            value = "Médicament : $it",
                        )
                    }

                    Spacer(modifier = Modifier.padding(10.dp))

                    ReusableTextMediumCard(
                        value = "Posologie : ${state.value.prescriptionInformation.posology}",
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    ReusableTextMediumCard(
                        value = "Fréquence : ${state.value.prescriptionInformation.frequency}",
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    state.value.duration?.let {
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
                                    checked = notification.notificationInformation.active,
                                    onCheckedChange = {
                                        viewModel.updatedNotificationState(context, index, it)
                                    }
                                )

                                ReusableAlertIconButton(
                                    onClick = {
                                        viewModel.removeNotification(
                                            context,
                                            notification.notificationInformation.id
                                        )
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
                            value = "Active : ${notification.notificationInformation.active}",
                        )

                        Spacer(modifier = Modifier.padding(10.dp))

                        ReusableTextMediumCard(
                            value = "Jours : ${notification.notificationInformation.days.map { it.toString() }}",
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
            )
        }
    }
}
