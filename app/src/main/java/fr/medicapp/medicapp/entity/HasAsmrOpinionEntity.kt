package fr.medicapp.medicapp.entity

import java.time.LocalDate

data class HasAsmrOpinionEntity(
    var cisCode: Long,
    var hasDossierCode: String,
    var evaluationReason: String,
    var transparencyCommissionOpinionDate: LocalDate?,
    var asmrValue: String,
    var asmrLabel: String,
    var transparencyCommissionOpinionLinks: MutableList<TransparencyCommissionOpinionLinksEntity>
)
