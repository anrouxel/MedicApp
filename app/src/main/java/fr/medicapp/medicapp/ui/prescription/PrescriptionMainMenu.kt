package fr.medicapp.medicapp.ui.prescription

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.medicapp.medicapp.entity.DurationEntity
import fr.medicapp.medicapp.entity.TreatmentEntity
import fr.medicapp.medicapp.model.Duration
import fr.medicapp.medicapp.model.Treatment
import fr.medicapp.medicapp.ui.theme.EUPurple100
import fr.medicapp.medicapp.ui.theme.EUPurple60
import fr.medicapp.medicapp.ui.theme.EUPurple80
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Cette fonction affiche le menu principal de prescription avec des informations spécifiques.
 *
 * @param ordonnances La liste des traitements pour les ordonnances.
 * @param onPrescription La fonction à exécuter lorsque l'utilisateur sélectionne une prescription.
 * @param addPrescription La fonction à exécuter lorsque l'utilisateur ajoute une prescription.
 */
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrescriptionMainMenu(
    ordonnances : MutableList<Treatment>,
    onPrescription: (String) -> Unit,
    addPrescription: () -> Unit
) {
    var darkmode : Boolean = isSystemInDarkTheme()
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = topAppBarColors(
                    containerColor = Color.Unspecified,
                    titleContentColor = if (darkmode) Color.White else Color.Black,
                ),
                title = {
                    Text(
                        "Traitements",
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        },
        bottomBar = {

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = addPrescription,
                containerColor = EUPurple100
            ) {
                Icon(
                    imageVector = Icons.Filled.DocumentScanner,
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
            if (ordonnances.isNotEmpty()){
                for (i in ordonnances) {
                    ElevatedCard(
                        onClick = {
                            onPrescription(i.id)
                        },
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        ),
                        modifier = Modifier
                            .wrapContentHeight(),
                            //.height(height = 110.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = EUPurple80,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                        ) {
                            Text(
                                text = i.medication?.name ?: "",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold

                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Row() {
                                Icon(
                                    imageVector = Icons.Filled.Person,
                                    contentDescription = "",
                                    tint = Color.White
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    i.posology,
                                    fontSize = 15.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            Row() {
                                Icon(
                                    imageVector = Icons.Filled.CalendarToday,
                                    contentDescription = "",
                                    tint = Color.White
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    i.duration!!.startDate.format(formatter) + " - " + i.duration!!.endDate.format(formatter),
                                    fontSize = 15.sp
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentHeight(align = Alignment.CenterVertically)
                ) {
                    Text(
                        "Vous n'avez pas d'ordonnances.\nPour en ajouter, cliquez sur le bouton en bas.",
                        color = if (darkmode) EUPurple60 else EUPurple100,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        fontStyle = FontStyle.Italic
                    )
                }

            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun PrescriptionMainMenuPreview() {
    var ordonnances = mutableListOf<Treatment>(
        Treatment(
            id = "1",
            medication = null,
            posology = "1 comprimé par jour",
            quantity = "1 boite",
            renew = "1 fois",
            duration = Duration(
                startDate = LocalDate.now(),
                endDate = LocalDate.now()
            ),
            notification = false
        ),
    )


    PrescriptionMainMenu(ordonnances, {}, {})
}