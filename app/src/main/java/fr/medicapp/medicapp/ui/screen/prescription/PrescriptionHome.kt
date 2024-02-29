package fr.medicapp.medicapp.ui.screen.prescription

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.database.repositories.relations.RelationRepository
import fr.medicapp.medicapp.model.prescription.Doctor
import fr.medicapp.medicapp.model.prescription.relationship.Prescription
import fr.medicapp.medicapp.ui.components.button.FloatingActionButtons
import fr.medicapp.medicapp.ui.components.button.ReusableElevatedCardButton
import fr.medicapp.medicapp.ui.components.card.CardContent
import fr.medicapp.medicapp.ui.components.modal.AlertModal
import fr.medicapp.medicapp.ui.components.modal.ConfirmReportModal
import fr.medicapp.medicapp.ui.components.screen.Home
import fr.medicapp.medicapp.ui.theme.EUPurpleColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun PrescriptionHome(
    prescriptions: List<Prescription>,
    onPrescriptionClick: (Long) -> Unit = {},
    onAddPrescriptionClick: () -> Unit = {}
) {
    val noPrescriptionDialog = remember { mutableStateOf(false) }
    var alertRedondantOpen by remember { mutableStateOf(false) }
    var alertRelationOpen by remember { mutableStateOf(false) }
    var isReportModalOpen by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val relations = RelationRepository(context)
    val sharedPrefs = context.getSharedPreferences("medicapp", Context.MODE_PRIVATE)
    val isNewMedicationAdded = sharedPrefs
        .getBoolean("isNewMedicationAdded", false)

    Home(
        title = "Prescriptions",
        floatingActionButtons = {
            FloatingActionButtons(
                buttons = listOf(
                    {
                        if (prescriptions.isEmpty()) {
                            noPrescriptionDialog.value = true

                        } else {
                            isReportModalOpen = true
                        }
                    } to {
                        Icon(
                            imageVector = Icons.Default.FileOpen,
                            contentDescription = "Bouton pour générer un rapport",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    onAddPrescriptionClick to {
                        Icon(
                            imageVector = Icons.Default.DocumentScanner,
                            contentDescription = "Bouton pour ajouter une prescription",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                )
            )
        }
    ) {
        when {
            prescriptions.isEmpty() -> {
                NoPrescriptionAvailable()
            }

            prescriptions.size > 1 -> {
                LaunchedEffect(prescriptions) {
                    val lastMedocCompo =
                        prescriptions.last().medication?.medicationCompositions?.map { it.substanceCode }

                    alertRedondantOpen = prescriptions.dropLast(1).any { prescription ->
                        prescription.medication?.medicationCompositions?.any {
                            it.substanceCode in (lastMedocCompo ?: emptyList())
                        } == true
                    } && isNewMedicationAdded

                    alertRelationOpen = withContext(Dispatchers.IO) {
                        controlRelation(prescriptions, relations)
                    } && isNewMedicationAdded

                }
                PrescriptionList(
                    prescriptions = prescriptions,
                    onPrescriptionClick = onPrescriptionClick
                )
            }

            else -> {
                PrescriptionList(
                    prescriptions = prescriptions,
                    onPrescriptionClick = onPrescriptionClick
                )
            }
        }
        if (isReportModalOpen) {
            ConfirmReportModal(onDismissRequest = { isReportModalOpen = false },
                noPrescriptionDialog = noPrescriptionDialog,
                onConfirmation = {
                    isReportModalOpen = false
                }
            )
        }
        if (alertRedondantOpen) {
            AlertModal(
                title = "Redondance des principes actifs",
                content = "Attention, le médicament que vous venez de rajouter contient le même principe actif qu'un déjà présent dans vos traitements",
                dismissText = "",
                confirmText = "Compris",
                onConfirm = {
                    alertRedondantOpen = false
                    with(sharedPrefs.edit()) {
                        putBoolean("isNewMedicationAdded", false)
                        apply()
                    }
                }
            )
        }
        if (alertRelationOpen) {
            AlertModal(
                title = "Relation médicamenteuse dangereuse",
                content = "Attention, le médicament que vous venez de rajouter contient un principe actif qui peut interagir avec un autre médicament déjà présent dans vos traitements.",
                dismissText = "",
                confirmText = "Compris",
                onConfirm = {
                    alertRelationOpen = false
                    with(sharedPrefs.edit()) {
                        putBoolean("isNewMedicationAdded", false)
                        apply()
                    }
                }
            )
        }
        if (noPrescriptionDialog.value) {
            AlertModal(
                title = "Erreur",
                content = "Vous n'avez pas de prescription pour le moment.",
                dismissText = "Annuler",
                confirmText = "Ok",
                onDismissRequest = {
                    noPrescriptionDialog.value = false
                },
                onConfirm = {
                    noPrescriptionDialog.value = false
                }
            )
        }
    }
}

@Composable
fun PrescriptionList(
    prescriptions: List<Prescription>,
    onPrescriptionClick: (Long) -> Unit = {}
) {
    Column {
        prescriptions.forEachIndexed { index, prescription ->
            PrescriptionItem(
                prescription = prescription,
                onPrescriptionClick = onPrescriptionClick
            )

            if (index != prescriptions.size - 1) {
                Spacer(modifier = Modifier.padding(10.dp))
            }
        }
    }
}

@Composable
fun PrescriptionItem(
    prescription: Prescription,
    onPrescriptionClick: (Long) -> Unit
) {
    ReusableElevatedCardButton(
        onClick = { onPrescriptionClick(prescription.prescriptionInformation.id) }
    ) {
        CardContent(
            title = prescription.medication?.medicationInformation?.name ?: "Médicament inconnu",
            description = prescription.doctor?.firstName ?: "Docteur inconnu",
        )
    }
}

@Composable
fun NoPrescriptionAvailable() {
    Text(
        text = "Vous n'avez pas d'ordonnances.\n" + "Pour en ajouter, cliquez sur le bouton en bas",
        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
        fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
        fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(align = Alignment.CenterVertically)
    )
}

suspend fun controlRelation(
    prescriptions: List<Prescription>,
    relationRepo: RelationRepository
): Boolean {
    Log.d("GuegueTest", "ça rentre dans la méthode")
    val substanceNames =
        prescriptions.last().medication?.medicationCompositions?.map { it.substanceName }
    if (substanceNames.isNullOrEmpty()) {
        return withContext(Dispatchers.IO) {
            false
        }
    }

    val relationsNoAccent =
        relationRepo.getAll().map { it.relationInfo.substance }.map { it.removeAccents() }
    val relationsNorm = relationRepo.getAll().map { it.relationInfo.substance }
    Log.d("Relation", relationsNorm.toString())
    Log.d("Relation", substanceNames.toString())
    for (substance in substanceNames) {
        val substanceCheck = substance.removeAccents()
        if (relationsNoAccent.contains(substanceCheck)) {
            //On cherche si il y a intéraction avec un autre produit
            //Interactions est une liste de substance
            Log.d("GuegueTest", "ça rentre dans la boucle = relation trouvée")
            val index = relationsNoAccent.indexOf(substanceCheck)
            Log.d("GuegueTest", "Substance qui pose problème : ${relationsNorm[index]}")
            val interactions = relationRepo.getBySubstance(relationsNorm[index])
                .map { it.interactions }
                .flatten()
                .map { it.substance }
                .map { it.removeAccents() }


            Log.d("GuegueTest", "voici les interactions : $interactions")

            for (prescription in prescriptions.dropLast(1)) {
                Log.d("GuegueTest", "taille de prescription : ${prescriptions.dropLast(1).size}")
                Log.d("GuegueTest", "ça rentre dans la boucle2")
                val prescriptionSubstanceNames =
                    prescription.medication?.medicationCompositions?.map { it.substanceName }
                        ?.map { it.removeAccents() }
                if (!prescriptionSubstanceNames.isNullOrEmpty()) {
                    Log.d("GuegueTest", "prescriptionSubstanceNames : $prescriptionSubstanceNames")
                    for (prescriptionSubstance in prescriptionSubstanceNames) {
                        if (interactions.contains(prescriptionSubstance)) {
                            Log.d("GuegueTest", "aie aie aie")
                            return withContext(Dispatchers.IO) {
                                true
                            }
                        }
                    }
                }
            }

        }
    }
    return withContext(Dispatchers.IO) {
        false
    }
}

fun String.removeAccents(): String {
    return this
        .replace("é".uppercase(), "E")
        .replace("è".uppercase(), "E")
        .replace("ê".uppercase(), "E")
        .replace("à".uppercase(), "A")
        .replace("â".uppercase(), "A")
        .replace("ù".uppercase(), "U")
        .replace("û".uppercase(), "U")
        .replace("ô".uppercase(), "O")
        .replace("î".uppercase(), "I")
        .replace("ï".uppercase(), "I")
        .replace("ç".uppercase(), "C")
        .replace("ë".uppercase(), "E")
        .replace("ü".uppercase(), "U")
        .replace("ö".uppercase(), "O")
}

@RequiresApi(Build.VERSION_CODES.Q)
@Preview(name = "Light Theme", showSystemUi = true)
@Composable
private fun PrescriptionHomePreview() {
    val prescription = Prescription(
        doctor = Doctor(
            firstName = "Mottu"
        ),
    )

    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        PrescriptionHome(
            prescriptions = listOf(prescription)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Preview(name = "Dark Theme", showSystemUi = true)
@Composable
private fun PrescriptionHomeDarkPreview() {
    val prescription = Prescription(
        doctor = Doctor(
            firstName = "Mottu"
        )
    )

    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        PrescriptionHome(
            prescriptions = listOf(prescription)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Preview(name = "Light Theme - No Prescription", showSystemUi = true)
@Composable
private fun NoPrescriptionAvailablePreview() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        PrescriptionHome(
            prescriptions = emptyList()
        )
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Preview(name = "Dark Theme - No Prescription", showSystemUi = true)
@Composable
private fun NoPrescriptionAvailableDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        PrescriptionHome(
            prescriptions = emptyList()
        )
    }
}
