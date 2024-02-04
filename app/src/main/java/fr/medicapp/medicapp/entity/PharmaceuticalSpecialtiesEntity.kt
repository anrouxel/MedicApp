package fr.medicapp.medicapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "PharmaceuticalSpecialties")
data class PharmaceuticalSpecialtiesEntity(

    /**
     * L'identifiant unique de la spécialité pharmaceutique.
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
     * Le code Cip13
     */
    @ColumnInfo(name = "Cip13Code")
    val cip13Code: String,

    /**
     * Le code de status
     */
    @ColumnInfo(name = "StatusCode")
    val statusCode: Int,

    /**
     * Le label du status
     */
    @ColumnInfo(name = "StatusLabel")
    val statusLabel: String,

    /**
     * Date de début
     */
    @ColumnInfo(name = "StartDate")
    val startDate: LocalDate?,

    /**
     * Date de mise à jour
     */
    @ColumnInfo(name = "UpdateDate")
    val updateDate: LocalDate?,

    /**
     * Date de retour
     */
    @ColumnInfo(name = "ReturnToDate")
    val returnToDate: LocalDate?,

    /**
     * lien du site de l'ANSM
     */
    @ColumnInfo(name = "AnsmSiteLink")
    val ansmSiteLink: String,

    /**
     * L'id du médicament
     */
    @ColumnInfo(name = "MedicationId")
    val medicationId: String


)
