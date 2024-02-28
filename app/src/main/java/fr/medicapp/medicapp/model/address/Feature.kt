package fr.medicapp.medicapp.model.address

import com.google.gson.annotations.SerializedName

data class Feature(
    @SerializedName("type") val type: String,
    @SerializedName("geometry") val geometry: Geometry,
    @SerializedName("properties") val properties: Properties
)
