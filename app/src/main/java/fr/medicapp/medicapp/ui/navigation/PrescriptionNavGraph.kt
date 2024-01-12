package fr.medicapp.medicapp.ui.navigation

import android.app.NotificationManager
import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import de.coldtea.smplr.smplralarm.alarmNotification
import de.coldtea.smplr.smplralarm.channel
import de.coldtea.smplr.smplralarm.smplrAlarmCancel
import de.coldtea.smplr.smplralarm.smplrAlarmSet
import de.coldtea.smplr.smplralarm.smplrAlarmUpdate
import fr.medicapp.medicapp.R
import fr.medicapp.medicapp.ai.PrescriptionAI
import fr.medicapp.medicapp.database.AppDatabase
import fr.medicapp.medicapp.entity.MedicationEntity
import fr.medicapp.medicapp.model.Doctor
import fr.medicapp.medicapp.model.Treatment
import fr.medicapp.medicapp.repository.MedicationRepository
import fr.medicapp.medicapp.repository.NotificationRepository
import fr.medicapp.medicapp.repository.SideEffectRepository
import fr.medicapp.medicapp.repository.TreatmentRepository
import fr.medicapp.medicapp.ui.prescription.EditPrescription
import fr.medicapp.medicapp.ui.prescription.Prescription
import fr.medicapp.medicapp.ui.prescription.PrescriptionMainMenu
import fr.medicapp.medicapp.ui.prescription.TestConsultation
import fr.medicapp.medicapp.ui.prescription.TestOrdonnance
import fr.medicapp.medicapp.viewModel.SharedAddPrescriptionViewModel
import okhttp3.internal.notifyAll
import java.io.File
import java.text.SimpleDateFormat
import java.time.DayOfWeek
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
            val repositoryMedication = MedicationRepository(db.medicationDAO())

            var result: MutableList<Treatment> = mutableListOf()
            Thread {
                val treatments = repository.getAll().map { it.toTreatment(repositoryMedication) }

                result.clear()
                result.addAll(treatments)

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
            val repositoryMedication = MedicationRepository(db.medicationDAO())
            val repositorySideEffect = SideEffectRepository(db.sideEffectDAO())
            val repositoryNotification = NotificationRepository(db.notificationDAO())

            var result: MutableList<Treatment> = mutableListOf()

            Thread {
                result.clear()
                val treatmentEntity = repository.getOne(id)
                if (treatmentEntity != null) {
                    result.add(treatmentEntity.toTreatment(repositoryMedication))
                }
            }.start()

            val prescription = remember {
                result
            }

            var context = LocalContext.current

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
                    Thread {
                        prescription.map { treatment ->  treatment?.toEntity() }.forEach { treatment ->
                            if (treatment != null) {
                                Log.d("TAG", "Deleting treatment: $treatment")

                                val sideEffects = repositorySideEffect.getByMedicament(treatment.id)

                                sideEffects.forEach { sideEffect ->
                                    repositorySideEffect.delete(sideEffect)
                                }

                                val notifications = repositoryNotification.getByMedicament(treatment.id)

                                notifications.forEach { notification ->
                                    notification.alarms.forEach { alarm ->
                                        smplrAlarmCancel(context) {
                                            requestCode { alarm }
                                        }
                                    }

                                    repositoryNotification.delete(notification)
                                }

                                repository.delete(treatment)
                            }
                        }
                    }.start()
                    navController.navigate(PrescriptionRoute.Main.route) {
                        popUpTo(PrescriptionRoute.Prescription.route) {
                            inclusive = true
                        }
                    }
                },
                onUpdate = {treatmentId, notificationValue ->
                    Thread {
                        val treatment = repository.getOne(treatmentId).toTreatment(repositoryMedication)

                        if (treatment != null) {
                            val notifications = repositoryNotification.getByMedicament(treatment.id)

                            Log.d("TAG", "Updating treatment: $notificationValue")

                            notifications.forEach { notification ->
                                notification.alarms.forEach { alarm ->
                                    smplrAlarmUpdate(context) {
                                        requestCode { alarm }
                                        isActive { notificationValue }
                                    }
                                }
                            }

                            treatment.notification = notificationValue
                            repository.update(treatment.toEntity())
                        }
                    }.start()
                }
            )
        }

        composable(route = PrescriptionRoute.AddPrescription.route) {
            val viewModel =
                it.sharedViewModel<SharedAddPrescriptionViewModel>(navController = navController)
            val state by viewModel.sharedState.collectAsStateWithLifecycle()

            val db = AppDatabase.getInstance(LocalContext.current)
            val repository = TreatmentRepository(db.treatmentDAO())
            val repositoryMedication = MedicationRepository(db.medicationDAO())

            var result: MutableList<MedicationEntity> = mutableListOf()

            Thread {
                result.clear()
                result.addAll(repositoryMedication.getAllWithoutNotTreadings())
            }.start()

            val medication = remember {
                result
            }

            val cameraPermissionState = rememberPermissionState(
                android.Manifest.permission.CAMERA
            )

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
                    }
                }
            )

            val cameraLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.TakePicture(),
                onResult = { success: Boolean ->
                    hasImage = success

                    if (imageUri != null && success) {
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
                    }
                }
            )

            if (loading.value) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Traitement de l'ordonnance en cours...")

                        Spacer(modifier = Modifier.height(10.dp))

                        //LinearProgressIndicator()
                        CircularProgressIndicator()
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
                        state.treatments.forEach {
                            Log.d("TEST", it.notification.toString())
                        }

                        Log.d("TEST", "Notification: ${state.treatments.any { it.notification }}")

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
                    medications = medication
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