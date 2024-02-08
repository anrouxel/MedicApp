package fr.medicapp.medicapp.ui.sideeffect

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import fr.medicapp.medicapp.entity.medication.Medication
import fr.medicapp.medicapp.model.SideEffect
import fr.medicapp.medicapp.model.Treatment
import fr.medicapp.medicapp.ui.components.card.CardContent
import fr.medicapp.medicapp.ui.components.card.ReusableElevatedCard
import fr.medicapp.medicapp.ui.components.screen.Home
import fr.medicapp.medicapp.ui.theme.EURedColorShema
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import java.time.LocalDate

@Composable
fun SideEffectHome(
    sideEffects: List<SideEffect>,
    onSideEffectClick: () -> Unit = {},
    onAddSideEffectClick: () -> Unit = {}
) {
    Home(
        title = "Prescription",
        floatingActionButtonOnClick = onAddSideEffectClick,
        floatActionButtonIcon = Icons.Default.DocumentScanner
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
    onSideEffectClick: () -> Unit = {}
) {
    sideEffects.forEach { sideEffect ->
        SideEffectItem(
            sideEffect = sideEffect,
            onSideEffectClick = onSideEffectClick
        )
    }
}

@Composable
fun SideEffectItem(
    sideEffect: SideEffect,
    onSideEffectClick: () -> Unit
) {
    ReusableElevatedCard(
        onClick = onSideEffectClick
    ) {
        CardContent(
            title = sideEffect.treatment?.medication?.name ?: "",
            description = sideEffect.date?.toString() ?: ""
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
        modifier = Modifier.fillMaxSize().wrapContentHeight(align = Alignment.CenterVertically)
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Light Theme", showSystemUi = true)
@Composable
private fun SideEffectDetailPreview() {
    val sideEffect = SideEffect(
        treatment = Treatment(
            medication = Medication(
                name = "Doliprane"
            )
        ),
        date = LocalDate.now()
    )

    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EURedColorShema
    ) {
        SideEffectHome(
            sideEffects = listOf(sideEffect)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Dark Theme", showSystemUi = true)
@Composable
private fun SideEffectDetailDarkPreview() {
    val sideEffect = SideEffect(
        treatment = Treatment(
            medication = Medication(
                name = "Doliprane"
            )
        ),
        date = LocalDate.now()
    )

    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EURedColorShema
    ) {
        SideEffectHome(
            sideEffects = listOf(sideEffect)
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
