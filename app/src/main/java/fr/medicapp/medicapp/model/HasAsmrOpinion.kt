package fr.medicapp.medicapp.model

import java.util.Date

data class HasAsmrOpinion(
    val cisCode : String,
    val hasDossierCode : String,
    val evaluationReason : String,
    val transparencyCommissionOpinionDate : Date?,
    val asmrValue : String,
    val asmrLabel : String,
    val transparencyCommissionOpinionLinks : List<TransparencyCommissionOpinionLinks>
)