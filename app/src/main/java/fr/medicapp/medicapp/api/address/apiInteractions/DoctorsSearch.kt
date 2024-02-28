package fr.medicapp.medicapp.api.address.apiInteractions

import android.os.HandlerThread
import android.util.Log
import com.google.gson.GsonBuilder
import fr.medicapp.medicapp.api.address.APIAddressClient
import fr.medicapp.medicapp.database.converter.LocalDateTypeAdapter
import java.time.LocalDate
import java.lang.reflect.Type
import com.google.gson.reflect.TypeToken
import fr.medicapp.medicapp.model.prescription.Doctor
import android.os.Handler
import java.io.IOException
import android.os.Looper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DoctorsSearch {

    private val handlerThread = HandlerThread("DoctorSearchThread").apply { start() }
    private val backgroundHandler = Handler(handlerThread.looper)

    private var currentSearchJob: Job? = null

    fun searchDoctor(doctor: String, callback: (List<Doctor>) -> Unit) {
        cancelSearch()

        // = CoroutineScope(Dispatchers.IO).launch {
            backgroundHandler.post {
                val apiService = APIAddressClient().apiServiceGuewen
                try {
                    val response = apiService.getDocByNom(doctor).execute()
                    if (response.isSuccessful) {
                        val allDocsJsonArray = response.body()!!
                        Log.d("Docteurs", "Les docteurs sont trouvés !")

                        val gson = GsonBuilder()
                            .registerTypeAdapter(LocalDate::class.java, LocalDateTypeAdapter())
                            .create()

                        val doctorListType: Type = object : TypeToken<List<Doctor>>() {}.type
                        val doctors: List<Doctor> = gson.fromJson(allDocsJsonArray, doctorListType)

                        Log.d("Docteurs", "Les docteurs sont trouvés ! : ${doctors.size}")

                        // Callback on the main thread
                        Handler(Looper.getMainLooper()).post { callback(doctors) }
                    } else {
                        Log.d("Docteurs", "Les docteurs ne sont pas trouvés !")
                        // Callback on the main thread
                        Handler(Looper.getMainLooper()).post { callback(emptyList()) }
                    }
                } catch (e: IOException) {
                    Log.e("Network", "Error during API call: ${e.message}")
                    // Callback on the main thread
                    Handler(Looper.getMainLooper()).post { callback(emptyList()) }
                }
            }
        //}
    }

    fun cancelSearch() {
        // Annuler la recherche en cours, s'il y en a une
        currentSearchJob?.cancel()
    }
}
