package fr.medicapp.medicapp.model.medication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class HasSmrOpinionInformation(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "has_smr_opinion_id")
    val id: Long = 0L,

    var cisCode: Long = 0L,

    var hasDossierCode: String = "",

    var evaluationReason: String = "",

    var transparencyCommissionOpinionDate: LocalDate? = null,

    var smrValue: String = "",

    var smrLabel: String = "",
)