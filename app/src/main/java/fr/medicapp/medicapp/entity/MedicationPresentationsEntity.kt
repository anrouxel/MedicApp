package fr.medicapp.medicapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "MedicationPresentations")
data class MedicationPresentationsEntity(

    /**
     * L'identifiant unique de la présentation du médicament.
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
     * le code CIP7
     */
    @ColumnInfo(name = "CIP7Code")
    val cip7Code: String,

    /**
     * Label de presentation
     */
    @ColumnInfo(name = "PresentationLabel")
    val presentationLabel: String,

    /**
     * Statut de présentation
     */
    @ColumnInfo(name = "PresentationStatus")
    val presentationStatus: String,

    /**
     * Status de commercialisation de la presentation
     */
    @ColumnInfo(name = "PresentationCommercializationStatus")
    val presentationCommercializationStatus: String,

    /**
     * Date de déclaration de commercialisation
     */

    @ColumnInfo(name = "CommercializationDeclarationDate")
    val commercializationDeclarationDate: LocalDate?,

    /**
     * Code CIP13
     */
    @ColumnInfo(name = "CIP13Code")
    val cip13Code: String,

    /**
     * Approbation pour les communautés
     */
    @ColumnInfo(name = "ApprovalForCommunities")
    val approvalForCommunities: Int,

    /**
     * Taux de remboursement
     */
    @ColumnInfo(name = "ReimbursementRates")
    val reimbursementRates: String,

    /**
     * Prix sans honoraire en euros
     */
    @ColumnInfo(name = "PriceWithoutHonoraryInEuro")
    val priceWithoutHonoraryInEuro: String,

    /**
     * Prix avec honoraire en euros
     */
    @ColumnInfo(name = "PriceWithHonoraryInEuro")
    val priceWithHonoraryInEuro: String,

    /**
     * Prix honoraire en euros
     */
    @ColumnInfo(name = "PriceHonoraryInEuro")
    val priceHonoraryInEuro: String,

    /**
     * Indications de remboursement
     */
    @ColumnInfo(name = "ReimbursementIndications")
    val reimbursementIndications: String,

    /**
     * id du médicament
     */
    @ColumnInfo(name = "MedicationId")
    val medicationId: String



)
