package fr.medicapp.medicapp.model.gson

import fr.medicapp.medicapp.model.medication.GenericGroup
import fr.medicapp.medicapp.model.medication.ImportantInformation
import fr.medicapp.medicapp.model.medication.MedicationComposition
import fr.medicapp.medicapp.model.medication.MedicationInformation
import fr.medicapp.medicapp.model.medication.MedicationPresentation
import fr.medicapp.medicapp.model.medication.PharmaceuticalSpecialty
import fr.medicapp.medicapp.model.medication.PrescriptionDispensingConditions
import fr.medicapp.medicapp.model.medication.relationship.Medication
import java.time.LocalDate

data class MedicationGSON(
    var cisCode: Long = 0L,

    var name: String = "",

    var pharmaceuticalForm: String = "",

    var administrationRoutes: MutableList<String> = mutableListOf(),

    var marketingAuthorizationStatus: String = "",

    var marketingAuthorizationProcedureType: String = "",

    var commercializationStatus: String = "",

    var marketingAuthorizationDate: LocalDate? = null,

    var bdmStatus: String = "",

    var europeanAuthorizationNumber: String = "",

    var holders: MutableList<String> = mutableListOf(),

    var enhancedMonitoring: Boolean? = null,

    var medicationCompositions: MutableList<MedicationComposition> = mutableListOf(),

    var medicationPresentations: MutableList<MedicationPresentation> = mutableListOf(),

    var genericGroups: MutableList<GenericGroup> = mutableListOf(),

    var hasSmrOpinions: MutableList<HasSmrOpinionGSON> = mutableListOf(),

    var hasAsmrOpinions: MutableList<HasAsmrOpinionGSON> = mutableListOf(),

    var importantInformations: MutableList<ImportantInformation> = mutableListOf(),

    var prescriptionDispensingConditions: MutableList<PrescriptionDispensingConditions> = mutableListOf(),

    var pharmaceuticalSpecialties: MutableList<PharmaceuticalSpecialty> = mutableListOf(),
) {
    fun toMedication(): Medication {
        return Medication(
            medicationInformation = MedicationInformation(
                cisCode = cisCode,
                name = name,
                pharmaceuticalForm = pharmaceuticalForm,
                administrationRoutes = administrationRoutes,
                marketingAuthorizationStatus = marketingAuthorizationStatus,
                marketingAuthorizationProcedureType = marketingAuthorizationProcedureType,
                commercializationStatus = commercializationStatus,
                marketingAuthorizationDate = marketingAuthorizationDate,
                bdmStatus = bdmStatus,
                europeanAuthorizationNumber = europeanAuthorizationNumber,
                holders = holders,
                enhancedMonitoring = enhancedMonitoring
            ),
            medicationCompositions = medicationCompositions,
            medicationPresentations = medicationPresentations,
            genericGroups = genericGroups,
            hasSmrOpinions = hasSmrOpinions.map { it.toHasSmrOpinion() }.toMutableList(),
            hasAsmrOpinions = hasAsmrOpinions.map { it.toHasAsmrOpinion() }.toMutableList(),
            importantInformations = importantInformations,
            prescriptionDispensingConditions = prescriptionDispensingConditions,
            pharmaceuticalSpecialties = pharmaceuticalSpecialties
        )
    }
}