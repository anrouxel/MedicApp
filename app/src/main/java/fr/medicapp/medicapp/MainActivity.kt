package fr.medicapp.medicapp

import android.content.Context
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
import com.google.gson.reflect.TypeToken
import fr.medicapp.medicapp.ai.PrescriptionAI
import fr.medicapp.medicapp.api.address.APIAddressClient
import fr.medicapp.medicapp.database.converter.LocalDateTypeAdapter
import fr.medicapp.medicapp.database.repositories.UserRepository
import fr.medicapp.medicapp.database.repositories.medication.MedicationRepository
import fr.medicapp.medicapp.model.gson.MedicationGSON
import fr.medicapp.medicapp.model.medication.relationship.Medication
import fr.medicapp.medicapp.mozilla.GeckoManager
import fr.medicapp.medicapp.ui.navigation.RootNavGraph
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.internal.wait
import java.lang.reflect.Type
import java.time.LocalDate

/**
 * Activité principale de l'application.
 * Elle initialise la base de données et l'IA de prescription, et définit le contenu de l'activité.
 */
class MainActivity : ComponentActivity() {

    /**
     * Crée l'activité. Cette méthode est appelée lorsque l'activité est créée.
     *
     * @param savedInstanceState Si l'activité est recréée après avoir été tuée par le système, c'est le bundle qui contient l'état de l'activité. Sinon, c'est null.
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isUser = this.getSharedPreferences("medicapp", Context.MODE_PRIVATE).getBoolean("isUserCreated", false)

        Thread {
            val medicationRepository = MedicationRepository(this)

            Log.d("ObjectBox", "Medication count: ${medicationRepository.getAll().count()}")

            if (medicationRepository.getAll().isEmpty()) {

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

                        val listType: Type = object : TypeToken<List<MedicationGSON>>() {}.type

                        val medications: List<MedicationGSON> =
                            gson.fromJson(allMedsJsonArray, listType)

                        // Mapper et convertir les données en MedicationEntity
                        val medicationEntities = medications.map { it.toMedication() }

                        // Enregistrer les MedicationEntity dans ObjectBox
                        medicationRepository.insert(medicationEntities)
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
                    onThemeChange = { theme = it },
                    isUser = isUser
                )
            }
        }
    }
}
