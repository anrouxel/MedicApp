package fr.medicapp.medicapp.entity

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.time.LocalDate

@Entity
data class MedicationEntity(
    @Id
    var cisCode: Long,
    var name: String,
    var pharmaceuticalForm: String,
    var administrationRoutes: MutableList<String>,
    var marketingAuthorizationStatus: String,
    var marketingAuthorizationProcedureType: String,
    var commercializationStatus: String,
    var marketingAuthorizationDate: LocalDate?,
    var bdmStatus: String,
    var europeanAuthorizationNumber: String,
    var holders: MutableList<String>,
    var enhancedMonitoring: Boolean?,
    var medicationCompositions: MutableList<MedicationCompositionEntity>,
    var medicationPresentations: MutableList<MedicationPresentationEntity>,
    var genericGroups: MutableList<GenericGroupEntity>,
    var hasSmrOpinions: MutableList<HasSmrOpinionEntity>,
    var hasAsmrOpinions: MutableList<HasAsmrOpinionEntity>,
    var importantInformations: MutableList<ImportantInformationEntity>,
    var prescriptionDispensingConditions: MutableList<PrescriptionDispensingConditionsEntity>,
    var pharmaceuticalSpecialties: MutableList<PharmaceuticalSpecialtyEntity>
)
