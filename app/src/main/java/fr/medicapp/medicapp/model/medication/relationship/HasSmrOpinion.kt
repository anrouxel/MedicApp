package fr.medicapp.medicapp.model.medication.relationship

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import fr.medicapp.medicapp.model.medication.HasSmrOpinionInformation
import fr.medicapp.medicapp.model.medication.TransparencyCommissionOpinionLinks
import fr.medicapp.medicapp.model.medication.relationship.crossRef.HasSmrOpinionTransparencyCommissionOpinionLinksCrossRef

data class HasSmrOpinion(
    @Embedded val hasSmrOpinion: HasSmrOpinionInformation,

    @Relation(
        parentColumn = "has_smr_opinion_id",
        entityColumn = "transparency_commission_opinion_id",
        associateBy = Junction(HasSmrOpinionTransparencyCommissionOpinionLinksCrossRef::class)
    )
    var transparencyCommissionOpinionLinks: MutableList<TransparencyCommissionOpinionLinks> = mutableListOf()
)
