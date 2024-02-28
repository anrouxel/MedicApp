package fr.medicapp.medicapp.model.gson

import fr.medicapp.medicapp.model.medication.HasAsmrOpinionInformation
import fr.medicapp.medicapp.model.medication.TransparencyCommissionOpinionLinks
import fr.medicapp.medicapp.model.medication.relationship.HasAsmrOpinion
import java.time.LocalDate

data class HasAsmrOpinionGSON(

    var cisCode: Long = 0L,

    var hasDossierCode: String = "",

    var evaluationReason: String = "",

    var transparencyCommissionOpinionDate: LocalDate? = null,

    var asmrValue: String = "",

    var asmrLabel: String = "",

    var transparencyCommissionOpinionLinks: MutableList<TransparencyCommissionOpinionLinks> = mutableListOf()
) {
    fun toHasAsmrOpinion(): HasAsmrOpinion {
        return HasAsmrOpinion(
            hasAsmrOpinionInformation = HasAsmrOpinionInformation(
                cisCode = cisCode,
                hasDossierCode = hasDossierCode,
                evaluationReason = evaluationReason,
                transparencyCommissionOpinionDate = transparencyCommissionOpinionDate,
                asmrValue = asmrValue,
                asmrLabel = asmrLabel
            ),
            transparencyCommissionOpinionLinks = transparencyCommissionOpinionLinks
        )
    }
}