package fr.medicapp.medicapp.ui.screen.sideeffect

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.model.prescription.relationship.SideEffect
import fr.medicapp.medicapp.ui.components.button.ReusableElevatedCardButton
import fr.medicapp.medicapp.ui.components.card.CardContent
import fr.medicapp.medicapp.ui.components.screen.Home
import fr.medicapp.medicapp.ui.theme.EURedColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

@Composable
fun SideEffectHome(
    sideEffects: List<SideEffect>,
    onSideEffectClick: (Long) -> Unit = {},
    onAddSideEffectClick: () -> Unit = {}
) {
    Home(
        title = "Effets secondaires",

        floatingActionButtons = {
            FloatingActionButton(
                onClick = onAddSideEffectClick,
                containerColor = MaterialTheme.colorScheme.primary,
            ) {
                Icon(
                    imageVector = Icons.Default.DocumentScanner,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) {
        if (sideEffects.isEmpty()) {
            NoSideEffectAvailable()
        } else {
            SideEffectList(
                sideEffects = sideEffects,
                onSideEffectClick = onSideEffectClick
            )
        }
    }
}

@Composable
fun SideEffectList(
    sideEffects: List<SideEffect>,
    onSideEffectClick: (Long) -> Unit = {}
) {
    Column {
        sideEffects.forEachIndexed { index, sideEffect ->
            SideEffectItem(
                sideEffect = sideEffect,
                onSideEffectClick = onSideEffectClick
            )

            if (index != sideEffects.size - 1) {
                Spacer(modifier = Modifier.padding(10.dp))
            }
        }
    }
}

@Composable
fun SideEffectItem(
    sideEffect: SideEffect,
    onSideEffectClick: (Long) -> Unit
) {
    ReusableElevatedCardButton(
        onClick = { onSideEffectClick(sideEffect.sideEffectInformation.id) }
    ) {
        CardContent(
            title = sideEffect.prescription!!.medication!!.medicationInformation.name,
            description = sideEffect.sideEffectInformation.date.toString()
        )
    }
}

@Composable
fun NoSideEffectAvailable() {
    Text(
        text = "Vous n'avez pas eu d'effet secondaire.\n" + "Pour en ajouter, cliquez sur le bouton en bas",
        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
        fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
        fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(align = Alignment.CenterVertically)
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Light Theme", showSystemUi = true)
@Composable
private fun SideEffectHomePreview() {
    /*val sideEffect = SideEffect(
        prescription = Prescription(
            treatment = Treatment(
                medication = Medication(
                    name = "Doliprane"
                )
            ),
        ),
        date = LocalDate.now()
    )*/

    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EURedColorShema
    ) {
        SideEffectHome(
            sideEffects = listOf()
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Dark Theme", showSystemUi = true)
@Composable
private fun SideEffectHomeDarkPreview() {
    /*val sideEffect = SideEffect(
        prescription = Prescription(
            treatment = Treatment(
                medication = Medication(
                    name = "Doliprane"
                )
            ),
        ),
        date = LocalDate.now()
    )*/

    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EURedColorShema
    ) {
        SideEffectHome(
            sideEffects = listOf()
        )
    }
}

@Preview(name = "Light Theme - No Prescription", showSystemUi = true)
@Composable
private fun NoSideEffectAvailablePreview() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EURedColorShema
    ) {
        SideEffectHome(
            sideEffects = emptyList()
        )
    }
}

@Preview(name = "Dark Theme - No Prescription", showSystemUi = true)
@Composable
private fun NoSideEffectAvailableDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EURedColorShema
    ) {
        SideEffectHome(
            sideEffects = emptyList()
        )
    }
}
