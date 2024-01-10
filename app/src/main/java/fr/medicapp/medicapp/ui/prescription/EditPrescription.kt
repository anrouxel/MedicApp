package fr.medicapp.medicapp.ui.prescription

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ImportExport
import androidx.compose.material.icons.filled.Upload
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
import fr.medicapp.medicapp.model.Doctor
import fr.medicapp.medicapp.model.Duration
import fr.medicapp.medicapp.model.Prescription
import fr.medicapp.medicapp.model.Treatment
import fr.medicapp.medicapp.ui.prescription.EditPrescription.AddButton
import fr.medicapp.medicapp.ui.prescription.EditPrescription.TreatmentCard
import fr.medicapp.medicapp.ui.theme.EUGreen100
import fr.medicapp.medicapp.ui.theme.EUGreen40
import fr.medicapp.medicapp.ui.theme.EUOrange20
import fr.medicapp.medicapp.ui.theme.EUPurple100
import fr.medicapp.medicapp.ui.theme.EUPurple20
import fr.medicapp.medicapp.ui.theme.EUPurple60
import fr.medicapp.medicapp.ui.theme.EUPurple80
import fr.medicapp.medicapp.ui.theme.EURed100
import fr.medicapp.medicapp.ui.theme.EURed60
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPrescription(
    doctors: List<Doctor>,
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    prescription: Prescription
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
                            onConfirm()
                        },
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
                            selection = OptionSelection.Single(
                                doctors.map { doctor: Doctor -> doctor.getOption() }
                            ) { index, _ ->
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

                    var datePrescriptionState = rememberDatePickerState()

                    if (datePrescriptionOpen) {
                        DatePickerModal(
                            state = datePrescriptionState,
                            onDismissRequest = {
                                datePrescriptionOpen = false
                            },
                            onConfirm = {
                                datePrescriptionOpen = false
                                if (datePrescriptionState.selectedDateMillis != null) {
                                    prescription.date = Instant.ofEpochMilli(datePrescriptionState.selectedDateMillis!!).atZone(
                                        ZoneId.systemDefault()
                                    ).toLocalDate()
                                }
                            }
                        )
                    }

                    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

                    OutlinedTextField(
                        enabled = false,
                        value = if (prescription.date != null) prescription.date!!.format(formatter) else "",
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
                prescription.treatments.forEachIndexed { index, treatment ->
                    TreatmentCard(
                        treatment = treatment,
                        onRemove = {
                            prescription.treatments.removeAt(index)
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }

                AddButton(
                    text = "Ajouter un traitement",
                    icone = Icons.Filled.Add,
                    color = EUPurple80,
                    onClick = {
                        Log.d("EditPrescription", "AddButton: onClick")
                        var treatment = Treatment(
                            duration = Duration(
                                startDate = LocalDate.now(),
                                endDate = LocalDate.now(),
                            ),
                            notification = false,
                        )
                        Log.d("EditPrescription", "AddButton: treatment = $treatment")
                        prescription.treatments.add(treatment)
                        Log.d(
                            "EditPrescription",
                            "AddButton: prescription.treatments = ${prescription.treatments}"
                        )
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                AddButton(
                    text = "Importer une ordonnance (Galerie)",
                    icone = Icons.Filled.Upload,
                    color = EUPurple80,
                    onClick = {
                        Log.d("EditPrescription", "AddButton: onClick")
                        var treatment = Treatment(
                            duration = Duration(
                                startDate = LocalDate.now(),
                                endDate = LocalDate.now(),
                            ),
                            notification = false,
                        )
                        Log.d("EditPrescription", "AddButton: treatment = $treatment")
                        prescription.treatments.add(treatment)
                        Log.d(
                            "EditPrescription",
                            "AddButton: prescription.treatments = ${prescription.treatments}"
                        )
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                AddButton(
                    text = "Importer une ordonnance (Camera)",
                    icone = Icons.Filled.Upload,
                    color = EUPurple80,
                    onClick = {
                        Log.d("EditPrescription", "AddButton: onClick")
                        var treatment = Treatment(
                            duration = Duration(
                                startDate = LocalDate.now(),
                                endDate = LocalDate.now(),
                            ),
                            notification = false,
                        )
                        Log.d("EditPrescription", "AddButton: treatment = $treatment")
                        prescription.treatments.add(treatment)
                        Log.d(
                            "EditPrescription",
                            "AddButton: prescription.treatments = ${prescription.treatments}"
                        )
                    }
                )
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
        onCancel = {},
        onConfirm = {},
    )
}