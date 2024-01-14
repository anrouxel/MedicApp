package fr.medicapp.medicapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import fr.medicapp.medicapp.model.OptionDialog
import java.time.LocalDate

/**
 * Entité représentant un médicament dans la base de données.
 *
 * @property cisCode Le code CIS du médicament.
 * @property name Le nom du médicament.
 * @property pharmaceuticalForm La forme pharmaceutique du médicament.
 * @property administrationRoutes Les voies d'administration du médicament.
 * @property marketingAuthorizationStatus Le statut de l'autorisation de mise sur le marché du médicament.
 * @property marketingAuthorizationProcedureType Le type de procédure d'autorisation de mise sur le marché du médicament.
 * @property commercializationStatus Le statut de commercialisation du médicament.
 * @property marketingAuthorizationDate La date d'autorisation de mise sur le marché du médicament.
 * @property bdmStatus Le statut BDM du médicament.
 * @property europeanAuthorizationNumber Le numéro d'autorisation européen du médicament.
 * @property holders Les détenteurs du médicament.
 * @property enhancedMonitoring Le suivi renforcé du médicament.
 */
@Entity(tableName = "Medications")
data class MedicationEntity(

    /**
     * Le code CIS du médicament.
     */
    @PrimaryKey
    @ColumnInfo(name = "CISCode")
    val cisCode: String,

    /**
     * Le nom du médicament.
     */
    @ColumnInfo(name = "Name")
    val name: String,

    /**
     * La forme pharmaceutique du médicament.
     */
    @ColumnInfo(name = "PharmaceuticalForm")
    val pharmaceuticalForm: String,

    /**
     * Les voies d'administration du médicament.
     */
    @ColumnInfo(name = "AdministrationRoutes")
    val administrationRoutes: List<String>,

    /**
     * Le statut de l'autorisation de mise sur le marché du médicament.
     */
    @ColumnInfo(name = "MarketingAuthorizationStatus")
    val marketingAuthorizationStatus: String,

    /**
     * Le type de procédure d'autorisation de mise sur le marché du médicament.
     */
    @ColumnInfo(name = "MarketingAuthorizationProcedureType")
    val marketingAuthorizationProcedureType: String,

    /**
     * Le statut de commercialisation du médicament.
     */
    @ColumnInfo(name = "CommercializationStatus")
    val commercializationStatus: String,

    /**
     * La date d'autorisation de mise sur le marché du médicament.
     */
    @ColumnInfo(name = "MarketingAuthorizationDate")
    val marketingAuthorizationDate: LocalDate?,

    /**
     * Le statut BDM du médicament.
     */
    @ColumnInfo(name = "BdmStatus")
    val bdmStatus: String,

    /**
     * Le numéro d'autorisation européen du médicament.
     */
    @ColumnInfo(name = "EuropeanAuthorizationNumber")
    val europeanAuthorizationNumber: String,

    /**
     * Les détenteurs du médicament.
     */
    @ColumnInfo(name = "Holders")
    val holders: List<String>,

    /**
     * Le suivi renforcé du médicament.
     */
    @ColumnInfo(name = "EnhancedMonitoring")
    val enhancedMonitoring: Boolean?,
) {

    /**
     * Convertit cette entité en un objet OptionDialog.
     *
     * @return Un objet OptionDialog correspondant à cette entité.
     */
    fun toOptionDialog(): OptionDialog {
        return OptionDialog(
            id = cisCode,
            title = name
        )
    }
}
