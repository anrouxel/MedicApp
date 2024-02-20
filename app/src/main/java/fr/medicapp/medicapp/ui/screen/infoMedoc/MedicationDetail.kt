package fr.medicapp.medicapp.ui.screen.infoMedoc

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.model.medication.Medication
import fr.medicapp.medicapp.ui.components.card.ReusableElevatedCard
import fr.medicapp.medicapp.ui.components.screen.Detail
import fr.medicapp.medicapp.ui.components.text.ReusableTextMediumCard
import fr.medicapp.medicapp.ui.theme.EURedColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

@Composable
fun MedicationDetail(
    medication: Medication
) {
    Detail(
        title = "Information du médicament"
    ) {
        Column {
            ReusableElevatedCard {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    MedicationDetailCard(
                        title = "Nom du médicament",
                        value = medication.name,
                        padding = 10.dp
                    )

                    MedicationDetailCard(
                        title = "Voie d'administration",
                        value = medication.administrationRoutes,
                        padding = 10.dp
                    )

                    MedicationDetailCard(
                        title = "Code CIS",
                        value = medication.cisCode,
                        padding = 0.dp
                    )
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))

            ReusableElevatedCard {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    MedicationDetailCard(
                        title = "Informations Importantes",
                        value = medication.importantInformations,
                        padding = 10.dp
                    )

                    MedicationDetailCard(
                        title = "Forme pharmaceutique",
                        value = medication.pharmaceuticalForm,
                        padding = 10.dp
                    )

                    MedicationDetailCard(
                        title = "Composition",
                        value = medication.medicationCompositions,
                        padding = 0.dp
                    )

                }
            }

            Spacer(modifier = Modifier.padding(10.dp))

            ReusableElevatedCard {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    MedicationDetailCard(
                        title = "Conditions de délivrance des ordonnances",
                        value = medication.prescriptionDispensingConditions,
                        padding = 10.dp
                    )

                    MedicationDetailCard(
                        title = "Groupe générique",
                        value = medication.genericGroups,
                        padding = 10.dp
                    )

                    MedicationDetailCard(
                        title = "Numéro d'authorisation européen",
                        value = medication.europeanAuthorizationNumber,
                        padding = 0.dp
                    )
                }
            }
        }
    }
}

@Composable
private fun MedicationDetailCard(
    title: String,
    value: Any,
    padding: Dp
) {
    ReusableTextMediumCard(value = "$title : $value")

    Spacer(modifier = Modifier.padding(padding))
}

@Preview
@Composable
fun MedicationDetailPreview() {
    val medication = Medication(
        name = "Doliprane",
    )
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EURedColorShema
    ) {
        MedicationDetail(medication)
    }
}

@Preview
@Composable
fun MedicationDetailDarkPreview() {
    val medication = Medication(
        name = "Janumet",
    )
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EURedColorShema
    ) {
        MedicationDetail(medication)
    }
}