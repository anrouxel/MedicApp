package fr.medicapp.medicapp.ui.prescription

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.MoneyOff
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberDatePickerState
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.medicapp.medicapp.ui.theme.EUBlue100
import fr.medicapp.medicapp.ui.theme.EUGreen100
import fr.medicapp.medicapp.ui.theme.EUGreen40
import fr.medicapp.medicapp.ui.theme.EUOrange100
import fr.medicapp.medicapp.ui.theme.EUOrange20
import fr.medicapp.medicapp.ui.theme.EUPurple100
import fr.medicapp.medicapp.ui.theme.EUPurple20
import fr.medicapp.medicapp.ui.theme.EUPurple60
import fr.medicapp.medicapp.ui.theme.EUPurple80
import fr.medicapp.medicapp.ui.theme.EURed100
import fr.medicapp.medicapp.ui.theme.EURed40
import fr.medicapp.medicapp.ui.theme.EURed60
import fr.medicapp.medicapp.ui.theme.EUYellow100

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPrescription(consultations : TestConsultation) {

    Scaffold(
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
                        enabled = errorList(consultations),
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
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(10.dp)
        ) {
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 150.dp),
                colors = CardDefaults.cardColors(
                    containerColor = EUPurple80,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    var medecin by remember { mutableStateOf("") }
                    var date by remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = consultations.medecin,
                        textStyle = TextStyle(fontSize = 16.sp, color = Color.White),
                        onValueChange = { medecin = it},
                        label = { Text(
                            "Médecin",
                            color = EUPurple20
                        ) },
                        shape = RoundedCornerShape(20),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = EUPurple20,
                            unfocusedBorderColor = EUPurple20,
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    /*val time = System.currentTimeMillis()
                    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = time)
                    DatePicker(state = datePickerState, modifier = Modifier.padding(16.dp))

                    val state = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
                    DatePicker(state = state, modifier = Modifier.padding(16.dp))

                    Text("Entered date timestamp: ${state.selectedDateMillis ?: "no input"}")*/

                    OutlinedTextField(
                        value = consultations.date,
                        textStyle = TextStyle(fontSize = 16.sp, color = Color.White),
                        onValueChange = { date = it},
                        label = { Text(
                            "Date de consultation (jj/mm/yyyy)"
                        ) },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Filled.CalendarMonth,
                                contentDescription = "",
                                tint = Color.White
                            )
                        },
                        isError = false,
                        shape = RoundedCornerShape(20),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = EUPurple20,
                            focusedLabelColor = EUPurple20,
                            unfocusedBorderColor = EUPurple20,
                            unfocusedLabelColor = EUPurple20,
                            errorBorderColor = EURed60,
                            errorLabelColor = EURed60
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            for (i in consultations.medicaments) {
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
                            .verticalScroll(
                                enabled = true,
                                state = rememberScrollState()
                            )
                            .fillMaxSize()
                            .padding(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            OutlinedTextField(
                                value = i.nom,
                                textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                                onValueChange = { posologie = it},
                                label = { Text("Nom du médicament") },
                                shape = RoundedCornerShape(20),
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = EUPurple100,
                                    unfocusedBorderColor = EUPurple100,
                                ),
                                modifier = Modifier.weight(1f)
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Button(
                                onClick = { /*TODO*/ },
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

                        if (i.erreur.isNotEmpty()) {
                            Row() {
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
                        }

                        Row() {
                            var checked by remember { mutableStateOf(i.notificationActive) }
                            Switch(
                                checked = checked,
                                onCheckedChange = {
                                    checked = it
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
                                    "Notification de rappel ${ if (checked) "activée" else "désactivée" }",
                                    fontSize = 18.sp
                                )
                            }
                        }

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
                            OutlinedTextField(
                                value = i.posologie,
                                textStyle = TextStyle(fontSize = 16.sp),
                                onValueChange = { posologie = it},
                                label = { Text("Posologie") },
                                shape = RoundedCornerShape(20),
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = EUPurple100,
                                    unfocusedBorderColor = EUPurple100,
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        if (i.aRenouveler != 0) {
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
                                            .background(color = EUBlue100)
                                            .clip(RoundedCornerShape(100.dp)),
                                        imageVector = Icons.Filled.Repeat,
                                        contentDescription = "",
                                        tint = Color.White
                                    )
                                }
                                Spacer(modifier = Modifier.width(5.dp))
                                OutlinedTextField(
                                    value = i.aRenouveler.toString() + " fois",
                                    textStyle = TextStyle(fontSize = 16.sp),
                                    onValueChange = { renouvellement = it},
                                    label = { Text("Renouvellement") },
                                    shape = RoundedCornerShape(20),
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        focusedBorderColor = EUPurple100,
                                        unfocusedBorderColor = EUPurple100,
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }

                        if (i.quantiteSuffisantePour != "") {
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
                                            .background(color = EUOrange100)
                                            .clip(RoundedCornerShape(100.dp)),
                                        imageVector = Icons.Filled.Medication,
                                        contentDescription = "",
                                        tint = Color.White
                                    )
                                }
                                Spacer(modifier = Modifier.width(5.dp))
                                OutlinedTextField(
                                    value = i.quantiteSuffisantePour,
                                    textStyle = TextStyle(fontSize = 16.sp),
                                    onValueChange = { qsp = it},
                                    label = { Text("Quantité suffisante pour...") },
                                    shape = RoundedCornerShape(20),
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        focusedBorderColor = EUPurple100,
                                        unfocusedBorderColor = EUPurple100,
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }

                        if (i.remboursable) {
                            Row() {
                                Box(
                                    modifier = Modifier
                                        .padding(8.dp, end = 15.dp)
                                        .size(24.dp)
                                ) {
                                    Icon(
                                        modifier = Modifier
                                            .background(color = EUYellow100)
                                            .clip(RoundedCornerShape(100.dp)),
                                        imageVector = Icons.Filled.AttachMoney,
                                        contentDescription = "",
                                        tint = Color.White
                                    )
                                }
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    "Médicament remboursable",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        } else {
                            Row() {
                                Icon(
                                    imageVector = Icons.Filled.MoneyOff,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .background(color = EUYellow100)
                                        .clip(RoundedCornerShape(20.dp)),
                                    tint = Color.White
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    "Médicament non-remboursable",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
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
                    .height(height = 50.dp),
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

@Preview(showBackground = true)
@Composable
private fun EditPrescriptionPreview() {
    var tab = TestConsultation(
        "Dr. MOTTU",
        "22/11/2023",
        mutableListOf(
            TestMedicament("Doliprane 1000mg",
                "3 fois par jour",
                3,
                "8 jours",
                true,
                true,
                ""),
            TestMedicament("Cortisone",
                "1 fois par jour",
                0,
                "",
                false,
                false,
                ""),
            TestMedicament("Esoméprazole",
                "2 fois par jour",
                2,
                "8 semaines",
                true,
                false,
                ""),
            TestMedicament("Monoprost",
                "1 fois par jour",
                10,
                "15 jours",
                true,
                true,
                ""),
            TestMedicament("Ibuprofène",
                "2 fois par jour",
                0,
                "8 jours",
                true,
                true,
                ""))
    )
    /*tab = TestConsultation(
        "Dr. MOTTU",
        "22/11/2023",
        mutableListOf(
            TestMedicament("Doliprane 1000mg",
                "3 fois par jour",
                3,
                "8 jours",
                true,
                true,
                ""))
    )*/
    EditPrescription(tab)
}