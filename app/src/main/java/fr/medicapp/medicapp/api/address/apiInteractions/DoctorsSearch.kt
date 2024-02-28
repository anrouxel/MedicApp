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
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DoctorsSearch {

    fun searchDoctor(doctor: String, callback: (List<Doctor>) -> Unit) {

        cancelSearch()

        val apiService = APIAddressClient().apiServiceGuewen
        val call = apiService.getDocByNom(doctor)

        currentCall = call

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (call != currentCall){
                    //requête obsolète
                    closeConnection()
                    return
                }
                if (response.isSuccessful) {
                    val allDocsJsonArray = response.body()!!
                    Log.d("Docteurs", "Les docteurs sont trouvés !")

                    val gson = GsonBuilder()
                        .registerTypeAdapter(LocalDate::class.java, LocalDateTypeAdapter())
                        .create()

                    val doctorListType: Type = object : TypeToken<List<Doctor>>() {}.type
                    val doctors: List<Doctor> = gson.fromJson(allDocsJsonArray, doctorListType)

                    Log.d("Docteurs", "Les docteurs sont trouvés ! : ${doctors.size}")
                    currentCall = null
                    // Callback on the main thread
                    closeConnection()
                    return callback(doctors)
                } else {
                    Log.d("Docteurs", "Les docteurs ne sont pas trouvés !")
                    currentCall = null
                    closeConnection()
                    // Callback on the main thread
                    return callback(emptyList())
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                if (call != currentCall) {
                    //requête obsolète
                    closeConnection()
                    return
                }
                Log.e("GuegueApi", "Error during API call: ${t.message}")
                if (t.message == "timeout") {
                    Log.d("Gueguemagouille", "je relance la requête")
                    Handler(Looper.getMainLooper()).post {
                        // Relancer la requête avec les mêmes paramètres
                        searchDoctor(doctor, callback)
                    }
                } else {
                    currentCall = null
                    closeConnection()
                    return callback(emptyList())
                }
            }
        })
    }

    private fun cancelSearch() {
        // Annuler la recherche en cours, s'il y en a une
        currentCall?.cancel()
    }

    fun closeConnection() {
        okHttpClient.dispatcher.executorService.shutdown()
    }

    companion object {
        private var currentCall: Call<String>? = null
        private var okHttpClient = OkHttpClient.Builder().build()
    }
}
