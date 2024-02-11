package fr.medicapp.medicapp

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.google.gson.GsonBuilder
import dagger.hilt.android.AndroidEntryPoint
import fr.medicapp.medicapp.ai.PrescriptionAI
import fr.medicapp.medicapp.database.LocalDateTypeAdapter
import fr.medicapp.medicapp.database.ObjectBox
import fr.medicapp.medicapp.database.entity.medication.MedicationEntity
import fr.medicapp.medicapp.entity.medication.MedicationEntity_
import fr.medicapp.medicapp.ui.navigation.RootNavGraph
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import java.time.LocalDate

/**
 * Activité principale de l'application.
 * Elle initialise la base de données et l'IA de prescription, et définit le contenu de l'activité.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * Crée l'activité. Cette méthode est appelée lorsque l'activité est créée.
     *
     * @param savedInstanceState Si l'activité est recréée après avoir été tuée par le système, c'est le bundle qui contient l'état de l'activité. Sinon, c'est null.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialisation de la base de données
        val boxStore = ObjectBox.getInstance(this)

        Thread {
            val store = boxStore.boxFor(MedicationEntity::class.java)

            val gson = GsonBuilder()
                .registerTypeAdapter(LocalDate::class.java, LocalDateTypeAdapter())
                .create()

            val monoprost = "{\"cisCode\":67303969,\"name\":\"MONOPROST 50 MICROGRAMMES/ML, COLLYRE EN SOLUTION EN RÉCIPIENT UNIDOSE\",\"pharmaceuticalForm\":\"COLLYRE EN SOLUTION\",\"administrationRoutes\":[\"OPHTALMIQUE\"],\"marketingAuthorizationStatus\":\"AUTORISATION ACTIVE\",\"marketingAuthorizationProcedureType\":\"PROCÉDURE DÉCENTRALISÉE\",\"commercializationStatus\":\"COMMERCIALISÉE\",\"marketingAuthorizationDate\":\"2013-02-14\",\"bdmStatus\":\"\",\"europeanAuthorizationNumber\":\"\",\"holders\":[\"THEA\"],\"enhancedMonitoring\":false,\"medicationCompositions\":[{\"cisCode\":67303969,\"pharmaceuticalElementDesignation\":\"COLLYRE\",\"substanceCode\":33474,\"substanceName\":\"LATANOPROST\",\"substanceDosage\":\"50 MICROGRAMMES\",\"dosageReference\":\"1 ML DE COLLYRE EN SOLUTION\",\"componentNature\":\"SA\",\"linkNumber\":1}],\"medicationPresentations\":[{\"cisCode\":67303969,\"ciP7Code\":2673826,\"presentationLabel\":\"30 RÉCIPIENT(S) UNIDOSE(S) POLYÉTHYLÈNE BASSE DENSITÉ (PEBD) SUREMBALLÉE(S)/SURPOCHÉE(S) DE 0,2 ML\",\"presentationStatus\":\"PRÉSENTATION ACTIVE\",\"presentationCommercializationStatus\":\"DÉCLARATION DE COMMERCIALISATION\",\"commercializationDeclarationDate\":\"2014-01-02\",\"ciP13Code\":3400926738266,\"approvalForCommunities\":true,\"reimbursementRates\":[65],\"priceWithoutHonoraryInEuro\":8.78,\"priceWithHonoraryInEuro\":9.80,\"priceHonoraryInEuro\":1.02,\"reimbursementIndications\":\"\"}],\"genericGroups\":[],\"hasSmrOpinions\":[{\"cisCode\":67303969,\"hasDossierCode\":\"CT-17190\",\"evaluationReason\":\"RENOUVELLEMENT D'INSCRIPTION (CT)\",\"transparencyCommissionOpinionDate\":\"2019-02-06\",\"smrValue\":\"IMPORTANT\",\"smrLabel\":\"LE SERVICE MÉDICAL RENDU PAR MONOPROST 50 ΜG/ML, COLLYRE EN SOLUTION EN RÉCIPIENT UNIDOSE ET EN FLACON MULTIDOSE RESTE IMPORTANT DANS LES INDICATIONS DE L\\u0092AMM.\",\"transparencyCommissionOpinionLinks\":[{\"hasDossierCode\":\"CT-17190\",\"commissionOpinionLink\":\"https://www.has-sante.fr/jcms/c_2905736\"}]}],\"hasAsmrOpinions\":[{\"cisCode\":67303969,\"hasDossierCode\":\"CT-12748\",\"evaluationReason\":\"INSCRIPTION (CT)\",\"transparencyCommissionOpinionDate\":\"2013-05-29\",\"asmrValue\":\"V\",\"asmrLabel\":\"MONOPROST 50 ΜG/ML, COLLYRE EN SOLUTION EN RÉCIPIENT UNIDOSE, N\\u0092APPORTE PAS D\\u0092AMÉLIORATION DU SERVICE MÉDICAL RENDU (ASMR V) PAR RAPPORT À XALATAN 0,005 %, COLLYRE EN SOLUTION.\",\"transparencyCommissionOpinionLinks\":[{\"hasDossierCode\":\"CT-12748\",\"commissionOpinionLink\":\"https://www.has-sante.fr/jcms/c_1615556\"}]}],\"importantInformations\":[],\"prescriptionDispensingConditions\":[{\"cisCode\":67303969,\"prescriptionDispensingCondition\":\"LISTE I\"}],\"pharmaceuticalSpecialties\":[{\"cisCode\":67303969,\"cip13Code\":\"\",\"statusCode\":2,\"statusLabel\":\"TENSION D'APPROVISIONNEMENT\",\"startDate\":\"2023-02-07\",\"updateDate\":\"2024-01-16\",\"returnToDate\":null,\"ansmSiteLink\":\"HTTPS://ANSM.SANTE.FR/DISPONIBILITES-DES-PRODUITS-DE-SANTE/MEDICAMENTS/MONOPROST-50-MICROGRAMMES-ML-COLLYRE-EN-SOLUTION-EN-RECIPIENT-UNIDOSE-LATANOPROST\"}]}"

            Log.d("ObjectBox", "Monoprost : $monoprost")

            val medication : MedicationEntity = gson.fromJson(monoprost, MedicationEntity::class.java)

            Log.d("ObjectBox", "MedicationEntity : $medication")

            // si le cisCode n'existe pas dans la base de données, on l'ajoute
            if (store.query().equal(MedicationEntity_.cisCode, medication.cisCode).build().find().isEmpty()) {
                Log.d("ObjectBox", "Ajout de Monoprost")
                store.put(medication)
                val medicationTmp = store.get(medication.id)
                medicationTmp.medicationCompositions.addAll(medication.medicationCompositions)
                medicationTmp.medicationPresentations.addAll(medication.medicationPresentations)
                medicationTmp.genericGroups.addAll(medication.genericGroups)
                medicationTmp.hasSmrOpinions.addAll(medication.hasSmrOpinions)
                medicationTmp.hasAsmrOpinions.addAll(medication.hasAsmrOpinions)
                medicationTmp.importantInformations.addAll(medication.importantInformations)
                medicationTmp.prescriptionDispensingConditions.addAll(medication.prescriptionDispensingConditions)
                medicationTmp.pharmaceuticalSpecialties.addAll(medication.pharmaceuticalSpecialties)
                store.put(medicationTmp)
            }

            Log.d("ObjectBox", "MedicationEntity : " + store.query().build().find().toString())
        }.start()

        // Initialisation de l'IA de prescription
        PrescriptionAI.getInstance(this)

        // Définition du contenu de l'activité
        setContent {
            var theme by remember { mutableStateOf(EUYellowColorShema) }
            // Utilisation du thème de l'application
            MedicAppTheme(
                theme = theme
            ) {
                // Création du graphe de navigation racine
                RootNavGraph(
                    navController = rememberNavController(),
                    theme = theme,
                    onThemeChange = { theme = it }
                )
            }
        }
    }
}
