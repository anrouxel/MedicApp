package fr.medicapp.medicapp.model.medication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.time.LocalDate

@Entity
data class PharmaceuticalSpecialty(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pharmaceutical_specialty_id")
    val id: Long = 0L,

    @SerializedName("CISCode")
    var cisCode: Long = 0L,

    @SerializedName("Cip13Code")
    var cip13Code: String = "",

    @SerializedName("StatusCode")
    var statusCode: Int = 0,

    @SerializedName("StatusLabel")
    var statusLabel: String = "",

    @SerializedName("StartDate")
    var startDate: LocalDate? = null,

    @SerializedName("UpdateDate")
    var updateDate: LocalDate? = null,

    @SerializedName("ReturnToDate")
    var returnToDate: LocalDate? = null,

    @SerializedName("AnsmSiteLink")
    var ansmSiteLink: String = "",

    @ColumnInfo(name = "medication_id")
    var medicationId: Long = 0L
)