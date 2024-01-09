package fr.medicapp.medicapp.ui.prescription

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.AttachMoney
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
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.medicapp.medicapp.ui.theme.EUBlue100
import fr.medicapp.medicapp.ui.theme.EUGreen100
import fr.medicapp.medicapp.ui.theme.EUGreen40
import fr.medicapp.medicapp.ui.theme.EUOrange100
import fr.medicapp.medicapp.ui.theme.EUPurple20
import fr.medicapp.medicapp.ui.theme.EUPurple80
import fr.medicapp.medicapp.ui.theme.EURed100
import fr.medicapp.medicapp.ui.theme.EURed40
import fr.medicapp.medicapp.ui.theme.EUYellow100

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Prescription(consultation : TestConsultation) {
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
                            text = "Fermer",
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
                            containerColor = EUOrange100,
                            contentColor = Color.White
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
                    .height(height = 80.dp),
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
                    Row() {
                        Text(
                            "Médecin : ",
                            fontSize = 20.sp,
                            color = EUPurple20,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            consultation.medecin,
                            fontSize = 20.sp,
                            color = EUPurple20
                        )
                    }
                    Row() {
                        Text(
                            "Date : ",
                            fontSize = 20.sp,
                            color = EUPurple20,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            consultation.date,
                            fontSize = 20.sp,
                            color = EUPurple20
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))

            // Itération de la liste des médicaments
            for (i in consultation.medicaments) {
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
                        Text(
                            i.nom,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

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

                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            var checked by remember { mutableStateOf(i.notificationActive) }
                            Switch(
                                checked = checked,
                                onCheckedChange = {
                                    checked = it
                                },
                                colors = SwitchDefaults.colors(
                                    disabledCheckedThumbColor = Color.White,
                                    disabledCheckedTrackColor = EUGreen40,
                                    disabledUncheckedBorderColor = EURed40,
                                    disabledUncheckedThumbColor = Color.White,
                                    disabledUncheckedTrackColor = EURed40,
                                ),
                                enabled = false
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
                                i.posologie,
                                fontSize = 18.sp
                            )
                        }
                        Spacer(modifier = Modifier.width(5.dp))

                        if (i.aRenouveler != 0) {
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
                                    i.aRenouveler.toString() + " fois",
                                    fontSize = 18.sp
                                )
                            }
                            Spacer(modifier = Modifier.width(5.dp))
                        }

                        if (i.quantiteSuffisantePour != "") {
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
                                    i.quantiteSuffisantePour,
                                    fontSize = 18.sp
                                )
                            }
                            Spacer(modifier = Modifier.width(5.dp))
                        }

                        if (i.remboursable) {
                            Row() {
                                Icon(
                                    imageVector = Icons.Filled.AttachMoney,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .background(color = EUYellow100)
                                        .clip(RoundedCornerShape(20.dp)),
                                    tint = Color.White
                                )
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
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PrescriptionPreview() {
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
                "Informations incomplètes"),
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
    Prescription(tab)
}