package fr.medicapp.medicapp.entity.medication

import fr.medicapp.medicapp.database.LocalDateConverter
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany
import java.time.LocalDate

data class Medication(
    val id: Long = 0L,

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

    var hasSmrOpinions: MutableList<HasSmrOpinion> = mutableListOf(),

    var hasAsmrOpinions: MutableList<HasAsmrOpinion> = mutableListOf(),

    var importantInformations: MutableList<ImportantInformation> = mutableListOf(),

    var prescriptionDispensingConditions: MutableList<PrescriptionDispensingConditions> = mutableListOf(),

    var pharmaceuticalSpecialties: MutableList<PharmaceuticalSpecialty> = mutableListOf(),
)
