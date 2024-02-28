package fr.medicapp.medicapp.model.medication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.time.LocalDate

@Entity
data class HasSmrOpinionInformation(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "has_smr_opinion_id")
    val id: Long = 0L,

    @SerializedName("CISCode")
    var cisCode: Long = 0L,

    @SerializedName("HasDossierCode")
    var hasDossierCode: String = "",

    @SerializedName("EvaluationReason")
    var evaluationReason: String = "",

    @SerializedName("TransparencyCommissionOpinionDate")
    var transparencyCommissionOpinionDate: LocalDate? = null,

    @SerializedName("SmrValue")
    var smrValue: String = "",

    @SerializedName("SmrLabel")
    var smrLabel: String = "",

    @ColumnInfo(name = "medication_id")
    var medicationId: Long = 0L
)
