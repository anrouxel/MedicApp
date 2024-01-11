package fr.medicapp.medicapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.maxkeppeler.sheets.option.OptionDialog
import fr.medicapp.medicapp.model.OptionDialog
import java.time.LocalDate

/**
 * Table nommée Medication.
 * Elle est définie par son cisCode.
 * Elle représente un médicament avec toutes ses informations nécessaire.
 * La majorité des tables de la base de données sont utilisées ici.
 * */
@Entity(tableName = "Medications")
data class MedicationEntity(
    @PrimaryKey
    @ColumnInfo(name = "CISCode")
    val cisCode: String,

    @ColumnInfo(name = "Name")
    val name: String,

    @ColumnInfo(name = "PharmaceuticalForm")
    val pharmaceuticalForm: String,

    @ColumnInfo(name = "AdministrationRoutes")
    val administrationRoutes: List<String>,

    @ColumnInfo(name = "MarketingAuthorizationStatus")
    val marketingAuthorizationStatus: String,

    @ColumnInfo(name = "MarketingAuthorizationProcedureType")
    val marketingAuthorizationProcedureType: String,

    @ColumnInfo(name = "CommercializationStatus")
    val commercializationStatus: String,

    @ColumnInfo(name = "MarketingAuthorizationDate")
    val marketingAuthorizationDate: LocalDate?,

    @ColumnInfo(name = "BdmStatus")
    val bdmStatus: String,

    @ColumnInfo(name = "EuropeanAuthorizationNumber")
    val europeanAuthorizationNumber: String,

    @ColumnInfo(name = "Holders")
    val holders: List<String>,

    @ColumnInfo(name = "EnhancedMonitoring")
    val enhancedMonitoring: Boolean?,
) {
    fun toOptionDialog(): OptionDialog {
        return OptionDialog(
            id = cisCode,
            title = name
        )
    }
}