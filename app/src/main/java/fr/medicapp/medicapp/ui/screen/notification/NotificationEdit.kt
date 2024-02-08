package fr.medicapp.medicapp.ui.screen.notification

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fr.medicapp.medicapp.ui.components.screen.Edit
import fr.medicapp.medicapp.ui.theme.EUPurpleColorShema
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

@Composable
fun NotificationEdit() {
    Edit(
        title = "Ajouter une notification"
    )
}

@Preview(name = "Light Theme", showSystemUi = true)
@Composable
private fun NotificationEditPreview() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUYellowColorShema
    ) {
        NotificationEdit()
    }
}

@Preview(name = "Dark Theme", showSystemUi = true)
@Composable
private fun NotificationEditDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EUYellowColorShema
    ) {
        NotificationEdit()
    }
}
