package fr.medicapp.medicapp.model.medication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.time.LocalDate

@Entity
data class ImportantInformation(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "important_information_id")
    val id: Long = 0L,

    @SerializedName("CISCode")
    var cisCode: Long = 0L,

    @SerializedName("SafetyInformationStartDate")
    var safetyInformationStartDate: LocalDate? = null,

    @SerializedName("SafetyInformationEndDate")
    var safetyInformationEndDate: LocalDate? = null,

    @ColumnInfo(name = "medication_id")
    var medicationId: Long = 0L
)
