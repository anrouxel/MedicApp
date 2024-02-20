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
            val medicationDetails = listOf(
                Pair("Nom du médicament", medication.name),
                Pair("Voie d'administration", medication.administrationRoutes.joinToString()),
                Pair("Code CIS", medication.cisCode.toString()),
                Pair("Informations Importantes", medication.importantInformations.joinToString()),
                Pair("Forme pharmaceutique", medication.pharmaceuticalForm),
                Pair("Composition", medication.medicationCompositions.joinToString()),
                Pair(
                    "Conditions de délivrance des ordonnances",
                    medication.prescriptionDispensingConditions.joinToString()
                ),
                Pair("Groupe générique", medication.genericGroups.joinToString()),
                Pair("Numéro d'authorisation européen", medication.europeanAuthorizationNumber),
                Pair(
                    "Status de l'autorisation de mise sur le marché",
                    medication.marketingAuthorizationStatus
                ),
                Pair(
                    "Type de procédure d'autorisation de mise sur le marché",
                    medication.marketingAuthorizationProcedureType
                ),
                Pair("Status de commercialisation", medication.commercializationStatus),
                Pair(
                    "Date d'autorisation de mise sur le marché",
                    medication.marketingAuthorizationDate.toString()
                ),
                Pair("Status BDM", medication.bdmStatus),
                Pair("Détenteurs", medication.holders.joinToString()),
                Pair("Surveillance renforcée", medication.enhancedMonitoring.toString()),
                Pair(
                    "Présentations du médicament",
                    medication.medicationPresentations.joinToString()
                ),
                Pair("Avis SMR", medication.hasSmrOpinions.joinToString()),
                Pair("Avis ASMR", medication.hasAsmrOpinions.joinToString()),
                Pair(
                    "Spécialités pharmaceutiques",
                    medication.pharmaceuticalSpecialties.joinToString()
                )
            )
            medicationDetails.chunked(3).forEach { element ->
                ReusableElevatedCard {
                    Column(
                        modifier = Modifier.padding(10.dp)
                    ) {
                        element.forEach {
                            MedicationDetailCard(
                                title = it.first,
                                value = it.second,
                                padding = 10.dp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.padding(10.dp))
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