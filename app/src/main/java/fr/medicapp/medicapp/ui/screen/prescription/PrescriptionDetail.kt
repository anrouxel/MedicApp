package fr.medicapp.medicapp.ui.screen.prescription

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.model.Duration
import fr.medicapp.medicapp.model.Prescription
import fr.medicapp.medicapp.model.Treatment
import fr.medicapp.medicapp.model.medication.Medication
import fr.medicapp.medicapp.ui.components.card.ReusableElevatedCard
import fr.medicapp.medicapp.ui.components.screen.Detail
import fr.medicapp.medicapp.ui.components.text.ReusableTextMediumCard
import fr.medicapp.medicapp.ui.theme.EUPurpleColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import fr.medicapp.medicapp.viewModel.SharedPrescriptionEditViewModel
import java.time.LocalDate

@Composable
fun PrescriptionDetail(
    viewModel: SharedPrescriptionEditViewModel,
) {
    val state = viewModel.sharedState.collectAsState()

    PrescriptionDetailContent(
        state = state.value
    )
}

@Composable
private fun PrescriptionDetailContent(
    state: Prescription
) {
    Detail(
        title = "Détail de l'ordonnance",
    ) {
        Column {
            ReusableElevatedCard {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    state.doctor?.let {
                        ReusableTextMediumCard(
                            value = "Docteur : $it",
                        )

                        Spacer(modifier = Modifier.padding(10.dp))
                    }

                    state.date?.let {
                        ReusableTextMediumCard(
                            value = "Date de l'ordonnance : $it",
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))

            ReusableElevatedCard {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    state.treatment.medication?.let {
                        ReusableTextMediumCard(
                            value = "Médicament : $it",
                        )
                    }

                    Spacer(modifier = Modifier.padding(10.dp))

                    ReusableTextMediumCard(
                        value = "Posologie : ${state.treatment.posology}",
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    ReusableTextMediumCard(
                        value = "Fréquence : ${state.treatment.frequency}",
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    state.treatment.duration?.let {
                        ReusableTextMediumCard(
                            value = "Durée : $it",
                        )
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Light Mode")
@Composable
fun PrescriptionDetailPreview() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        PrescriptionDetailContent(
            state = Prescription(
                id = 1,
                date = LocalDate.now(),
                treatment = Treatment(
                    medication = Medication(
                        name = "Doliprane"
                    ),
                    posology = "1 comprimé",
                    frequency = "3 fois par jour",
                    duration = Duration(
                        startDate = LocalDate.now(),
                        endDate = LocalDate.now().plusDays(7
                        )
                    )
                )
            )
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Dark Mode")
@Composable
fun PrescriptionDetailDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        PrescriptionDetailContent(
            state = Prescription(
                id = 1,
                date = LocalDate.now(),
                treatment = Treatment(
                    medication = Medication(
                        name = "Doliprane"
                    ),
                    posology = "1 comprimé",
                    frequency = "3 fois par jour",
                    duration = Duration(
                        startDate = LocalDate.now(),
                        endDate = LocalDate.now().plusDays(7
                        )
                    )
                )
            )
        )
    }
}
