package fr.medicapp.medicapp.model.medication

import android.content.Context
import fr.medicapp.medicapp.database.ObjectBox
import fr.medicapp.medicapp.database.converter.ModelToEntityMapper
import fr.medicapp.medicapp.database.entity.medication.MedicationEntity
import fr.medicapp.medicapp.model.OptionDialog
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
) : ModelToEntityMapper<MedicationEntity> {
    override fun convert(context: Context): MedicationEntity {
        val medicationEntity = MedicationEntity(
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
            enhancedMonitoring
        )

        val boxStore = ObjectBox.getInstance(context)
        val store = boxStore.boxFor(MedicationEntity::class.java)
        store.attach(medicationEntity)

        medicationEntity.medicationCompositions.addAll(
            medicationCompositions.map { it.convert(context) }
        )
        medicationEntity.medicationPresentations.addAll(
            medicationPresentations.map { it.convert(context) }
        )
        medicationEntity.genericGroups.addAll(
            genericGroups.map { it.convert(context) }
        )
        medicationEntity.hasSmrOpinions.addAll(
            hasSmrOpinions.map { it.convert(context) }
        )
        medicationEntity.hasAsmrOpinions.addAll(
            hasAsmrOpinions.map { it.convert(context) }
        )
        medicationEntity.importantInformations.addAll(
            importantInformations.map { it.convert(context) }
        )
        medicationEntity.prescriptionDispensingConditions.addAll(
            prescriptionDispensingConditions.map { it.convert(context) }
        )
        medicationEntity.pharmaceuticalSpecialties.addAll(
            pharmaceuticalSpecialties.map { it.convert(context) }
        )
        return medicationEntity
    }

    override fun toString(): String {
        return name
    }

    fun toOptionDialog(): OptionDialog {
        return OptionDialog(
            id,
            name,
            pharmaceuticalForm
        )
    }
}
