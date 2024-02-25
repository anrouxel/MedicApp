package fr.medicapp.medicapp.model.medication.relationship.crossRef

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["has_smr_opinion_id", "transparency_commission_opinion_links_id"])
data class HasSmrOpinionTransparencyCommissionOpinionLinksCrossRef(
    @ColumnInfo(name = "has_smr_opinion_id")
    var hasSmrOpinionId: Long = 0L,

    @ColumnInfo(name = "transparency_commission_opinion_links_id")
    var transparencyCommissionOpinionId: Long = 0L
)
