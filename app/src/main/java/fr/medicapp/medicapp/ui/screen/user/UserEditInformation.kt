package fr.medicapp.medicapp.ui.screen.user

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fr.medicapp.medicapp.ui.theme.EUPurpleColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

@Composable
fun UserEditInformation(

) {

}

@Preview
@Composable
fun UserEditInformationPreview() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        UserEditInformation()
    }
}