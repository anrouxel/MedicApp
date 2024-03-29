package fr.medicapp.medicapp.api.address.apiInteractions

import android.content.Context
import android.content.SharedPreferences
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import androidx.annotation.WorkerThread
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import fr.medicapp.medicapp.api.address.APIAddressClient
import fr.medicapp.medicapp.database.converter.LocalDateTypeAdapter
import fr.medicapp.medicapp.database.repositories.medication.MedicationRepository
import fr.medicapp.medicapp.database.repositories.relations.RelationRepository
import fr.medicapp.medicapp.model.gson.MedicationGSON
import fr.medicapp.medicapp.model.gson.RelationGSON
import kotlinx.coroutines.DelicateCoroutinesApi
import java.lang.reflect.Type
import java.time.LocalDate

class MedicationDownload(
    private val context: Context
) {
    /**
     * Thread en background pour le téléchargement des données
     */
    private var gBackgroundThread: HandlerThread = HandlerThread("MedicationDownload")

    /**
     * Gestionnaire des tâches en background
     */
    private var gHandler: Handler

    /**
     * Accès aux donnés sauvegardées dans le téléphone
     */
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("medicapp", Context.MODE_PRIVATE)

    /**
     * Initialisation du thread en background
     */
    init {
        gBackgroundThread.start()
        gHandler = Handler(gBackgroundThread.looper)

        // Lancement du téléchargement des médicaments en background
        gHandler.post {
            if (sharedPreferences.getInt("totalMedication", 0) == 0) {
                getTotalData()
            }
            downloadRelations()
            download()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    @WorkerThread
    fun download() {
        val medicationRepository = MedicationRepository(context)

        val apiService = APIAddressClient().apiServiceGuewen
        var page = sharedPreferences.getInt("medicationPage", 1)
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
                MedicationRepository(context).getAll().count().let {
                    updateDownloadCountInSharedPreferences(it)
                }
                // Enregistrer les MedicationEntity
                medicationRepository.insert(medicationEntities)
                page += 1
                sharedPreferences.edit().putInt("medicationPage", page).apply()
            } else if (response.code() == 409) {
                continuer = false
                Log.d("ObjectBox", "Fin du téléchargement des médicaments")
                sharedPreferences.edit().putBoolean("isDataDownloaded", true).apply()
                page += 1
                sharedPreferences.edit().putInt("medicationPage", page).apply()
            } else {
                continuer = false
                val errorBody = response.errorBody()?.string()
                Log.e("Error de guegue", errorBody ?: "Error body is null")
                Log.e("ObjectBox", "Error while fetching medications")
            }
        }
        gBackgroundThread.quitSafely()
    }

    @WorkerThread
    fun downloadRelations() {

        val dl = sharedPreferences.getBoolean("isRelationDownloaded", false)
        if (!dl) {
            Log.d("GuegueApi", "On télécharge les relations")
            val apiService = APIAddressClient().apiServiceGuewen
            val response = apiService.getRelations().execute()
            if (response.isSuccessful) {
                val allRelJsonArray = response.body()!!
                Log.d("GuegueApi", "Les données sont téléchargées")
                val gson = GsonBuilder()
                    .registerTypeAdapter(LocalDate::class.java, LocalDateTypeAdapter())
                    .create()

                val listType: Type = object : TypeToken<List<RelationGSON>>() {}.type

                val relations: List<RelationGSON> =
                    gson.fromJson(allRelJsonArray, listType)

                // Mapper et convertir les données en MedicationEntity
                val relationsEntities = relations.map { it.toRelations() }

                // Enregistrer les RelationsEntity
                RelationRepository(context).insert(relationsEntities)
                Log.d("GuegueApi", "Les relations sont enregistrées")
                sharedPreferences.edit().putBoolean("isRelationDownloaded", true).apply()
            } else {
                Log.e("ObjectBox", "Error while fetching relations")
            }
        }
    }

    @WorkerThread
    fun getTotalData() {
        val sharedPreferences = context.getSharedPreferences("medicapp", Context.MODE_PRIVATE)
        val apiService = APIAddressClient().apiServiceGuewen
        val response = apiService.getTotalMedications().execute()
        if (response.isSuccessful) {
            sharedPreferences.edit().putInt("totalMedication", response.body()!!.toInt()).apply()
        } else {
            Log.e("ObjectBox", "Error while fetching total medications")
        }
    }

    private fun updateDownloadCountInSharedPreferences(countDownload: Int) {
        sharedPreferences.edit().putInt("downloadCount", countDownload).apply()
    }
}
