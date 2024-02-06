package fr.medicapp.medicapp.ui.navigation

import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import fr.medicapp.medicapp.database.ObjectBox
import fr.medicapp.medicapp.entity.medication.MedicationEntity
import fr.medicapp.medicapp.entity.TreatmentEntity
import fr.medicapp.medicapp.model.Doctor
import fr.medicapp.medicapp.ui.prescription.EditPrescription
import fr.medicapp.medicapp.ui.prescription.Prescription
import fr.medicapp.medicapp.ui.prescription.PrescriptionMainMenu
import fr.medicapp.medicapp.viewModel.SharedAddPrescriptionViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

/**
 * Cette fonction construit le graphe de navigation pour les prescriptions.
 *
 * @param navController Le contrôleur de navigation.
 */
@OptIn(ExperimentalPermissionsApi::class)
@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.prescriptionNavGraph(
    navController: NavHostController
) {
    /**
     * Définit la navigation pour le graphe de prescription.
     */
    navigation(
        route = Graph.PRESCRIPTION,
        startDestination = PrescriptionRoute.Main.route,
    ) {
        /**
         * Composable pour la route principale de prescription.
         */
        composable(route = PrescriptionRoute.Main.route) {
            val store = ObjectBox.getInstance(LocalContext.current)
            val treatmentBox = store.boxFor(TreatmentEntity::class.java)

            val ordonnance = remember {
                treatmentBox.all
            }

            PrescriptionMainMenu(
                ordonnances = ordonnance,
                onPrescription = { id ->
                    navController.navigate(PrescriptionRoute.Prescription.route.replace("{id}", id))
                },
            ) {
                navController.navigate(PrescriptionRoute.AddPrescription.route)
            }
        }

        /**
         * Composable pour afficher une prescription spécifique.
         */
        composable(route = PrescriptionRoute.Prescription.route) {
            val id = it.arguments?.getString("id") ?: return@composable

            val store = ObjectBox.getInstance(LocalContext.current)

            val treatmentBox = store.boxFor(TreatmentEntity::class.java)

            val prescription = remember {
                treatmentBox.get(id.toLong())
            }

            Prescription(
                consultation = prescription,
                onClose = {
                    navController.navigate(PrescriptionRoute.Main.route) {
                        popUpTo(PrescriptionRoute.Prescription.route) {
                            inclusive = true
                        }
                    }
                },
                onRemove = {
                    treatmentBox.remove(prescription)
                    navController.navigate(PrescriptionRoute.Main.route) {
                        popUpTo(PrescriptionRoute.Prescription.route) {
                            inclusive = true
                        }
                    }
                }
            ) { treatmentId, notificationValue ->
                val treatment = treatmentBox.get(treatmentId.toLong())
                treatment.notification = notificationValue
                treatmentBox.put(treatment)
            }
        }

        /**
         * Composable pour ajouter une nouvelle prescription.
         */
        composable(route = PrescriptionRoute.AddPrescription.route) {
            val viewModel =
                it.sharedViewModel<SharedAddPrescriptionViewModel>(navController = navController)
            val state by viewModel.sharedState.collectAsStateWithLifecycle()

            val cameraPermissionState = rememberPermissionState(
                android.Manifest.permission.CAMERA
            )

            val context = LocalContext.current

            var store = ObjectBox.getInstance(context)

            val medicationStore = store.boxFor(MedicationEntity::class.java)
            val treatmentsStore = store.boxFor(TreatmentEntity::class.java)

            val medications = remember {
                medicationStore.all
            }

            var hasImage by remember { mutableStateOf(false) }

            var imageUri by remember { mutableStateOf<Uri?>(null) }

            var loading = remember {
                mutableStateOf(false)
            }

            val imagePicker = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent(),
                onResult = { uri: Uri? ->
                    hasImage = uri != null
                    imageUri = uri

                    /*if (imageUri != null) {
                        loading.value = true
                        val prescriptionAI = PrescriptionAI.getInstance(context)
                        val prediction = prescriptionAI.analyse(
                            imageUri!!,
                            onPrediction = { prediction ->
                                var treatment = TreatmentEntity()
                                prediction.forEach { (word, label) ->
                                    when {
                                        label.startsWith("B-") -> {
                                            if (label.removePrefix("B-") == "Drug" && treatment.query.isNotEmpty()) {
                                                treatment.query = treatment.query.trim()
                                                treatment.posology = treatment.posology.trim()
                                                state.treatments.add(treatment)
                                                treatment = Treatment()
                                            }
                                            when (label.removePrefix("B-")) {
                                                "Drug" -> treatment.query += " $word"
                                                "DrugQuantity" -> treatment.posology += " $word"
                                                "DrugForm" -> treatment.posology += " $word"
                                                "DrugFrequency" -> treatment.posology += " $word"
                                                "DrugDuration" -> treatment.renew += " $word"
                                            }
                                        }

                                        label.startsWith("I-") -> {
                                            when (label.removePrefix("I-")) {
                                                "Drug" -> treatment.query += " $word"
                                                "DrugQuantity" -> treatment.posology += " $word"
                                                "DrugForm" -> treatment.posology += " $word"
                                                "DrugFrequency" -> treatment.posology += " $word"
                                                "DrugDuration" -> treatment.renew += " $word"
                                            }
                                        }
                                    }
                                }
                                if (treatment.query.isNotEmpty()) {
                                    treatment.query = treatment.query.trim()
                                    treatment.posology = treatment.posology.trim()
                                    state.treatments.add(treatment)
                                }
                            },
                            onDismiss = {
                                loading.value = false
                            }
                        )
                    }*/
                }
            )

            val cameraLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.TakePicture(),
                onResult = { success: Boolean ->
                    hasImage = success

                    /*if (imageUri != null && success) {
                        loading.value = true
                        val prescriptionAI = PrescriptionAI.getInstance(context)
                        val prediction = prescriptionAI.analyse(
                            imageUri!!,
                            onPrediction = { prediction ->
                                var treatment = Treatment()
                                prediction.forEach { (word, label) ->
                                    when {
                                        label.startsWith("B-") -> {
                                            if (label.removePrefix("B-") == "Drug" && treatment.query.isNotEmpty()) {
                                                treatment.query = treatment.query.trim()
                                                treatment.posology = treatment.posology.trim()
                                                state.treatments.add(treatment)
                                                treatment = Treatment()
                                            }
                                            when (label.removePrefix("B-")) {
                                                "Drug" -> treatment.query += " $word"
                                                "DrugQuantity" -> treatment.posology += " $word"
                                                "DrugForm" -> treatment.posology += " $word"
                                                "DrugFrequency" -> treatment.posology += " $word"
                                                "DrugDuration" -> treatment.renew += " $word"
                                            }
                                        }

                                        label.startsWith("I-") -> {
                                            when (label.removePrefix("I-")) {
                                                "Drug" -> treatment.query += " $word"
                                                "DrugQuantity" -> treatment.posology += " $word"
                                                "DrugForm" -> treatment.posology += " $word"
                                                "DrugFrequency" -> treatment.posology += " $word"
                                                "DrugDuration" -> treatment.renew += " $word"
                                            }
                                        }
                                    }
                                }
                                if (treatment.query.isNotEmpty()) {
                                    treatment.query = treatment.query.trim()
                                    treatment.posology = treatment.posology.trim()
                                    state.treatments.add(treatment)
                                }
                            },
                            onDismiss = {
                                loading.value = false
                            }
                        )
                    }*/
                }
            )

            if (loading.value) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Traitement de l'ordonnance en cours...")

                        Spacer(modifier = Modifier.height(10.dp))

                        LinearProgressIndicator()
                    }
                }
            } else {
                EditPrescription(
                    prescription = state,
                    doctors = listOf(
                        Doctor(
                            lastName = "Doe",
                            firstName = "John",
                        ),
                        Doctor(
                            lastName = "Doe",
                            firstName = "Jane",
                        ),
                    ),
                    onCancel = {
                        navController.popBackStack()
                    },
                    onConfirm = {
                        treatmentsStore.put(state.treatments)

                        if (state.treatments.any { it.notification }) {
                            navController.navigate(NotificationRoute.AddNotification.route) {
                                popUpTo(PrescriptionRoute.AddPrescription.route) {
                                    inclusive = true
                                }
                            }
                        } else {
                            navController.navigate(PrescriptionRoute.Main.route) {
                                popUpTo(PrescriptionRoute.AddPrescription.route) {
                                    inclusive = true
                                }
                            }
                        }
                    },
                    onCameraPicker = {
                        imageUri = context.createImageFile()
                        cameraLauncher.launch(imageUri)
                    },
                    cameraPermissionState = cameraPermissionState,
                    onCameraPermissionRequested = {
                        cameraPermissionState.launchPermissionRequest()
                    },
                    onImagePicker = {
                        imagePicker.launch("image/*")
                    },
                )
            }
        }
    }
}

/**
 * Crée un fichier image temporaire et retourne son Uri.
 *
 * @return L'Uri du fichier image créé.
 */
fun Context.createImageFile(): Uri {
    val provider: String = "${applicationContext.packageName}.fileprovider"
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"

    val image = File.createTempFile(
        imageFileName,
        ".jpg",
        cacheDir
    ).apply {
        createNewFile()
    }

    return FileProvider.getUriForFile(applicationContext, provider, image)
}

/**
 * Récupère une instance partagée du ViewModel spécifié.
 * Cette fonction est utile pour partager des données entre plusieurs composables dans le même graphe de navigation.
 *
 * @param navController Le contrôleur de navigation.
 * @return Une instance partagée du ViewModel.
 */
@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController,
): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}

/**
 * Cette classe scellée définit les différentes routes pour les prescriptions.
 */
sealed class PrescriptionRoute(val route: String) {
    /**
     * Route pour la page principale des prescriptions.
     */
    object Main : PrescriptionRoute(route = "mainmenu")

    /**
     * Route pour afficher une prescription spécifique.
     */
    object Prescription : PrescriptionRoute(route = "prescription/{id}")

    /**
     * Route pour ajouter une nouvelle prescription.
     */
    object AddPrescription : PrescriptionRoute(route = "add_prescriptions")
}
