package fr.medicapp.medicapp.ui.screen.sideeffect

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.model.SideEffect
import fr.medicapp.medicapp.ui.components.card.ReusableElevatedCard
import fr.medicapp.medicapp.ui.components.screen.Detail
import fr.medicapp.medicapp.ui.components.text.ReusableTextMediumCard
import fr.medicapp.medicapp.ui.theme.EURedColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import fr.medicapp.medicapp.viewModel.SharedSideEffectDetailViewModel

@Composable
fun SideEffectDetail(
    viewModel: SharedSideEffectDetailViewModel,
) {
    val state = viewModel.sharedState.collectAsState()

    SideEffectDetailContent(
        state = state.value
    )
}

@Composable
fun SideEffectDetailContent(
    state: SideEffect
) {
    Detail(
        title = "Détail de l'effet secondaire",
    ) {
        Column {
            ReusableElevatedCard {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    state.prescription?.let {
                        ReusableTextMediumCard(
                            value = "Prescription : ${it.treatment.medication}"
                        )
                    }

                    state.date?.let {
                        Spacer(modifier = Modifier.padding(10.dp))

                        ReusableTextMediumCard(
                            value = "Date : ${it}"
                        )
                    }

                    state.description.let {
                        Spacer(modifier = Modifier.padding(10.dp))

                        ReusableTextMediumCard(
                            value = "Description : ${it}"
                        )
                    }
                }
            }
        }
    }
}

@Preview(name = "Light Theme", showSystemUi = true)
@Composable
private fun SideEffectDetailPreview() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EURedColorShema
    ) {
        SideEffectDetailContent(
            state = SideEffect()
        )
    }
}

@Preview(name = "Dark Theme", showSystemUi = true)
@Composable
private fun SideEffectDetailDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EURedColorShema
    ) {
        SideEffectDetailContent(
            state = SideEffect()
        )
    }
}
