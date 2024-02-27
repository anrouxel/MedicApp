package fr.medicapp.medicapp.api.address

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

fun interface ApiServiceGuewen {
    @GET("get_med/all/{page}")
    fun getAllMeds(
        @Path("page") page: Int
    ): Call<String>

    @GET("get_doc/nom/{nom}")
    fun getDocByNom(
        @Path("nom") nom: String
    ): Call<String>
}