package fr.medicapp.medicapp.ui.screen.doctor

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fr.medicapp.medicapp.model.Doctor
import fr.medicapp.medicapp.ui.theme.EURedColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

@Composable
fun DoctorDetail(
    doctor: Doctor
) {

}

@Preview
@Composable
fun DoctorDetailPreview() {
    val doctor = Doctor()
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EURedColorShema
    ) {
        DoctorDetail(doctor = doctor)
    }
}

@Preview
@Composable
fun DoctorDetailDarkPreview() {
    val doctor = Doctor()
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EURedColorShema
    ) {
        DoctorDetail(doctor = doctor)
    }
}