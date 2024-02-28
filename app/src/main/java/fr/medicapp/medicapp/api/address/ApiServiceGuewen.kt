package fr.medicapp.medicapp.api.address

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServiceGuewen {
    @GET("get_med/all/{page}")
    fun getAllMeds(
        @Path("page") page: Int
    ): Call<String>

    @GET("get_doc/nom/{nom}")
    fun getDocByNom(
        @Path("nom") nom: String
    ): Call<String>

    @GET("get_all_med")
    fun getTotalMedications(): Call<String>

    @GET("get_doc/id/{id}")
    fun getDocById(
        @Path("id") id: Long
    ): Call<String>

    @GET("get_doc/onlyName/{name}")
    fun getLittleDocByNom(
        @Path("name") name: String
    ): Call<String>

    @GET("get_relations")
    fun getRelations(): Call<String>
}
