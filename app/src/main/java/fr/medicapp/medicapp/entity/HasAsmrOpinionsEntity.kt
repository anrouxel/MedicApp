package fr.medicapp.medicapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "HasAsmrOpinions", foreignKeys =
arrayOf(ForeignKey(entity = MedicationEntity::class, parentColumns = ["Id"], childColumns = ["MedicationId"]))
)
data class HasAsmrOpinionsEntity(

    /**
     * L'identifiant unique de l'opinion ASMR.
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
     * AsmrValue
     */
    @ColumnInfo(name = "AsmrValue")
    val asmrValue: String,

    /**
     * AsmrLabel
     */
    @ColumnInfo(name = "AsmrLabel")
    val asmrLabel: String,

    /**
     * id medicament
     */
    @ColumnInfo(name = "MedicationId")
    val medicationId: String


)
