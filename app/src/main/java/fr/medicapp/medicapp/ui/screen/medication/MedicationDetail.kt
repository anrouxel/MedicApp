package fr.medicapp.medicapp.ui.screen.medication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.medicapp.medicapp.ui.components.button.ReusableButton
import fr.medicapp.medicapp.ui.components.card.ReusableElevatedCard
import fr.medicapp.medicapp.ui.components.screen.Detail
import fr.medicapp.medicapp.ui.components.text.ReusableTextMediumCard
import fr.medicapp.medicapp.ui.navigation.Graph
import fr.medicapp.medicapp.ui.theme.EURedColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import fr.medicapp.medicapp.viewModel.SharedMedicationDetailViewModel

@Composable
fun MedicationDetail(
    viewModel: SharedMedicationDetailViewModel,
    onMoreInformationClick: () -> Unit,
) {
    val state = viewModel.sharedState.collectAsState()

    val medicationDetails = remember { mutableStateListOf<Pair<String, String>>() }

    Detail(
        title = "Information du médicament"
    ) {
        Column {
            if (state.value != null) {
                medicationDetails.clear()
                medicationDetails.addAll(
                    listOf(
                        Pair("Nom du médicament", state.value!!.medicationInformation.name),
                        Pair(
                            "Voie d'administration",
                            state.value!!.medicationInformation.administrationRoutes.joinToString()
                        ),
                        Pair("Code CIS", state.value!!.medicationInformation.cisCode.toString()),
                        Pair("Informations Importantes", state.value!!.importantInformations.joinToString()),
                        Pair("Forme pharmaceutique", state.value!!.medicationInformation.pharmaceuticalForm),
                        Pair("Composition", state.value!!.medicationCompositions.joinToString()),
                        Pair(
                            "Conditions de délivrance des ordonnances",
                            state.value!!.prescriptionDispensingConditions.joinToString()
                        ),
                        Pair("Groupe générique", state.value!!.genericGroups.joinToString()),
                        Pair(
                            "Numéro d'authorisation européen",
                            state.value!!.medicationInformation.europeanAuthorizationNumber
                        ),
                        Pair(
                            "Status de l'autorisation de mise sur le marché",
                            state.value!!.medicationInformation.marketingAuthorizationStatus
                        ),
                        Pair(
                            "Type de procédure d'autorisation de mise sur le marché",
                            state.value!!.medicationInformation.marketingAuthorizationProcedureType
                        ),
                        Pair(
                            "Status de commercialisation",
                            state.value!!.medicationInformation.commercializationStatus
                        ),
                        Pair(
                            "Date d'autorisation de mise sur le marché",
                            state.value!!.medicationInformation.marketingAuthorizationDate.toString()
                        ),
                        Pair("Status BDM", state.value!!.medicationInformation.bdmStatus),
                        Pair("Détenteurs", state.value!!.medicationInformation.holders.joinToString()),
                        Pair(
                            "Surveillance renforcée",
                            state.value!!.medicationInformation.enhancedMonitoring.toString()
                        ),
                        Pair(
                            "Présentations du médicament",
                            state.value!!.medicationPresentations.joinToString()
                        ),
                        Pair("Avis SMR", state.value!!.hasSmrOpinions.joinToString()),
                        Pair("Avis ASMR", state.value!!.hasAsmrOpinions.joinToString()),
                        Pair(
                            "Spécialités pharmaceutiques",
                            state.value!!.pharmaceuticalSpecialties.joinToString()
                        )
                    )
                )
            }
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

            ReusableButton(
                text = "Plus d'informations",
            ) {
                onMoreInformationClick()
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
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EURedColorShema
    ) {
        MedicationDetail(viewModel(), {})
    }
}

@Preview
@Composable
fun MedicationDetailDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EURedColorShema
    ) {
        MedicationDetail(viewModel(), {})
    }
}
