package fr.medicapp.medicapp.api.address

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServiceGuewen {
    @GET("get_med/all/{page}")
    fun getAllMeds(
        @Path("page") page: Int
    ): Call<String>
}