package fr.medicapp.medicapp.api.address.medication

import android.content.Context
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import androidx.annotation.WorkerThread
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import fr.medicapp.medicapp.api.address.APIAddressClient
import fr.medicapp.medicapp.database.converter.LocalDateTypeAdapter
import fr.medicapp.medicapp.database.repositories.medication.MedicationRepository
import fr.medicapp.medicapp.model.gson.MedicationGSON
import java.lang.reflect.Type
import java.time.LocalDate

class MedicationDownload(
    private val context: Context
) {
    /**
     * Thread en background pour le téléchargement des données
     */
    private lateinit var gBackgroundThread: HandlerThread

    /**
     * Gestionnaire des tâches en background
     */
    private lateinit var gHandler: Handler

    /**
     * Initialisation du thread en background
     */
    init {
        gBackgroundThread = HandlerThread("MedicationDownload")
        gBackgroundThread.start()
        gHandler = Handler(gBackgroundThread.looper)

        //Lancement du téléchargement des médicaments en background
        gHandler.post {
            download()
        }
    }

    @WorkerThread
    fun download() {

        val medicationRepository = MedicationRepository(context)

        val apiService = APIAddressClient().apiServiceGuewen
        var page = 1
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
}