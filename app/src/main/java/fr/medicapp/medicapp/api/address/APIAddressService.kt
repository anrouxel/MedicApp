package fr.medicapp.medicapp.api.address

import fr.medicapp.medicapp.model.address.Address
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIAddressService {
    @GET("search")
    suspend fun getPosition(@Query("q") q: String): Response<Address>
}
