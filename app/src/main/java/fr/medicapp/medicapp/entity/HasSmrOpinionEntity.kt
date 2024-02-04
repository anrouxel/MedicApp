package fr.medicapp.medicapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "HasSmrOpinions")
data class HasSmrOpinionsEntity(

    /**
     * L'identifiant unique de l'opinion SMR.
     */
    @PrimaryKey
    @ColumnInfo(name = "Id")
    val id: String,

    /**
     * Le code CIS
     */

    @ColumnInfo(name = "CISCode")
    val cisCode: String,

    /**
     * HasDossierCode
     */
    @ColumnInfo(name = "HasDossierCode")
    val hasDossierCode: String,

    /**
     * EvaluationReason
     */
    @ColumnInfo(name = "EvaluationReason")
    val evaluationReason: String,

    /**
     * TransparencyCommissionOpinionDate
     */
    @ColumnInfo(name = "TransparencyCommissionOpinionDate")
    val transparencyCommissionOpinionDate: LocalDate?,

    /**
     * SmrValue
     */
    @ColumnInfo(name = "SmrValue")
    val smrValue: String,

    /**
     * SmrLabel
     */
    @ColumnInfo(name = "SmrLabel")
    val smrLabel: String,

    /**
     * id medicament
     */
    @ColumnInfo(name = "MedicationId")
    val medicationId: String


)
