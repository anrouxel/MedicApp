package fr.medicapp.medicapp.entity

import androidx.room.Entity
import java.util.Date

@Entity(tableName = "HasSmrOpinion")
data class HasSmrOpinion(
    val cisCode : String,
    val hasDossierCode : String,
    val evaluationReason : String,
    val transparencyCommissionOpinionDate: Date?,
    val smrValue: String,
    val smrLabel:String,
    val transparencyCommissionOpinionLinks: List<TransparencyCommissionOpinionLinks>
)