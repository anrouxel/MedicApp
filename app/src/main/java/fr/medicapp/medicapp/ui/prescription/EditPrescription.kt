package fr.medicapp.medicapp.ui.prescription

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.date_time.DateTimeDialog
import com.maxkeppeler.sheets.date_time.models.DateTimeSelection
import com.maxkeppeler.sheets.option.OptionDialog
import com.maxkeppeler.sheets.option.models.DisplayMode
import com.maxkeppeler.sheets.option.models.Option
import com.maxkeppeler.sheets.option.models.OptionConfig
import com.maxkeppeler.sheets.option.models.OptionSelection
import fr.medicapp.medicapp.entity.Doctor
import fr.medicapp.medicapp.entity.Duration
import fr.medicapp.medicapp.entity.Frequency
import fr.medicapp.medicapp.entity.Prescription
import fr.medicapp.medicapp.entity.Treatment
import fr.medicapp.medicapp.ui.theme.EUGreen100
import fr.medicapp.medicapp.ui.theme.EUGreen40
import fr.medicapp.medicapp.ui.theme.EUOrange20
import fr.medicapp.medicapp.ui.theme.EUPurple100
import fr.medicapp.medicapp.ui.theme.EUPurple20
import fr.medicapp.medicapp.ui.theme.EUPurple60
import fr.medicapp.medicapp.ui.theme.EUPurple80
import fr.medicapp.medicapp.ui.theme.EURed100
import fr.medicapp.medicapp.ui.theme.EURed60
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPrescription(
    doctors: List<Doctor>,
    onValidate: () -> Unit,
    prescription: Prescription,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                colors = topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                ),
                title = {
                    Text(
                        "ORD0001",
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
                        onClick = { /*TODO*/ },
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
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f)
                    ) {}
                    Button(
                        onClick = { /*TODO*/ },
                        enabled = false,
                        shape = RoundedCornerShape(20),
                        colors = ButtonDefaults.buttonColors(
                            disabledContainerColor = EUOrange20,
                            disabledContentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(3f)
                    ) {
                        Text(
                            text = "Éditer",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f)
                    ) {}
                    Button(
                        onClick = { /*TODO*/ },
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
                .verticalScroll(rememberScrollState()),
        ) {
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = EUPurple80,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    var doctorOpen by remember { mutableStateOf(false) }

                    if (doctorOpen) {
                        OptionDialog(
                            state = rememberUseCaseState(true, onCloseRequest = {
                                doctorOpen = false
                            }),
                            selection = OptionSelection.Single(doctors.map { doctor: Doctor -> doctor.getOption() }) { index, option ->
                                prescription.doctor = doctors[index]
                            },
                            config = OptionConfig(mode = DisplayMode.LIST)
                        )
                    }

                    OutlinedTextField(
                        enabled = false,
                        value = prescription.doctor?.getFullName() ?: "",
                        textStyle = TextStyle(fontSize = 16.sp, color = Color.White),
                        onValueChange = { prescription.doctor!!.firstName = it },
                        label = {
                            Text(
                                "Médecin",
                                color = EUPurple20
                            )
                        },
                        shape = RoundedCornerShape(20),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = EUPurple20,
                            unfocusedBorderColor = EUPurple20,
                            disabledBorderColor = EUPurple20,
                            errorBorderColor = EURed60,
                            focusedLabelColor = EUPurple20,
                            unfocusedLabelColor = EUPurple20,
                            disabledLabelColor = EUPurple20,
                            errorLabelColor = EURed60,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                doctorOpen = true
                            }
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    var datePrescriptionOpen by remember { mutableStateOf(false) }

                    if (datePrescriptionOpen) {
                        CalendarDialog(
                            state = rememberUseCaseState(true, onCloseRequest = {
                                datePrescriptionOpen = false
                            }),
                            selection = CalendarSelection.Date { date ->
                                prescription.date = date
                            },
                        )
                    }

                    OutlinedTextField(
                        enabled = false,
                        value = prescription.date.toString(),
                        textStyle = TextStyle(fontSize = 16.sp, color = Color.White),
                        onValueChange = { },
                        label = {
                            Text(
                                "Date de consultation"
                            )
                        },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Filled.CalendarMonth,
                                contentDescription = "",
                                tint = Color.White
                            )
                        },
                        isError = false,
                        shape = RoundedCornerShape(20),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = EUPurple20,
                            unfocusedBorderColor = EUPurple20,
                            disabledBorderColor = EUPurple20,
                            errorBorderColor = EURed60,
                            focusedLabelColor = EUPurple20,
                            unfocusedLabelColor = EUPurple20,
                            disabledLabelColor = EUPurple20,
                            errorLabelColor = EURed60,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                datePrescriptionOpen = true
                            }
                    )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                prescription.treatments.forEach { treatment ->
                    var posologie by remember { mutableStateOf("") }
                    var renouvellement by remember { mutableStateOf("") }
                    var qsp by remember { mutableStateOf("") }

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
                                    value = treatment.medication?.name ?: "",
                                    textStyle = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    onValueChange = { },
                                    label = { Text("Nom du médicament") },
                                    shape = RoundedCornerShape(20),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = EUPurple100,
                                        unfocusedBorderColor = EUPurple100,
                                    ),
                                    modifier = Modifier.weight(1f)
                                )

                                Spacer(modifier = Modifier.width(10.dp))

                                Button(
                                    onClick = {
                                        prescription.treatments.remove(treatment)
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
                                    checked = treatment.notification,
                                    onCheckedChange = {
                                        Log.d("TAG", "EditPrescription: ${treatment.notification}")
                                        treatment.notification = it
                                        Log.d("TAG", "EditPrescription2: ${treatment.notification}")
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
                                        .fillMaxSize()
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
                                    .padding(10.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .padding(8.dp, end = 15.dp)
                                            .size(24.dp)
                                    ) {
                                        Icon(
                                            modifier = Modifier
                                                .background(color = EUGreen100)
                                                .clip(RoundedCornerShape(100.dp)),
                                            imageVector = Icons.Filled.Medication,
                                            contentDescription = "",
                                            tint = Color.White
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(5.dp))
                                    OutlinedTextField(
                                        value = treatment.dosage.toString(),
                                        textStyle = TextStyle(fontSize = 16.sp),
                                        onValueChange = { treatment.dosage = it.toInt() },
                                        label = { Text("Dosage") },
                                        shape = RoundedCornerShape(20),
                                        colors = OutlinedTextFieldDefaults.colors(
                                            focusedBorderColor = EUPurple100,
                                            unfocusedBorderColor = EUPurple100,
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
                                .padding(10.dp)
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
                                            .padding(8.dp, end = 15.dp)
                                            .size(24.dp)
                                    ) {
                                        Icon(
                                            modifier = Modifier
                                                .background(color = EUGreen100)
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

                                Spacer(modifier = Modifier.height(10.dp))

                                // Affichage des fréquences
                                Column {
                                    treatment.frequencies.forEach { frequency ->
                                        var frequencyDayOpen by remember { mutableStateOf(false) }
                                        var frequencyHourOpen by remember { mutableStateOf(false) }

                                        val options = listOf(
                                            Option(
                                                titleText = "Lundi",
                                            ),
                                            Option(
                                                titleText = "Mardi",
                                            ),
                                            Option(
                                                titleText = "Mercredi",
                                            ),
                                            Option(
                                                titleText = "Jeudi",
                                            ),
                                            Option(
                                                titleText = "Vendredi",
                                            ),
                                            Option(
                                                titleText = "Samedi",
                                            ),
                                            Option(
                                                titleText = "Dimanche",
                                            ),
                                        )

                                        if (frequencyDayOpen) {
                                            OptionDialog(
                                                state = rememberUseCaseState(
                                                    true,
                                                    onCloseRequest = {
                                                        frequencyDayOpen = false
                                                    }),
                                                selection = OptionSelection.Single(options) { index, option ->
                                                    frequency.day = index + 1
                                                },
                                            )
                                        }

                                        if (frequencyHourOpen) {
                                            DateTimeDialog(
                                                state = rememberUseCaseState(
                                                    true,
                                                    onCloseRequest = {
                                                        frequencyHourOpen = false
                                                    }),
                                                selection = DateTimeSelection.Time {
                                                    frequency.hour = it.hour
                                                }
                                            )
                                        }
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(10.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Button(
                                                onClick = {
                                                    prescription.treatments.remove(treatment)
                                                },
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = EUPurple60,
                                                    contentColor = Color.White,
                                                ),
                                                shape = RoundedCornerShape(30),
                                                contentPadding = PaddingValues(0.dp),
                                                modifier = Modifier
                                                    .padding(8.dp, end = 15.dp)
                                                    .size(30.dp)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Filled.Delete,
                                                    contentDescription = "",
                                                    tint = Color.White,
                                                )
                                            }
                                            Spacer(modifier = Modifier.width(5.dp))
                                            OutlinedTextField(
                                                enabled = false,
                                                value = frequency.day.toString(),
                                                textStyle = TextStyle(fontSize = 16.sp),
                                                onValueChange = { frequency.day = it.toInt() },
                                                label = { Text("Jour") },
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
                                                    .weight(1f)
                                                    .clickable {
                                                        frequencyDayOpen = true
                                                    }
                                            )
                                            Spacer(modifier = Modifier.width(10.dp))
                                            OutlinedTextField(
                                                enabled = false,
                                                value = frequency.hour.toString(),
                                                textStyle = TextStyle(fontSize = 16.sp),
                                                onValueChange = { frequency.hour = it.toInt() },
                                                label = { Text("Heure") },
                                                shape = RoundedCornerShape(20),
                                                colors = OutlinedTextFieldDefaults.colors(
                                                    focusedBorderColor = EUPurple100,
                                                    disabledBorderColor = EUPurple100,
                                                    unfocusedBorderColor = EUPurple100,
                                                    disabledLabelColor = EUPurple100,
                                                ),
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .clickable {
                                                        frequencyHourOpen = true
                                                    }
                                            )
                                        }
                                    }

                                        ElevatedCard(
                                            elevation = CardDefaults.cardElevation(
                                                defaultElevation = 6.dp
                                            ),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(height = 50.dp)
                                                .clickable {
                                                    treatment.frequencies.add(
                                                        Frequency(
                                                            hour = null,
                                                            day = null
                                                        )
                                                    )

                                                    Log.d("TAG", "EditPrescription: ${treatment.frequencies}")
                                                },
                                            colors = CardDefaults.cardColors(
                                                containerColor = EUPurple80,
                                                contentColor = Color.White
                                            ),
                                            shape = RoundedCornerShape(10.dp)
                                        ) {
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .padding(10.dp),
                                                horizontalArrangement = Arrangement.Center,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Icon(
                                                    modifier = Modifier
                                                        .size(24.dp)
                                                        .clip(RoundedCornerShape(100.dp)),
                                                    imageVector = Icons.Filled.Add,
                                                    contentDescription = "",
                                                    tint = Color.White
                                                )
                                                Spacer(modifier = Modifier.width(8.dp))
                                                Text(
                                                    "Ajouter une fréquence",
                                                    fontSize = 18.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    modifier = Modifier
                                                        .align(Alignment.CenterVertically)
                                                )
                                            }
                                        }

                                }
                            }
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .padding(8.dp, end = 15.dp)
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
                                            treatment.duration!!.startDate = startDate
                                            treatment.duration!!.endDate = endDate
                                        },
                                    )
                                }

                                OutlinedTextField(
                                    enabled = false,
                                    value = treatment.duration!!.startDate.toString() + " - " + treatment.duration!!.endDate.toString(),
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
                    Spacer(modifier = Modifier.height(20.dp))
                }

                    ElevatedCard(
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(height = 50.dp)
                            .clickable {
                                prescription.treatments.add(
                                    Treatment(
                                        medication = null,
                                        dosage = 0,
                                        frequencies = mutableListOf(),
                                        duration = Duration(
                                            startDate = LocalDate.now(),
                                            endDate = LocalDate.now(),
                                        ),
                                        notification = false,
                                        history = mutableListOf()
                                    )
                                )
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = EUPurple80,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(RoundedCornerShape(100.dp)),
                                imageVector = Icons.Filled.Add,
                                contentDescription = "",
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                "Ajouter un médicament",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                            )
                        }
                    }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun EditPrescriptionPreview() {
    EditPrescription(
        prescription = Prescription(),
        doctors = listOf(
            Doctor(
                firstName = "Jean",
                lastName = "Dupont"
            ),
            Doctor(
                firstName = "John",
                lastName = "Dupont"
            ),
            Doctor(
                firstName = "Mark",
                lastName = "Dupont"
            ),
        ),
        onValidate = {},
    )
}
