package fr.medicapp.medicapp.ui.screen.prescription

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fr.medicapp.medicapp.ui.components.screen.Edit
import fr.medicapp.medicapp.ui.theme.EUPurpleColorShema
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

@Composable
fun PrescriptionEdit() {
    Edit(
        title = "Ajouter une prescription"
    )
}

@Preview(name = "Light Theme", showSystemUi = true)
@Composable
private fun PrescriptionEditPreview() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        PrescriptionEdit()
    }
}

@Preview(name = "Dark Theme", showSystemUi = true)
@Composable
private fun PrescriptionEditDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        PrescriptionEdit()
    }
}
