package fr.medicapp.medicapp.model.gson

import fr.medicapp.medicapp.model.medication.HasSmrOpinionInformation
import fr.medicapp.medicapp.model.medication.TransparencyCommissionOpinionLinks
import fr.medicapp.medicapp.model.medication.relationship.HasSmrOpinion
import java.time.LocalDate


data class HasSmrOpinionGSON(
    var cisCode: Long = 0L,

    var hasDossierCode: String = "",

    var evaluationReason: String = "",

    var transparencyCommissionOpinionDate: LocalDate? = null,

    var smrValue: String = "",

    var smrLabel: String = "",

    var transparencyCommissionOpinionLinks: MutableList<TransparencyCommissionOpinionLinks> = mutableListOf()
) {
    fun toHasSmrOpinion(): HasSmrOpinion {
        return HasSmrOpinion(
            hasSmrOpinionInformation = HasSmrOpinionInformation(
                cisCode = cisCode,
                hasDossierCode = hasDossierCode,
                evaluationReason = evaluationReason,
                transparencyCommissionOpinionDate = transparencyCommissionOpinionDate,
                smrValue = smrValue,
                smrLabel = smrLabel
            ),
            transparencyCommissionOpinionLinks = transparencyCommissionOpinionLinks
        )
    }
}