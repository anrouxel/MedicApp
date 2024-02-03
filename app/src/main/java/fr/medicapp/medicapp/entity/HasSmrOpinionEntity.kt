package fr.medicapp.medicapp.entity

import java.time.LocalDate

data class HasSmrOpinionEntity(
    var cisCode: Long,
    var hasDossierCode: String,
    var evaluationReason: String,
    var transparencyCommissionOpinionDate: LocalDate?,
    var smrValue: String,
    var smrLabel: String,
    var transparencyCommissionOpinionLinks: MutableList<TransparencyCommissionOpinionLinksEntity>
)
