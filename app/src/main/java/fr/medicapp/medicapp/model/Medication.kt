package fr.medicapp.medicapp.model

import java.util.Date

data class Medication(
    val cisCode: String,
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
)