package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 * Table nommée HasAsmrOpinion.
 * Définie par le cisCode, elle est utilisée par la Medication.
 * Elle possède une liste de la table TransparencyCommissionOpinionLinks
 * */
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