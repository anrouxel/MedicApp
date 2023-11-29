package fr.medicapp.medicapp.entity

import androidx.room.Entity
import java.util.Date

@Entity(tableName = "HasAsmrOpinion")
data class HasAsmrOpinion(
    val cisCode : String,
    val hasDossierCode : String,
    val evaluationReason : String,
    val transparencyCommissionOpinionDate : Date?,
    val asmrValue : String,
    val asmrLabel : String,
    val transparencyCommissionOpinionLinks : List<TransparencyCommissionOpinionLinks>
)