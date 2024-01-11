package fr.medicapp.medicapp.ui.sideeffectsdiary

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.medicapp.medicapp.entity.SideEffectEntity
import fr.medicapp.medicapp.entity.TreatmentEntity
import fr.medicapp.medicapp.model.SideEffect
import fr.medicapp.medicapp.ui.theme.EUBlue100
import fr.medicapp.medicapp.ui.theme.EURed100
import fr.medicapp.medicapp.ui.theme.EURed80
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SEDMainMenu(
    sideeffects : List<SideEffect>,
    onSideEffect: (String) -> Unit,
    addSideEffect: () -> Unit
) {
    var darkmode : Boolean = isSystemInDarkTheme()
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

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { addSideEffect() },
                containerColor = EURed100
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
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
            if (sideeffects.isNotEmpty()){
                for (i in sideeffects) {
                    ElevatedCard(
                        onClick = { onSideEffect(i.id) },
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
                                text = i.medicament?.medication?.name ?: "",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold

                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                i.effetsConstates.toString().replace("[", "").replace("]", ""),
                                fontSize = 15.sp
                            )
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
                        "Vous n'avez pas constaté d'effets secondaires.\nPour en ajouter un, cliquez sur\nle bouton en bas.",
                        color = EURed100,
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
private fun SEDMainMenuPreview() {
    var se = mutableListOf<SideEffect>(
        SideEffect(
        id = "",
            null,
        LocalDate.now(),
        12,
        30,
        mutableListOf("Mal de tête", "Nausées"),
        "J'ai eu mal à la tête hier"
    )
    )
    //var se = listOf<TestSideEffect>() /* TODO */
    SEDMainMenu(se, {}, {})
}