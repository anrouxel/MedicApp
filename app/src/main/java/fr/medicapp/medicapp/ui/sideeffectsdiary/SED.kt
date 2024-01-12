package fr.medicapp.medicapp.ui.sideeffectsdiary

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.medicapp.medicapp.entity.DurationEntity
import fr.medicapp.medicapp.entity.SideEffectEntity
import fr.medicapp.medicapp.entity.TreatmentEntity
import fr.medicapp.medicapp.model.SideEffect
import fr.medicapp.medicapp.ui.theme.EURed100
import fr.medicapp.medicapp.ui.theme.EURed80
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SED(
    sideeffects: MutableList<SideEffect>,
    onMedication: (String) -> Unit,
    onClose: () -> Unit,
    onRemove: () -> Unit
) {
    var darkmode : Boolean = isSystemInDarkTheme()
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Unspecified,
                    titleContentColor = if (darkmode) Color.White else Color.Black,
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
        for (sideeffect in sideeffects) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                ElevatedCard(
                    onClick = {
                        sideeffect.medicament?.let { onMedication(it.id) }
                    },
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = 50.dp),
                    colors =
                    CardDefaults.cardColors(
                        containerColor = EURed80,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                    ) {
                        Text(
                            text = "Médicament :",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold

                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = sideeffect.medicament?.medication?.name ?: "",
                            fontSize = 18.sp
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
                        .height(height = 75.dp),
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
                            text = "Date/Heure de signalement :",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold

                        )
                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = "${if (sideeffect.date != null) sideeffect.date!!.format(formatter) else ""} à ${sideeffect.hour}h${if (sideeffect.minute!! < 9) "0"+sideeffect.minute else sideeffect.minute}",
                            fontSize = 18.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                var effetsConstatesSize = sideeffect.effetsConstates.size
                var effetsConstatesCard = if (sideeffect.effetsConstates.size > 5) 118 else effetsConstatesSize * 18

                ElevatedCard(
                    onClick = { /*TODO*/ },
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = 77.dp + (effetsConstatesCard).dp),
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
                            text = "Effets constatés :",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold

                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        if (effetsConstatesSize > 5) {
                            for (i in sideeffect.effetsConstates.slice(0..4)) {
                                Text(
                                    "- $i",
                                    fontSize = 18.sp
                                )
                            }
                            Text(
                                "Et plus...",
                                fontSize = 18.sp
                            )
                        } else {
                            for (i in sideeffect.effetsConstates) {
                                Text(
                                    "- $i",
                                    fontSize = 18.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun SEDPreview() {
    var se = mutableListOf<SideEffect>(
        SideEffect(
            "1",
            null,
            LocalDate.now(),
            0,
            0,
            mutableListOf("Mal de tête", "Nausées", "Malaise", "Vomissements", "Diarrhée", "Mal aux yeux"),
            "J'ai eu mal à la tête hier"
        )
    )
    // var se = listOf<TestSideEffect>()
    SED(se, {}, {}, {})
}
