package fr.medicapp.medicapp.api.address

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


class APIAddressClient {
    private val BASE_URL = "https://api-adresse.data.gouv.fr"

    private val gson: Gson by lazy {
        GsonBuilder().setLenient().create()
    }

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val apiService: APIAddressService by lazy {
        retrofit.create(APIAddressService::class.java)
    }

    private val retrofitGuewen = Retrofit.Builder()
        .baseUrl("https://app1.plogun.fr/api/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    val apiServiceGuewen: ApiServiceGuewen by lazy {
        retrofitGuewen.create(ApiServiceGuewen::class.java)
    }



}