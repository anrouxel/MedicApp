package fr.medicapp.medicapp.ui.navigation

import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
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
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import fr.medicapp.medicapp.ai.PrescriptionAI
import fr.medicapp.medicapp.database.AppDatabase
import fr.medicapp.medicapp.entity.TreatmentEntity
import fr.medicapp.medicapp.model.Doctor
import fr.medicapp.medicapp.model.Treatment
import fr.medicapp.medicapp.repository.TreatmentRepository
import fr.medicapp.medicapp.ui.prescription.EditPrescription
import fr.medicapp.medicapp.ui.prescription.Prescription
import fr.medicapp.medicapp.ui.prescription.PrescriptionMainMenu
import fr.medicapp.medicapp.ui.prescription.TestConsultation
import fr.medicapp.medicapp.ui.prescription.TestOrdonnance
import fr.medicapp.medicapp.viewModel.SharedAddPrescriptionViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

var ordonnances = listOf(
    TestOrdonnance(
        1,
        "Dr. MOTTU",
        "01/01/2023"
    ),
    TestOrdonnance(
        2,
        "Dr. CAZALAS",
        "02/10/2023"
    ),
    TestOrdonnance(
        3,
        "Dr. BERDJUGIN",
        "10/06/2023"
    )
)

var consultation = TestConsultation(
    "Dr. MOTTU",
    "22/11/2023",
    mutableListOf()
)

@OptIn(ExperimentalPermissionsApi::class)
@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.prescriptionNavGraph(
    navController: NavHostController
) {
    navigation(
        route = Graph.PRESCRIPTION,
        startDestination = PrescriptionRoute.Main.route,
    ) {
        composable(route = PrescriptionRoute.Main.route) {
            val db = AppDatabase.getInstance(LocalContext.current)
            val repository = TreatmentRepository(db.treatmentDAO())

            var result: MutableList<TreatmentEntity> = mutableListOf()
            Thread {
                result.clear()
                result.addAll(repository.getAll().toMutableList())

                result.forEach {
                    Log.d("TAG", it.toString())
                }
            }.start()

            val ordonnance = remember {
                result
            }

            PrescriptionMainMenu(
                ordonnances = ordonnance,
                onPrescription = { id ->
                    navController.navigate(PrescriptionRoute.Prescription.route.replace("{id}", id))
                },
                addPrescription = {
                    navController.navigate(PrescriptionRoute.AddPrescription.route)
                },
            )
        }

        composable(route = PrescriptionRoute.Prescription.route) {
            val id = it.arguments?.getString("id") ?: return@composable
            val db = AppDatabase.getInstance(LocalContext.current)
            val repository = TreatmentRepository(db.treatmentDAO())

            var result: MutableList<TreatmentEntity> = mutableListOf()

            Thread {
                result.clear()
                result.add(repository.getOne(id))
            }.start()

            val prescription = remember {
                result
            }

            Prescription(
                consultation = prescription,
                onClose = {
                    navController.popBackStack()
                },
            )
        }

        composable(route = PrescriptionRoute.AddPrescription.route) {
            val viewModel =
                it.sharedViewModel<SharedAddPrescriptionViewModel>(navController = navController)
            val state by viewModel.sharedState.collectAsStateWithLifecycle()

            val db = AppDatabase.getInstance(LocalContext.current)
            val repository = TreatmentRepository(db.treatmentDAO())

            val cameraPermissionState = rememberPermissionState(
                android.Manifest.permission.CAMERA
            )

            val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

            val context = LocalContext.current

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

                    if (imageUri != null) {
                        val image = InputImage.fromFilePath(context, imageUri!!)
                        recognizer.process(image)
                            .addOnSuccessListener { visionText ->
                                loading.value = true
                                Log.d("MLKit", visionText.text)
                                val prescriptionAI = PrescriptionAI.getInstance(context)
                                val prediction = prescriptionAI.analyse(visionText.text)

                                // ajouté les traitements à la liste
                                var treatment = Treatment()
                                prediction.forEach { (word, label) ->
                                    when {
                                        label.startsWith("B-") -> {
                                            if (label.removePrefix("B-") == "Drug" && treatment.medication.isNotEmpty()) {
                                                state.treatments.add(treatment)
                                                treatment = Treatment()
                                            }
                                            when (label.removePrefix("B-")) {
                                                "Drug" -> treatment.medication += " $word"
                                                "DrugQuantity" -> treatment.posology += " $word"
                                                "DrugForm" -> treatment.posology += " $word"
                                                "DrugFrequency" -> treatment.posology += " $word"
                                                "DrugDuration" -> treatment.posology += " $word"
                                            }
                                        }

                                        label.startsWith("I-") -> {
                                            when (label.removePrefix("I-")) {
                                                "Drug" -> treatment.medication += " $word"
                                                "DrugQuantity" -> treatment.posology += " $word"
                                                "DrugForm" -> treatment.posology += " $word"
                                                "DrugFrequency" -> treatment.posology += " $word"
                                                "DrugDuration" -> treatment.posology += " $word"
                                            }
                                        }
                                    }
                                }
                                if (treatment.medication.isNotEmpty()) {
                                    state.treatments.add(treatment)
                                }
                                loading.value = false
                            }
                            .addOnFailureListener { e ->
                                Log.d("MLKit", e.toString())
                            }
                    }
                }
            )

            val cameraLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.TakePicture(),
                onResult = { success: Boolean ->
                    hasImage = success

                    if (imageUri != null) {
                        val image = InputImage.fromFilePath(context, imageUri!!)
                        recognizer.process(image)
                            .addOnSuccessListener { visionText ->
                                loading.value = true
                                Log.d("MLKit", visionText.text)
                                val prescriptionAI = PrescriptionAI.getInstance(context)
                                val prediction = prescriptionAI.analyse(visionText.text)

                                // ajouté les traitements à la liste
                                var treatment = Treatment()
                                prediction.forEach { (word, label) ->
                                    when {
                                        label.startsWith("B-") -> {
                                            if (label.removePrefix("B-") == "Drug" && treatment.medication.isNotEmpty()) {
                                                state.treatments.add(treatment)
                                                treatment = Treatment()
                                            }
                                            when (label.removePrefix("B-")) {
                                                "Drug" -> treatment.medication += " $word"
                                                "DrugQuantity" -> treatment.posology += " $word"
                                                "DrugForm" -> treatment.posology += " $word"
                                                "DrugFrequency" -> treatment.posology += " $word"
                                                "DrugDuration" -> treatment.posology += " $word"
                                            }
                                        }

                                        label.startsWith("I-") -> {
                                            when (label.removePrefix("I-")) {
                                                "Drug" -> treatment.medication += " $word"
                                                "DrugQuantity" -> treatment.posology += " $word"
                                                "DrugForm" -> treatment.posology += " $word"
                                                "DrugFrequency" -> treatment.posology += " $word"
                                                "DrugDuration" -> treatment.posology += " $word"
                                            }
                                        }
                                    }
                                }
                                if (treatment.medication.isNotEmpty()) {
                                    state.treatments.add(treatment)
                                }
                                loading.value = false
                            }
                            .addOnFailureListener { e ->
                                Log.d("MLKit", e.toString())
                            }
                    }
                }
            )

            if (loading.value) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Chargement en cours...")
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
                        Thread {
                            repository.addAll(*state.treatments.map { it.toEntity() }
                                .toTypedArray())
                        }.start()
                        navController.popBackStack()
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

sealed class PrescriptionRoute(val route: String) {
    object Main : PrescriptionRoute(route = "mainmenu")
    object Prescription : PrescriptionRoute(route = "prescription/{id}")
    object AddPrescription : PrescriptionRoute(route = "add_prescriptions")
}