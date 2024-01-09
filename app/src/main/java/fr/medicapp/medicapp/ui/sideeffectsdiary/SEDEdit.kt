package fr.medicapp.medicapp.ui.sideeffectsdiary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import fr.medicapp.medicapp.ui.theme.EUPurple100
import fr.medicapp.medicapp.ui.theme.EURed100
import fr.medicapp.medicapp.ui.theme.EURed140
import fr.medicapp.medicapp.ui.theme.EURed40
import fr.medicapp.medicapp.ui.theme.EURed60
import fr.medicapp.medicapp.ui.theme.EURed80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SEDEdit(
    sideeffects : TestSideEffect
) {
    var nomMedicament by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                ),
                title = {
                    Text(
                        "Journal des effets",
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        },
        bottomBar = {

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {  },
                containerColor = EURed100
            ) {
                Icon(
                    imageVector = Icons.Filled.Save,
                    contentDescription = "",
                    tint = Color.White
                )
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
                onClick = { /*TODO*/ },
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 85.dp),
                colors =
                CardDefaults.cardColors(
                    containerColor = EURed80,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                ) {
                    OutlinedTextField(
                        value = sideeffects.medicament,
                        textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color= Color.White),
                        onValueChange = { nomMedicament = it },
                        label = { Text("Nom du médicament") },
                        shape = RoundedCornerShape(20),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedLabelColor = Color.White,
                            unfocusedLabelColor = Color.White,
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White,
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            ElevatedCard(
                onClick = { /*TODO*/ },
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 115.dp),
                colors =
                CardDefaults.cardColors(
                    containerColor = EURed80,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    Text(
                        text = "Signalement :",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Row() {
                        OutlinedTextField(
                            value = sideeffects.date,
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                                color = Color.White
                            ),
                            onValueChange = { nomMedicament = it },
                            label = { Text("Date") },
                            shape = RoundedCornerShape(20),
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.CalendarMonth,
                                    contentDescription = "",
                                    tint = Color.White
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedLabelColor = Color.White,
                                unfocusedLabelColor = Color.White,
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = Color.White,
                            ),
                            modifier = Modifier.width(200.dp)
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        OutlinedTextField(
                            value = "17h35",
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                                color = Color.White
                            ),
                            onValueChange = { nomMedicament = it },
                            label = { Text("Heure") },
                            shape = RoundedCornerShape(20),
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Alarm,
                                    contentDescription = "",
                                    tint = Color.White
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedLabelColor = Color.White,
                                unfocusedLabelColor = Color.White,
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = Color.White,
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            var effetsConstatesSize = sideeffects.effetsConstates.size

            ElevatedCard(
                onClick = { /*TODO*/ },
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 65.dp + (effetsConstatesSize * 63 + effetsConstatesSize).dp),
                colors =
                CardDefaults.cardColors(
                    containerColor = EURed80,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                ) {
                    Text(
                        text = "Effets constatés ($effetsConstatesSize) :",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    for (i in 0 until sideeffects.effetsConstates.size) {
                        OutlinedTextField(
                            value = sideeffects.effetsConstates[i],
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                                color = Color.White
                            ),
                            onValueChange = { nomMedicament = it },
                            shape = RoundedCornerShape(20),
                            trailingIcon = {
                                IconButton(onClick = { sideeffects.effetsConstates.drop(i) }) {
                                    Icon(
                                        imageVector = Icons.Filled.DeleteForever,
                                        contentDescription = "",
                                        tint = Color.White
                                    )
                                }
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedLabelColor = Color.White,
                                unfocusedLabelColor = Color.White,
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = Color.White,
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SEDEditPreview() {
    var se = TestSideEffect(
        "Nurofen",
        "14/12/2022",
        mutableListOf("Mal de tête", "Nausées", "Malaise", "Vomissements", "Diarrhée", "Mal aux yeux", "Ratio"),
        "J'ai eu mal à la tête hier"
    )
    //var se = listOf<TestSideEffect>()
    SEDEdit(se)
}