package fr.medicapp.medicapp.ui.screen.prescription

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.medicapp.medicapp.model.Alarm
import fr.medicapp.medicapp.model.Notification
import fr.medicapp.medicapp.model.Prescription
import fr.medicapp.medicapp.ui.components.card.ReusableElevatedCard
import fr.medicapp.medicapp.ui.components.screen.Edit
import fr.medicapp.medicapp.ui.components.textfield.ReusableOutlinedTextField
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
    Edit(
        title = "Ajouter une prescription",
        bottomText = "Terminer",
        onClick = onClick
    ) {
        Column {
            /*prescription.notifications.forEachIndexed { index, notification ->
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

                            Switch(
                                checked = true,
                                onCheckedChange = { }
                            )
                        }

                        Spacer(modifier = Modifier.padding(10.dp))

                        Row(
                            Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            DayOfWeek.values().forEachIndexed { index, dayOfWeek ->
                                IconToggleButton(
                                    checked = true,
                                    onCheckedChange = { },
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .border(
                                            1.dp,
                                            MaterialTheme.colorScheme.primary,
                                            CircleShape
                                        )
                                        .background(MaterialTheme.colorScheme.primary)

                                ) {
                                    Text(
                                        dayOfWeek.getDisplayName(TextStyle.FULL, Locale.FRENCH)
                                            .uppercase(Locale.FRENCH)
                                            .take(2),
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.padding(10.dp))

                        Column {
                            notification.alarms.forEach { alarm ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    ReusableOutlinedTextField(
                                        modifier = Modifier.fillMaxWidth().weight(1f),
                                        value = "",
                                        onValueChange = { },
                                    )

                                    IconButton(
                                        onClick = { /*TODO*/ },
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
                if (index != prescription.notifications.size - 1) {
                    Spacer(modifier = Modifier.padding(10.dp))
                }
            }*/
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Light Theme")
@Composable
private fun PrescriptionEditNotificationPreview() {
    val prescription = Prescription(
        notifications = mutableListOf(
            Notification(
                alarms = mutableListOf(
                    Alarm()
                )
            ),
            Notification(
                alarms = mutableListOf(
                    Alarm(),
                    Alarm()
                )
            ),
        )
    )

    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        PrescriptionEditNotification(
            viewModel = viewModel(),
            onClick = {}
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Dark Theme")
@Composable
private fun PrescriptionEditNotificationDarkPreview() {
    val prescription = Prescription(
        notifications = mutableListOf(
            Notification(
                alarms = mutableListOf(
                    Alarm()
                )
            ),
            Notification(
                alarms = mutableListOf(
                    Alarm(),
                    Alarm()
                )
            ),
        )
    )

    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        PrescriptionEditNotification(
            viewModel = viewModel(),
            onClick = {}
        )
    }
}
