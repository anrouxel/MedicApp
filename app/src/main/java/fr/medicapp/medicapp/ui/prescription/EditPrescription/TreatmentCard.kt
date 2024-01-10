package fr.medicapp.medicapp.ui.prescription.EditPrescription

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import fr.medicapp.medicapp.entity.Duration
import fr.medicapp.medicapp.entity.Frequency
import fr.medicapp.medicapp.entity.Treatment
import fr.medicapp.medicapp.ui.theme.EUBlack100
import fr.medicapp.medicapp.ui.theme.EUBlue100
import fr.medicapp.medicapp.ui.theme.EUGreen100
import fr.medicapp.medicapp.ui.theme.EUOrange100
import fr.medicapp.medicapp.ui.theme.EUPurple100
import fr.medicapp.medicapp.ui.theme.EUPurple20
import fr.medicapp.medicapp.ui.theme.EUPurple60
import fr.medicapp.medicapp.ui.theme.EURed100
import fr.medicapp.medicapp.ui.theme.EURed60

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TreatmentCard(
    treatment: Treatment,
    onRemove: () -> Unit
) {
    var notification = remember { mutableStateOf(treatment.notification) }
    var dosage = remember { mutableStateOf(treatment.dosage?.toString() ?: "") }
    var duration = remember { mutableStateOf(treatment.duration.toString()) }

    LaunchedEffect(treatment) {
        notification.value = treatment.notification
        dosage.value = treatment.dosage.toString()
        duration.value = treatment.duration.toString()
    }

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
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                OutlinedTextField(
                    value = treatment.medication.toString(),
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = EUBlack100
                    ),
                    enabled = false,
                    onValueChange = { },
                    label = { Text("Nom du médicament") },
                    shape = RoundedCornerShape(20),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = EUPurple100,
                        unfocusedBorderColor = EUPurple100,
                        disabledBorderColor = EUPurple100,
                        errorBorderColor = EURed60,
                        focusedLabelColor = EUPurple100,
                        unfocusedLabelColor = EUPurple100,
                        disabledLabelColor = EUPurple100,
                        errorLabelColor = EURed60,
                    ),
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Button(
                    onClick = {
                        onRemove()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = EUPurple60,
                        contentColor = Color.White,
                    ),
                    shape = RoundedCornerShape(30),
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .size(57.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            /*if (i.erreur.isNotEmpty()) {
                Row {
                    Icon(
                        imageVector = Icons.Filled.Warning,
                        contentDescription = "",
                        tint = EURed100,
                        modifier = Modifier
                            .padding(top = 2.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        i.erreur,
                        fontSize = 20.sp,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Light,
                        color = EURed100
                    )
                }
            }*/

            Row {
                Switch(
                    checked = notification.value,
                    onCheckedChange = {
                        notification.value = it
                        treatment.notification = it
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = EUGreen100,
                        uncheckedBorderColor = EURed100,
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = EURed100,
                    ),
                )
                Spacer(modifier = Modifier.width(7.dp))
                Box(
                    modifier = Modifier
                        .padding(top = 10.dp)
                ) {
                    Text(
                        "Notification de rappel ${if (treatment.notification) "activée" else "désactivée"}",
                        fontSize = 18.sp
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .padding(end = 15.dp)
                            .size(24.dp)
                    ) {
                        Icon(
                            modifier = Modifier
                                .background(color = EUOrange100)
                                .clip(RoundedCornerShape(100.dp)),
                            imageVector = Icons.Filled.Medication,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    OutlinedTextField(
                        value = dosage.value,//if (treatment.dosage != null) dosage.value else "0",
                        textStyle = TextStyle(fontSize = 16.sp),
                        onValueChange = {
                            dosage.value = it
                            //treatment.dosage = it.toIntOrNull()
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        label = { Text("Dosage") },
                        shape = RoundedCornerShape(20),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = EUPurple100,
                            unfocusedBorderColor = EUPurple100,
                            disabledBorderColor = EUPurple100,
                            errorBorderColor = EURed60,
                            focusedLabelColor = EUPurple100,
                            unfocusedLabelColor = EUPurple100,
                            disabledLabelColor = EUPurple100,
                            errorLabelColor = EURed60,
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        // Zone pour ajouter les fréquences
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .padding(end = 15.dp)
                            .size(24.dp)
                    ) {
                        Icon(
                            modifier = Modifier
                                .background(color = EUBlue100)
                                .clip(RoundedCornerShape(100.dp)),
                            imageVector = Icons.Filled.Repeat,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        "Fréquences",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

                // Affichage des fréquences
                Column {
                    treatment.frequencies.forEachIndexed { index, frequency ->
                        FrequencyCard(
                            frequency = frequency,
                            onRemove = {
                                treatment.frequencies.removeAt(index)
                            }
                        )
                    }

                    AddButton(
                        text = "Ajouter une fréquence",
                        onClick = {
                            treatment.frequencies.add(Frequency(0, 0))
                        }
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .padding(end = 15.dp)
                        .size(24.dp)
                ) {
                    Icon(
                        modifier = Modifier
                            .background(color = EUGreen100)
                            .clip(RoundedCornerShape(100.dp)),
                        imageVector = Icons.Filled.HourglassTop,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))

                var durationOpen by remember { mutableStateOf(false) }

                if (durationOpen) {
                    CalendarDialog(
                        state = rememberUseCaseState(true, onCloseRequest = {
                            durationOpen = false
                        }),
                        selection = CalendarSelection.Period { startDate, endDate ->
                            treatment.duration = Duration(startDate, endDate)
                            duration.value = treatment.duration.toString()
                            durationOpen = false
                        },
                    )
                }

                OutlinedTextField(
                    enabled = false,
                    value = duration.value,
                    textStyle = TextStyle(fontSize = 16.sp),
                    onValueChange = { },
                    label = { Text("Durée") },
                    shape = RoundedCornerShape(20),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = EUPurple100,
                        unfocusedBorderColor = EUPurple100,
                        disabledBorderColor = EUPurple100,
                        errorBorderColor = EURed60,
                        focusedLabelColor = EUPurple100,
                        unfocusedLabelColor = EUPurple100,
                        disabledLabelColor = EUPurple100,
                        errorLabelColor = EURed60,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            durationOpen = true
                        }
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun TreatmentCardPreview() {
    TreatmentCard(
        treatment = Treatment(
            medication = null,
            dosage = null,
            frequencies = mutableListOf(),
            duration = null,
            notification = false,
            history = mutableListOf(),
        ),
        onRemove = {}
    )
}