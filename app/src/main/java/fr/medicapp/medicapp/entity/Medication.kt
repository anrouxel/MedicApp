package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 * Table nommée Medication.
 * Elle est définie par son cisCode.
 * Elle représente un médicament avec toutes ses informations nécessaire.
 * La majorité des tables de la base de données sont utilisées ici.
 * */
@Entity(tableName = "Medication")
data class Medication(
    @PrimaryKey val cisCode: String,
    val name: String,
    val pharmaceuticalForm: String,
    val administrationRoutes: List<String>,
    val marketingAuthorizationStatus: String,
    val marketingAuthorizationProcedureType: String,
    val commercializationStatus: String,
    val marketingAuthorizationDate: Date?,
    val bdmStatus: String,
    val europeanAuthorizationNumber: String,
    val holders: List<String>,
    val enhancedMonitoring: Boolean?,
    val medicationCompositions: List<MedicationComposition>,
    val medicationPresentations: List<MedicationPresentation>,
    val genericGroups: List<GenericGroup>,
    val hasSmrOpinions: List<HasSmrOpinion>,
    val hasAsmrOpinions: List<HasAsmrOpinion>,
    val importantInformations: List<ImportantInformation>,
    val prescriptionDispensingConditions: List<PrescriptionDispensingConditions>
) {
    override fun toString(): String {
        return name
    }
}
