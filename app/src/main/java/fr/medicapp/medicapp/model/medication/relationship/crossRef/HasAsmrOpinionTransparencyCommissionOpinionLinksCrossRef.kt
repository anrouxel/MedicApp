package fr.medicapp.medicapp.model.medication.relationship.crossRef

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["has_asmr_opinion_id", "transparency_commission_opinion_id"])
data class HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRef (
    @ColumnInfo(name = "has_asmr_opinion_id")
    var hasAsmrOpinionId: Long = 0L,

    @ColumnInfo(name = "transparency_commission_opinion_id")
    var transparencyCommissionOpinionId: Long = 0L
)
