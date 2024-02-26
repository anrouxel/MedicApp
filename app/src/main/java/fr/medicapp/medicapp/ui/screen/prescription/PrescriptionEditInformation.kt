package fr.medicapp.medicapp.ui.screen.prescription

import android.graphics.drawable.Icon
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.ai.PrescriptionAI
import fr.medicapp.medicapp.ui.components.button.ReusableOutlinedDateRangePickerButton
import fr.medicapp.medicapp.ui.components.button.ReusableOutlinedSearchButton
import fr.medicapp.medicapp.ui.components.button.ReusableOutlinedTextFieldButton
import fr.medicapp.medicapp.ui.components.card.ReusableElevatedCard
import fr.medicapp.medicapp.ui.components.screen.Edit
import fr.medicapp.medicapp.ui.components.textfield.ReusableOutlinedTextField
import fr.medicapp.medicapp.viewModel.SharedPrescriptionEditViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PrescriptionEditInformation(
    viewModel: SharedPrescriptionEditViewModel,
    onClick: () -> Unit
) {
    val state = viewModel.sharedState.collectAsState().value[0]
    val context = LocalContext.current
    val imageUri = context.create
    Edit(
        title = "Ajouter une prescription",
        bottomText = "Suivant",
        onClick = onClick,
        enabled = state.medication != null && state.prescriptionInformation.posology.isNotEmpty() && state.prescriptionInformation.frequency.isNotEmpty() && state.duration != null
    ) {
        Column {

            ReusableElevatedCard {
                Column (
                    modifier = Modifier.padding(10.dp)
                ) {

                    Text("Scanner une ordonnance", color = MaterialTheme.colorScheme.primary )

                    Row (
                        horizontalArrangement = SpaceBetween
                    ){
                        Button(onClick = {

                            launcher.launch(null)
                        }) {
                            Row() {
                                Text("Appareil photo")

                                Icon(
                                    imageVector = Icons.Default.PhotoCamera,
                                    contentDescription = "Appareil photo"
                                )
                            }
                        }
                        Button(onClick = {}) {
                            Row {
                                Text("Galerie")

                                Icon(
                                    imageVector = Icons.Default.Photo,
                                    contentDescription = "Galerie"
                                )
                            }
                        }
                    }
                }
            }

                Spacer(modifier = Modifier.padding(7.dp))
            ReusableElevatedCard {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    ReusableOutlinedTextFieldButton(
                        value = "",
                        label = "Docteur",
                        warnings = false,
                        onClick = {}
                    )
                }
            }

            Spacer(modifier = Modifier.padding(7.dp))

            ReusableElevatedCard {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    ReusableOutlinedSearchButton(
                        options = {
                            viewModel.searchMedication(it, context)
                        },
                        value = state.medication,
                        label = "Médicament",
                        warnings = state.medication == null
                    ) {
                        viewModel.updateMedication(it, context)
                    }

                    Spacer(modifier = Modifier.padding(10.dp))

                    ReusableOutlinedTextField(
                        value = state.prescriptionInformation.posology,
                        onValueChange = {
                            viewModel.updatePosology(it)
                        },
                        warnings = state.prescriptionInformation.posology.isEmpty(),
                        label = "Posologie"
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    ReusableOutlinedTextField(
                        value = state.prescriptionInformation.frequency,
                        onValueChange = {
                            viewModel.updateFrequency(it)
                        },
                        warnings = state.prescriptionInformation.frequency.isEmpty(),
                        label = "Fréquence"
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    ReusableOutlinedDateRangePickerButton(
                        value = state.duration,
                        label = "Durée",
                        warnings = state.duration == null,
                        onSelected = {
                            viewModel.updateDuration(it)
                        }
                    )
                }
            }
        }
    }
}
