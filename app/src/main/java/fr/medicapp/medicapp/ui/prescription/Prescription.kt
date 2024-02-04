package fr.medicapp.medicapp.ui.prescription

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.medicapp.medicapp.entity.TreatmentEntity
import fr.medicapp.medicapp.model.Treatment
import fr.medicapp.medicapp.ui.theme.EUBlue100
import fr.medicapp.medicapp.ui.theme.EUGreen100
import fr.medicapp.medicapp.ui.theme.EUGreen40
import fr.medicapp.medicapp.ui.theme.EUOrange100
import fr.medicapp.medicapp.ui.theme.EUPurple20
import fr.medicapp.medicapp.ui.theme.EURed100
import fr.medicapp.medicapp.ui.theme.EURed40

/**
 * Cette fonction affiche la prescription avec des informations spécifiques.
 *
 * @param consultation La liste des traitements pour la consultation.
 * @param onClose La fonction à exécuter lorsque l'utilisateur ferme la prescription.
 * @param onRemove La fonction à exécuter lorsque l'utilisateur supprime la prescription.
 * @param onUpdate La fonction à exécuter lorsque l'utilisateur met à jour la prescription.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Prescription(
    consultation: TreatmentEntity,
    onClose: () -> Unit,
    onRemove: () -> Unit,
    onUpdate: (Long, Boolean) -> Unit
) {
    var darkmode: Boolean = isSystemInDarkTheme()
    Scaffold(
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
                        onClick = onRemove,
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
            Spacer(modifier = Modifier.height(15.dp))

            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = EUPurple20,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier
                        .verticalScroll(
                            enabled = true,
                            state = rememberScrollState()
                        )
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    /*Text(
                        i.medication?.name ?: "",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )*/

                    /*Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Switch(
                            enabled = false,
                            checked = notification.value,
                            onCheckedChange = {
                                notification.value = it
                                onUpdate(consultation.id, it)
                            },
                            colors = SwitchDefaults.colors(
                                disabledCheckedThumbColor = Color.White,
                                disabledCheckedTrackColor = EUGreen40,
                                disabledUncheckedBorderColor = EURed40,
                                disabledUncheckedThumbColor = Color.White,
                                disabledUncheckedTrackColor = EURed40,
                            ),
                        )
                        Spacer(modifier = Modifier.width(7.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 10.dp)
                        ) {
                            Text(
                                "Notification de rappel ${if (i.notification) "activée" else "désactivée"}",
                                fontSize = 18.sp
                            )
                        }
                    }*/

                    Row() {
                        Box(
                            modifier = Modifier
                                .background(color = EUGreen100)
                                .clip(RoundedCornerShape(100.dp))
                        ) {
                            Icon(
                                imageVector = Icons.Filled.HourglassTop,
                                contentDescription = "",
                                tint = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            consultation.posology,
                            fontSize = 18.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(5.dp))

                    if (consultation.renew != "") {
                        Row() {
                            Icon(
                                imageVector = Icons.Filled.Repeat,
                                contentDescription = "",
                                modifier = Modifier
                                    .background(color = EUBlue100)
                                    .clip(RoundedCornerShape(20.dp)),
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                "A renouveler : ",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                consultation.renew.toString() + " fois",
                                fontSize = 18.sp
                            )
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                    }

                    if (consultation.quantity != "") {
                        Row() {
                            Icon(
                                imageVector = Icons.Filled.Medication,
                                contentDescription = "",
                                modifier = Modifier
                                    .background(color = EUOrange100)
                                    .clip(RoundedCornerShape(20.dp)),
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                "Quantité suffisante pour : ",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                consultation.quantity,
                                fontSize = 18.sp
                            )
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PrescriptionPreview() {
    var tab = TreatmentEntity()
    Prescription(tab, {}, {}) { _, _ -> }
}