package fr.medicapp.medicapp.model.medication.relationship

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import fr.medicapp.medicapp.model.medication.GenericGroup
import fr.medicapp.medicapp.model.medication.HasSmrOpinionInformation
import fr.medicapp.medicapp.model.medication.ImportantInformation
import fr.medicapp.medicapp.model.medication.MedicationInformation
import fr.medicapp.medicapp.model.medication.MedicationComposition
import fr.medicapp.medicapp.model.medication.MedicationPresentation
import fr.medicapp.medicapp.model.medication.PharmaceuticalSpecialty
import fr.medicapp.medicapp.model.medication.PrescriptionDispensingConditions
import fr.medicapp.medicapp.model.medication.relationship.crossRef.MedicationGenericGroupCrossRef
import fr.medicapp.medicapp.model.medication.relationship.crossRef.MedicationImportantInformationCrossRef
import fr.medicapp.medicapp.model.medication.relationship.crossRef.MedicationMedicationCompositionCrossRef
import fr.medicapp.medicapp.model.medication.relationship.crossRef.MedicationMedicationPresentationCrossRef
import fr.medicapp.medicapp.model.medication.relationship.crossRef.MedicationPharmaceuticalSpecialtyCrossRef
import fr.medicapp.medicapp.model.medication.relationship.crossRef.MedicationPrescriptionDispensingConditionsCrossRef

data class Medication(
    @Embedded
    val medicationInformation: MedicationInformation,

    @Relation(
        parentColumn = "medication_id",
        entityColumn = "medication_composition_id",
        associateBy = Junction(MedicationMedicationCompositionCrossRef::class)
    )
    val medicationCompositions: List<MedicationComposition>,

    @Relation(
        parentColumn = "medication_id",
        entityColumn = "medication_presentation_id",
        associateBy = Junction(MedicationMedicationPresentationCrossRef::class)
    )
    val medicationPresentations: List<MedicationPresentation>,

    @Relation(
        parentColumn = "medication_id",
        entityColumn = "generic_group_id",
        associateBy = Junction(MedicationGenericGroupCrossRef::class)
    )
    val genericGroups: List<GenericGroup>,

/*    @Embedded
    val hasSmrOpinionWithTransparencyCommissionOpinionLinks: HasSmrOpinion,

    @Embedded
    val hasAsmrOpinionWithTransparencyCommissionOpinionLinks: HasAsmrOpinion,
*/
    @Relation(
        parentColumn = "medication_id",
        entityColumn = "important_information_id",
        associateBy = Junction(MedicationImportantInformationCrossRef::class)
    )
    val importantInformations: List<ImportantInformation>,

    @Relation(
        parentColumn = "medication_id",
        entityColumn = "prescription_dispensing_conditions_id",
        associateBy = Junction(MedicationPrescriptionDispensingConditionsCrossRef::class)
    )
    val prescriptionDispensingConditions: List<PrescriptionDispensingConditions>,

    @Relation(
        parentColumn = "medication_id",
        entityColumn = "pharmaceutical_specialty_id",
        associateBy = Junction(MedicationPharmaceuticalSpecialtyCrossRef::class)
    )
    val pharmaceuticalSpecialties: List<PharmaceuticalSpecialty>,
)