package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "HasAsmrOpinion")
data class HasAsmrOpinion(
    @PrimaryKey val cisCode : String,
    val hasDossierCode : String,
    val evaluationReason : String,
    val transparencyCommissionOpinionDate : Date?,
    val asmrValue : String,
    val asmrLabel : String,
    val transparencyCommissionOpinionLinks : List<TransparencyCommissionOpinionLinks>
)