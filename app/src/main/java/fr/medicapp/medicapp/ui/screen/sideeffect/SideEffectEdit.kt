package fr.medicapp.medicapp.ui.screen.sideeffect

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fr.medicapp.medicapp.ui.components.screen.Edit
import fr.medicapp.medicapp.ui.screen.prescription.PrescriptionEdit
import fr.medicapp.medicapp.ui.theme.EUPurpleColorShema
import fr.medicapp.medicapp.ui.theme.EURedColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

@Composable
fun SideEffectEdit() {
    Edit(
        title = "Ajouter un effet secondaire"
    )
}

@Preview(name = "Light Theme", showSystemUi = true)
@Composable
private fun SideEffectEditPreview() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EURedColorShema
    ) {
        SideEffectEdit()
    }
}

@Preview(name = "Dark Theme", showSystemUi = true)
@Composable
private fun SideEffectDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EURedColorShema
    ) {
        SideEffectEdit()
    }
}
