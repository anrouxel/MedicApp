package fr.medicapp.medicapp.model.medication.relationship

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import fr.medicapp.medicapp.model.medication.HasAsmrOpinionInformation
import fr.medicapp.medicapp.model.medication.TransparencyCommissionOpinionLinks
import fr.medicapp.medicapp.model.medication.relationship.crossRef.HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRef

data class HasAsmrOpinion(
    @Embedded val hasAsmrOpinion: HasAsmrOpinionInformation,

    @Relation(
        parentColumn = "has_asmr_opinion_id",
        entityColumn = "transparency_commission_opinion_id",
        associateBy = Junction(HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRef::class)
    )
    var transparencyCommissionOpinionLinks: MutableList<TransparencyCommissionOpinionLinks> = mutableListOf()
)
