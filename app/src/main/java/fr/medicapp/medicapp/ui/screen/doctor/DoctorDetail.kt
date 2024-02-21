package fr.medicapp.medicapp.ui.screen.doctor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.model.Doctor
import fr.medicapp.medicapp.ui.components.card.ReusableElevatedCard
import fr.medicapp.medicapp.ui.components.screen.Detail
import fr.medicapp.medicapp.ui.components.text.ReusableTextMediumCard
import fr.medicapp.medicapp.ui.theme.EURedColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

@Composable
fun DoctorDetail(
    doctor: Doctor
) {
    Detail(
        title = "Informations sur le docteur"
    ) {
        Column {
            ReusableElevatedCard {

                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    ReusableTextMediumCard(
                        value = "${doctor.civilCode} ${doctor.firstName} ${doctor.lastName} \n" +
                                doctor.skillLabel
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    ReusableTextMediumCard(
                        value = "Structure : ${doctor.siteCompanyName}"
                    )
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))

            ReusableElevatedCard {
                Column(
                    Modifier.padding(10.dp)
                ) {
                    ReusableTextMediumCard(
                        value = "Numéro de téléphone : ${doctor.structurePhoneNumber}"
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    ReusableTextMediumCard(
                        value = "Adresse e-mail : ${doctor.structureEmailAddress}"
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun DoctorDetailPreview() {
    val doctor = Doctor(

    )
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