package fr.medicapp.medicapp.database.entity.medication

import fr.medicapp.medicapp.database.converter.EntityToModelMapper
import fr.medicapp.medicapp.database.converter.LocalDateConverter
import fr.medicapp.medicapp.model.medication.Medication
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.Index
import io.objectbox.annotation.Unique
import io.objectbox.relation.ToMany
import java.time.LocalDate

@Entity
data class MedicationEntity(
    @Id
    var id: Long = 0L,

    @Index
    @Unique
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
) : EntityToModelMapper<Medication> {
    lateinit var medicationCompositions: ToMany<MedicationCompositionEntity>

    lateinit var medicationPresentations: ToMany<MedicationPresentationEntity>

    lateinit var genericGroups: ToMany<GenericGroupEntity>

    lateinit var hasSmrOpinions: ToMany<HasSmrOpinionEntity>

    lateinit var hasAsmrOpinions: ToMany<HasAsmrOpinionEntity>

    lateinit var importantInformations: ToMany<ImportantInformationEntity>

    lateinit var prescriptionDispensingConditions: ToMany<PrescriptionDispensingConditionsEntity>

    lateinit var pharmaceuticalSpecialties: ToMany<PharmaceuticalSpecialtyEntity>

    override fun convert(): Medication {
        return Medication(
            id,
            cisCode,
            name,
            pharmaceuticalForm,
            administrationRoutes,
            marketingAuthorizationStatus,
            marketingAuthorizationProcedureType,
            commercializationStatus,
            marketingAuthorizationDate,
            bdmStatus,
            europeanAuthorizationNumber,
            holders,
            enhancedMonitoring,
            medicationCompositions.map { it.convert() }.toMutableList(),
            medicationPresentations.map { it.convert() }.toMutableList(),
            genericGroups.map { it.convert() }.toMutableList(),
            hasSmrOpinions.map { it.convert() }.toMutableList(),
            hasAsmrOpinions.map { it.convert() }.toMutableList(),
            importantInformations.map { it.convert() }.toMutableList(),
            prescriptionDispensingConditions.map { it.convert() }.toMutableList(),
            pharmaceuticalSpecialties.map { it.convert() }.toMutableList()
        )
    }
}
