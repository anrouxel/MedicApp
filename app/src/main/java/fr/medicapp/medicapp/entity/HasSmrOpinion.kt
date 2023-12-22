package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 * Table nommée HasSmrOpinion.
 * Définie par le cisCode, elle est utilisée par Medication.
 * Elle utilise une liste de la table TransparencyCommissionOpinionLinks.
 * */
@Entity(tableName = "HasSmrOpinion")
data class HasSmrOpinion(
    @PrimaryKey val cisCode: String,
    val hasDossierCode: String,
    val evaluationReason: String,
    val transparencyCommissionOpinionDate: Date?,
    val smrValue: String,
    val smrLabel: String,
    val transparencyCommissionOpinionLinks: List<TransparencyCommissionOpinionLinks>
)
