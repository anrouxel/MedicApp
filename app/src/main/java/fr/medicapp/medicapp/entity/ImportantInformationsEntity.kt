package fr.medicapp.medicapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "ImportantInformations")
data class ImportantInformationsEntity(

    /**
     * L'identifiant unique des informations importantes.
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
     * Date de début des informations de sécurité
     */
    @ColumnInfo(name = "SafetyInformationStartDate")
    val safetyInformationStartDate: LocalDate?,

    /**
     * Date de fin des informations de sécurité
     */
    @ColumnInfo(name = "SafetyInformationEndDate")
    val safetyInformationEndDate: LocalDate?,

    /**
     * Lien vers les informations de sécurité
     */
    @ColumnInfo(name = "SafetyInformationLink")
    val safetyInformationLink: String,

    /**
     * L'id du médicament
     */
    @ColumnInfo(name = "MedicationId")
    val medicationId: String
)
