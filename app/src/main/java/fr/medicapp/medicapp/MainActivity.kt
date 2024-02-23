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
import fr.medicapp.medicapp.ai.PrescriptionAI
import fr.medicapp.medicapp.database.ObjectBox
import fr.medicapp.medicapp.database.converter.LocalDateTypeAdapter
import fr.medicapp.medicapp.database.entity.medication.MedicationEntity
import fr.medicapp.medicapp.model.medication.Medication
import fr.medicapp.medicapp.mozilla.GeckoManager
import fr.medicapp.medicapp.ui.navigation.RootNavGraph
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import java.time.LocalDate
import java.lang.reflect.Type
import com.google.gson.reflect.TypeToken
import fr.medicapp.medicapp.api.address.APIAddressClient
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Activité principale de l'application.
 * Elle initialise la base de données et l'IA de prescription, et définit le contenu de l'activité.
 */
class MainActivity : ComponentActivity() {

    suspend fun main() {
        //Connexion à ObjectBox
        val boxStore = ObjectBox.getInstance(this)
        val store = boxStore.boxFor(MedicationEntity::class.java)

        // Création du client HTTP
        val client = OkHttpClient()

        // Création de la requête pour récupérer les données de l'API
        val request = Request.Builder()
            .url("https://app1.plogun.fr/api/get_med/all")
            .build()

        // Exécution de la requête
        val response = client.newCall(request).execute()

        if (!response.isSuccessful) {
            println("Échec de la requête : ${response.code}")
            return
        }

        // Récupération du corps de la réponse
        val responseBody = response.body?.string()

        responseBody?.let { it ->
            // Désérialisation des données JSON en liste de Medication
            val medications: List<Medication> = Json.decodeFromString(it)

            // Conversion des données en liste de MedicamentEntity
            val medicationEntities: List<MedicationEntity> = medications.map { it.convert(this) }

            //Ajout des données dans ObjectBox
            store.put(medicationEntities)
        }
    }


    /**
     * Crée l'activité. Cette méthode est appelée lorsque l'activité est créée.
     *
     * @param savedInstanceState Si l'activité est recréée après avoir été tuée par le système, c'est le bundle qui contient l'état de l'activité. Sinon, c'est null.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val boxStore = ObjectBox.getInstance(this)

        Thread {
            val store = boxStore.boxFor(MedicationEntity::class.java)

            if (store.all.isEmpty()) {

                val apiService = APIAddressClient().apiServiceGuewen
                var page: Int = 1
                var continuer = true
                while (continuer) {

                    val response = apiService.getAllMeds(page).execute()
                    Log.d("ObjectBox", "Page $page")
                    if (response.isSuccessful) {
                        val allMedsJsonArray = response.body()!!

                        val gson = GsonBuilder()
                            .registerTypeAdapter(LocalDate::class.java, LocalDateTypeAdapter())
                            .create()

                        val listType: Type = object : TypeToken<List<Medication>>() {}.type

                        val medications: List<Medication> =
                            gson.fromJson(allMedsJsonArray, listType)

                        // Mapper et convertir les données en MedicationEntity
                        val medicationEntities = medications.map { it.convert(this) }

                        // Enregistrer les MedicationEntity dans ObjectBox
                        val store = boxStore.boxFor(MedicationEntity::class.java)
                        store.put(medicationEntities)
                        page += 1

                    } else {
                        continuer = false
                        val errorBody = response.errorBody()?.string()
                        Log.e("Error de guegue", errorBody ?: "Error body is null")
                        Log.e("ObjectBox", "Error while fetching medications")
                    }
                }
            }

        }.start()

        // Initialisation de l'IA de prescription
        PrescriptionAI.getInstance(this)

        // Initialisation du moteur de rendu Gecko
        GeckoManager.getInstances(this)

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
