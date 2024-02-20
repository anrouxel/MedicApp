package fr.medicapp.medicapp.ui.screen.doctor

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fr.medicapp.medicapp.ui.components.screen.Home
import fr.medicapp.medicapp.ui.theme.EURedColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

@Composable
fun DoctorHome(

) {
    Home(
        title = "Recherche de médecin",
        floatingActionButtonOnClick = { },
        floatActionButtonIcon = Icons.Filled.Search
    ) {

    }
}

@Preview
@Composable
fun DoctorHomePreview() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EURedColorShema
    ) {
        DoctorHome()
    }
}

@Preview
@Composable
fun DoctorHomeDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EURedColorShema
    ) {
        DoctorHome()
    }
}