package fr.medicapp.medicapp.ui.prescription

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import fr.medicapp.medicapp.entity.medication.MedicationEntity
import fr.medicapp.medicapp.entity.TreatmentEntity
import fr.medicapp.medicapp.model.Doctor
import fr.medicapp.medicapp.model.Prescription
import fr.medicapp.medicapp.ui.prescription.EditPrescription.AddButton
import fr.medicapp.medicapp.ui.prescription.EditPrescription.TreatmentCard
import fr.medicapp.medicapp.ui.theme.EUGreen100
import fr.medicapp.medicapp.ui.theme.EUGreen40
import fr.medicapp.medicapp.ui.theme.EUPurple80
import fr.medicapp.medicapp.ui.theme.EURed100

/**
 * Cette fonction affiche l'écran d'édition de prescription avec des informations spécifiques.
 *
 * @param doctors La liste des docteurs disponibles.
 * @param onCancel La fonction à exécuter lorsque l'utilisateur annule l'édition.
 * @param onConfirm La fonction à exécuter lorsque l'utilisateur confirme l'édition.
 * @param onCameraPicker La fonction à exécuter lorsque l'utilisateur choisit d'importer une ordonnance via la caméra.
 * @param onCameraPermissionRequested La fonction à exécuter lorsque l'utilisateur demande l'accès à la caméra.
 * @param onImagePicker La fonction à exécuter lorsque l'utilisateur choisit d'importer une ordonnance via la galerie d'images.
 * @param cameraPermissionState L'état de la permission d'accès à la caméra.
 * @param prescription La prescription à éditer.
 * @param medications La liste des médicaments disponibles.
 */
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun EditPrescription(
    doctors: List<Doctor>,
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    onCameraPicker: () -> Unit,
    onCameraPermissionRequested: () -> Unit,
    onImagePicker: () -> Unit,
    cameraPermissionState: PermissionState,
    prescription: Prescription,
) {
    var darkmode : Boolean = isSystemInDarkTheme()
    var errorDialogOpen = remember { mutableStateOf(false) }

    if (errorDialogOpen.value) {
        AlertDialog(
            onDismissRequest = {
                errorDialogOpen.value = false
            },
            title = {
                Text("Erreur")
            },
            text = {
                Text("Veuillez remplir tous les champs")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        errorDialogOpen.value = false
                    }
                ) {
                    Text("OK")
                }
            }
        )
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                colors = topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                ),
                title = {
                    Text(
                        "ORD ${prescription.id}",
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Unspecified
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                        .weight(1f)
                ) {
                    Button(
                        onClick = {
                            onCancel()
                        },
                        shape = RoundedCornerShape(20),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = EURed100,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(3f)
                    ) {
                        Text(
                            text = "Annuler",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.weight(0.3f))

                    Button(
                        onClick = {
                            if (prescription.treatments.size > 0 && prescription.treatments.all { it.medication != null && it.posology != "" && it.quantity != "" && it.renew != "" && it.duration != null }) {
                                onConfirm()
                            } else {
                                errorDialogOpen.value = true
                            }
                        },
                        shape = RoundedCornerShape(20),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = EUGreen100,
                            contentColor = Color.White,
                            disabledContainerColor = EUGreen40,
                            disabledContentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(3f)
                    ) {
                        Text(
                            text = "Valider",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(10.dp)
                .verticalScroll(rememberScrollState()),
        ) {

            Spacer(modifier = Modifier.height(15.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                prescription.treatments.forEachIndexed { index, treatment ->
                    TreatmentCard(
                        treatment = treatment,
                        onRemove = {
                            prescription.treatments.removeAt(index)
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }

                AddButton(
                    text = "Ajouter un traitement",
                    icone = Icons.Filled.Add,
                    color = EUPurple80,
                    onClick = {
                        prescription.treatments.add(TreatmentEntity())
                        Log.d(
                            "EditPrescription",
                            "AddButton: prescription.treatments = ${prescription.treatments}"
                        )
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                AddButton(
                    text = "Importer une ordonnance (Galerie)",
                    icone = Icons.Filled.Upload,
                    color = EUPurple80,
                    onClick = onImagePicker
                )

                Spacer(modifier = Modifier.height(10.dp))

                if (cameraPermissionState.status.isGranted) {
                    AddButton(
                        text = "Importer une ordonnance (Camera)",
                        icone = Icons.Filled.Upload,
                        color = EUPurple80,
                        onClick = onCameraPicker
                    )
                } else {
                    AddButton(
                        text = "Autoriser l'accès à la caméra",
                        icone = Icons.Filled.Upload,
                        color = EUPurple80,
                        onClick = onCameraPermissionRequested
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun EditPrescriptionPreview() {
    EditPrescription(
        prescription = Prescription(),
        doctors = listOf(
            Doctor(
                firstName = "Jean",
                lastName = "Dupont"
            ),
            Doctor(
                firstName = "John",
                lastName = "Dupont"
            ),
            Doctor(
                firstName = "Mark",
                lastName = "Dupont"
            ),
        ),
        onCancel = {},
        onConfirm = {},
        onCameraPicker = {},
        onCameraPermissionRequested = {},
        onImagePicker = {},
        cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA),
    )
}