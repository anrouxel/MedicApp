package fr.medicapp.medicapp.model.medication.relationship

import androidx.compose.runtime.mutableStateListOf
import androidx.room.Embedded
import androidx.room.Relation
import fr.medicapp.medicapp.model.OptionDialog
import fr.medicapp.medicapp.model.medication.GenericGroup
import fr.medicapp.medicapp.model.medication.HasAsmrOpinionInformation
import fr.medicapp.medicapp.model.medication.HasSmrOpinionInformation
import fr.medicapp.medicapp.model.medication.ImportantInformation
import fr.medicapp.medicapp.model.medication.MedicationComposition
import fr.medicapp.medicapp.model.medication.MedicationInformation
import fr.medicapp.medicapp.model.medication.MedicationPresentation
import fr.medicapp.medicapp.model.medication.PharmaceuticalSpecialty
import fr.medicapp.medicapp.model.medication.PrescriptionDispensingConditions

data class Medication(
    @Embedded
    val medicationInformation: MedicationInformation = MedicationInformation(),

    @Relation(
        entity = MedicationComposition::class,
        parentColumn = "medication_id",
        entityColumn = "medication_composition_id",
    )
    val medicationCompositions: MutableList<MedicationComposition> = mutableStateListOf(),

    @Relation(
        entity = MedicationPresentation::class,
        parentColumn = "medication_id",
        entityColumn = "medication_presentation_id",
    )
    val medicationPresentations: MutableList<MedicationPresentation> = mutableStateListOf(),

    @Relation(
        entity = GenericGroup::class,
        parentColumn = "medication_id",
        entityColumn = "generic_group_id",
    )
    val genericGroups: MutableList<GenericGroup> = mutableStateListOf(),

    @Relation(
        entity = HasSmrOpinionInformation::class,
        parentColumn = "medication_id",
        entityColumn = "has_smr_opinion_id",
    )
    val hasSmrOpinions: MutableList<HasSmrOpinion> = mutableStateListOf(),

    @Relation(
        entity = HasAsmrOpinionInformation::class,
        parentColumn = "medication_id",
        entityColumn = "has_asmr_opinion_id",
    )
    val hasAsmrOpinions: MutableList<HasAsmrOpinion> = mutableStateListOf(),

    @Relation(
        entity = ImportantInformation::class,
        parentColumn = "medication_id",
        entityColumn = "important_information_id",
    )
    val importantInformations: MutableList<ImportantInformation> = mutableStateListOf(),

    @Relation(
        entity = PrescriptionDispensingConditions::class,
        parentColumn = "medication_id",
        entityColumn = "prescription_dispensing_conditions_id",
    )
    val prescriptionDispensingConditions: MutableList<PrescriptionDispensingConditions> = mutableStateListOf(),

    @Relation(
        entity = PharmaceuticalSpecialty::class,
        parentColumn = "medication_id",
        entityColumn = "pharmaceutical_specialty_id",
    )
    val pharmaceuticalSpecialties: MutableList<PharmaceuticalSpecialty> = mutableStateListOf()
) {
    override fun toString(): String {
        return medicationInformation.name
    }

    fun toOptionDialog(): OptionDialog {
        return medicationInformation.toOptionDialog()
    }
}
