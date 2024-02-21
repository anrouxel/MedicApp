package fr.medicapp.medicapp.model.address

import com.google.gson.annotations.SerializedName

data class Properties(
    @SerializedName("label") val label: String,
    @SerializedName("score") val score: Double,
    @SerializedName("housenumber") val houseNumber: String,
    @SerializedName("id") val id: String,
    @SerializedName("type") val type: String,
    @SerializedName("name") val name: String,
    @SerializedName("postcode") val postcode: String,
    @SerializedName("citycode") val cityCode: String,
    @SerializedName("x") val x: Double,
    @SerializedName("y") val y: Double,
    @SerializedName("city") val city: String,
    @SerializedName("context") val context: String,
    @SerializedName("importance") val importance: Double,
    @SerializedName("street") val street: String
)
