package fr.medicapp.medicapp.ui.screen.prescription

import android.graphics.drawable.Icon
import android.net.Uri
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import fr.medicapp.medicapp.ui.components.button.ReusableButton
import fr.medicapp.medicapp.ui.components.button.ReusableOutlinedDateRangePickerButton
import fr.medicapp.medicapp.ui.components.button.ReusableOutlinedSearchButton
import fr.medicapp.medicapp.ui.components.button.ReusableOutlinedTextFieldButton
import fr.medicapp.medicapp.ui.components.card.ReusableElevatedCard
import fr.medicapp.medicapp.ui.components.screen.Edit
import fr.medicapp.medicapp.ui.components.textfield.ReusableOutlinedTextField
import fr.medicapp.medicapp.utils.createImageFile
import fr.medicapp.medicapp.viewModel.SharedPrescriptionEditViewModel

@OptIn(ExperimentalPermissionsApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PrescriptionEditInformation(
    viewModel: SharedPrescriptionEditViewModel,
    onClick: () -> Unit
) {
    val state = viewModel.sharedState.collectAsState().value[0]
    val context = LocalContext.current

    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )

    var hasImage by remember { mutableStateOf(false) }

    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            hasImage = uri != null
            imageUri = uri

            if (imageUri != null) {
            }
        }
    )

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success: Boolean ->
            hasImage = success

            if (imageUri != null && success) {
            }
        }
    )

    Edit(
        title = "Ajouter une prescription",
        bottomText = "Suivant",
        onClick = onClick,
        enabled = state.medication != null && state.prescriptionInformation.posology.isNotEmpty() && state.prescriptionInformation.frequency.isNotEmpty() && state.duration != null
    ) {
        Column {
            ReusableElevatedCard {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text("Scanner une ordonnance", color = MaterialTheme.colorScheme.primary)

                    Row(
                        horizontalArrangement = SpaceBetween
                    ) {
                        ReusableButton(
                            text = "Appareil photo",
                            icon = Icons.Default.PhotoCamera,
                        ) {
                            if (cameraPermissionState.status.isGranted) {
                                imageUri = context.createImageFile()
                                cameraLauncher.launch(imageUri)
                            } else {
                                cameraPermissionState.launchPermissionRequest()
                            }
                        }

                        Spacer(modifier = Modifier.padding(7.dp))

                        ReusableButton(
                            text = "Galerie",
                            icon = Icons.Default.Photo,
                        ) {
                            imagePicker.launch("image/*")
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
