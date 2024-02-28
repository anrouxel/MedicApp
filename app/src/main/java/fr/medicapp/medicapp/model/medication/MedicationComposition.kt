package fr.medicapp.medicapp.model.medication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class MedicationComposition(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "medication_composition_id")
    val id: Long = 0L,

    @SerializedName("CISCode")
    var cisCode: Long = 0L,

    @SerializedName("PharmaceuticalElementDesignation")
    var pharmaceuticalElementDesignation: String = "",

    @SerializedName("SubstanceCode")
    var substanceCode: Long = 0L,

    @SerializedName("SubstanceName")
    var substanceName: String = "",

    @SerializedName("SubstanceDosage")
    var substanceDosage: String = "",

    @SerializedName("DosageReference")
    var dosageReference: String = "",

    @SerializedName("ComponentNature")
    var componentNature: String = "",

    @SerializedName("LinkNumber")
    var linkNumber: Int? = null,

    @ColumnInfo(name = "medication_id")
    var medicationId: Long = 0L
)
