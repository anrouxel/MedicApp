package fr.medicapp.medicapp.entity

import fr.medicapp.medicapp.database.LocalDateConverter
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany
import java.time.LocalDate

@Entity
data class MedicationEntity(
    @Id
    var id: Long = 0L,

    var cisCode: Long = 0L,

    var name: String = "",

    var pharmaceuticalForm: String = "",

    var administrationRoutes: MutableList<String> = mutableListOf(),

    var marketingAuthorizationStatus: String = "",

    var marketingAuthorizationProcedureType: String = "",

    var commercializationStatus: String = "",

    @Convert(converter = LocalDateConverter::class, dbType = String::class)
    var marketingAuthorizationDate: LocalDate? = null,

    var bdmStatus: String = "",

    var europeanAuthorizationNumber: String = "",

    var holders: MutableList<String> = mutableListOf(),

    var enhancedMonitoring: Boolean? = null,
) {
    var medicationCompositions: MutableList<MedicationCompositionEntity> = ToMany(this, MedicationEntity_.medicationCompositions)

    var medicationPresentations: MutableList<MedicationPresentationEntity> = ToMany(this, MedicationEntity_.medicationPresentations)

    var genericGroups: MutableList<GenericGroupEntity> = ToMany(this, MedicationEntity_.genericGroups)

    var hasSmrOpinions: MutableList<HasSmrOpinionEntity> = ToMany(this, MedicationEntity_.hasSmrOpinions)

    var hasAsmrOpinions: MutableList<HasAsmrOpinionEntity> = ToMany(this, MedicationEntity_.hasAsmrOpinions)

    var importantInformations: MutableList<ImportantInformationEntity> = ToMany(this, MedicationEntity_.importantInformations)

    var prescriptionDispensingConditions: MutableList<PrescriptionDispensingConditionsEntity> = ToMany(this, MedicationEntity_.prescriptionDispensingConditions)

    var pharmaceuticalSpecialties: MutableList<PharmaceuticalSpecialtyEntity> = ToMany(this, MedicationEntity_.pharmaceuticalSpecialties)
}
