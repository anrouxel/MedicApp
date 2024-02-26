package fr.medicapp.medicapp.model.address

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("type") val type: String,
    @SerializedName("version") val version: String,
    @SerializedName("features") val features: List<Feature>,
    @SerializedName("attribution") val attribution: String,
    @SerializedName("licence") val licence: String,
    @SerializedName("query") val query: String,
    @SerializedName("limit") val limit: Int
)
