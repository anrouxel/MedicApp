package fr.medicapp.medicapp.ui.prescription

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.MoneyOff
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.medicapp.medicapp.R
import fr.medicapp.medicapp.ui.theme.EUBlue100
import fr.medicapp.medicapp.ui.theme.EUGreen100
import fr.medicapp.medicapp.ui.theme.EUGreen120
import fr.medicapp.medicapp.ui.theme.EUOrange100
import fr.medicapp.medicapp.ui.theme.EUPurple20
import fr.medicapp.medicapp.ui.theme.EUPurple80
import fr.medicapp.medicapp.ui.theme.EURed100
import fr.medicapp.medicapp.ui.theme.EUYellow100

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Prescription() {
    var tab = listOf(
        TestMedicament("Doliprane 1000mg",
        "3 fois par jour",
        3,
        "8 jours",
        true),
        TestMedicament("Cortisone",
        "1 fois par jour",
        0,
        "",
        false),
        TestMedicament("Esoméprazole",
        "2 fois par jour",
        2,
        "8 semaines",
        true),
        TestMedicament("Monoprost",
        "1 fois par jour",
        10,
        "15 jours",
        true),
        TestMedicament("Ibuprofène",
        "2 fois par jour",
        0,
        "8 jours",
        true))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "ORD0001",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        )

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
                        "Dr. MOTTU",
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
                        "22/11/2023",
                        fontSize = 20.sp,
                        color = EUPurple20
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(15.dp))


        for (i in tab) {
            Box() {
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier.verticalScroll(
                        enabled = true,
                        state = rememberScrollState()
                    )
                ) {
                    Text(
                        i.nom,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Row() {
                        Icon(
                            imageVector = Icons.Filled.HourglassTop,
                            contentDescription = "",
                            modifier = Modifier
                                .background(color = EUGreen100)
                                .clip(RoundedCornerShape(20.dp)),
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            i.posologie,
                            fontSize = 18.sp
                        )
                    }

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
                    Box(
                        modifier = Modifier.height(10.dp)
                    ) {

                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, top = 30.dp)
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
                    text = "Editer",
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
                    contentColor = Color.White
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

@Preview(showBackground = true)
@Composable
private fun PrescriptionPreview() {
    Prescription()
}