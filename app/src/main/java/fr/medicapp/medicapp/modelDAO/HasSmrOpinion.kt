package fr.medicapp.medicapp.modelDAO

import java.util.Date

data class HasSmrOpinion(
    val cisCode : String,
    val hasDossierCode : String,
    val evaluationReason : String,
    val transparencyCommissionOpinionDate: Date?,
    val smrValue: String,
    val smrLabel:String,
    val transparencyCommissionOpinionLinks: List<TransparencyCommissionOpinionLinks>
)