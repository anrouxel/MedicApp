package fr.medicapp.medicapp.database.repositories.medication

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.database.repositories.medication.crossRef.MedicationGenericGroupCrossRefRepository
import fr.medicapp.medicapp.database.repositories.medication.crossRef.MedicationImportantInformationCrossRefRepository
import fr.medicapp.medicapp.database.repositories.medication.crossRef.MedicationMedicationCompositionCrossRefRepository
import fr.medicapp.medicapp.database.repositories.medication.crossRef.MedicationMedicationPresentationCrossRefRepository
import fr.medicapp.medicapp.database.repositories.medication.crossRef.MedicationPharmaceuticalSpecialtyCrossRefRepository
import fr.medicapp.medicapp.database.repositories.medication.crossRef.MedicationPrescriptionDispensingConditionsCrossRefRepository
import fr.medicapp.medicapp.model.medication.relationship.Medication
import fr.medicapp.medicapp.model.medication.relationship.crossRef.MedicationGenericGroupCrossRef
import fr.medicapp.medicapp.model.medication.relationship.crossRef.MedicationImportantInformationCrossRef
import fr.medicapp.medicapp.model.medication.relationship.crossRef.MedicationMedicationCompositionCrossRef
import fr.medicapp.medicapp.model.medication.relationship.crossRef.MedicationMedicationPresentationCrossRef
import fr.medicapp.medicapp.model.medication.relationship.crossRef.MedicationPharmaceuticalSpecialtyCrossRef
import fr.medicapp.medicapp.model.medication.relationship.crossRef.MedicationPrescriptionDispensingConditionsCrossRef

class MedicationRepository(context: Context) : Repository(context) {
    fun getAll(): List<Medication> {
        return db.medicationDAO().getAll()
    }

    fun insert(medication: Medication): Long {
        val genericGroupIds = GenericGroupRepository(context).insert(medication.genericGroups)
        val importantInformationIds = ImportantInformationRepository(context).insert(medication.importantInformations)
        val medicationCompositionIds = MedicationCompositionRepository(
            context
        ).insert(medication.medicationCompositions)
        val medicationPresentationIds = MedicationPresentationRepository(
            context
        ).insert(medication.medicationPresentations)
        val pharmaceuticalSpecialtyIds = PharmaceuticalSpecialtyRepository(
            context
        ).insert(medication.pharmaceuticalSpecialties)
        val prescriptionDispensingConditionsIds = PrescriptionDispensingConditionsRepository(
            context
        ).insert(medication.prescriptionDispensingConditions)

        MedicationGenericGroupCrossRefRepository(context).insert(
            genericGroupIds.map {
                MedicationGenericGroupCrossRef(
                    medication.medicationInformation.id,
                    it
                )
            }
        )
        MedicationImportantInformationCrossRefRepository(context).insert(
            importantInformationIds.map {
                MedicationImportantInformationCrossRef(
                    medication.medicationInformation.id,
                    it
                )
            }
        )
        MedicationMedicationCompositionCrossRefRepository(context).insert(
            medicationCompositionIds.map {
                MedicationMedicationCompositionCrossRef(
                    medication.medicationInformation.id,
                    it
                )
            }
        )
        MedicationMedicationPresentationCrossRefRepository(context).insert(
            medicationPresentationIds.map {
                MedicationMedicationPresentationCrossRef(
                    medication.medicationInformation.id,
                    it
                )
            }
        )
        MedicationPharmaceuticalSpecialtyCrossRefRepository(context).insert(
            pharmaceuticalSpecialtyIds.map {
                MedicationPharmaceuticalSpecialtyCrossRef(
                    medication.medicationInformation.id,
                    it
                )
            }
        )
        MedicationPrescriptionDispensingConditionsCrossRefRepository(context).insert(
            prescriptionDispensingConditionsIds.map {
                MedicationPrescriptionDispensingConditionsCrossRef(
                    medication.medicationInformation.id,
                    it
                )
            }
        )

        return db.medicationDAO().insert(medication.medicationInformation)
    }

    fun insert(medications: List<Medication>): List<Long> {
        medications.forEach { medication ->
            val genericGroupIds = GenericGroupRepository(context).insert(medication.genericGroups)
            val importantInformationIds = ImportantInformationRepository(
                context
            ).insert(medication.importantInformations)
            val medicationCompositionIds = MedicationCompositionRepository(
                context
            ).insert(medication.medicationCompositions)
            val medicationPresentationIds = MedicationPresentationRepository(
                context
            ).insert(medication.medicationPresentations)
            val pharmaceuticalSpecialtyIds = PharmaceuticalSpecialtyRepository(
                context
            ).insert(medication.pharmaceuticalSpecialties)
            val prescriptionDispensingConditionsIds = PrescriptionDispensingConditionsRepository(
                context
            ).insert(medication.prescriptionDispensingConditions)

            MedicationGenericGroupCrossRefRepository(context).insert(
                genericGroupIds.map {
                    MedicationGenericGroupCrossRef(
                        medication.medicationInformation.id,
                        it
                    )
                }
            )
            MedicationImportantInformationCrossRefRepository(context).insert(
                importantInformationIds.map {
                    MedicationImportantInformationCrossRef(
                        medication.medicationInformation.id,
                        it
                    )
                }
            )
            MedicationMedicationCompositionCrossRefRepository(context).insert(
                medicationCompositionIds.map {
                    MedicationMedicationCompositionCrossRef(
                        medication.medicationInformation.id,
                        it
                    )
                }
            )
            MedicationMedicationPresentationCrossRefRepository(context).insert(
                medicationPresentationIds.map {
                    MedicationMedicationPresentationCrossRef(
                        medication.medicationInformation.id,
                        it
                    )
                }
            )
            MedicationPharmaceuticalSpecialtyCrossRefRepository(context).insert(
                pharmaceuticalSpecialtyIds.map {
                    MedicationPharmaceuticalSpecialtyCrossRef(
                        medication.medicationInformation.id,
                        it
                    )
                }
            )
            MedicationPrescriptionDispensingConditionsCrossRefRepository(context).insert(
                prescriptionDispensingConditionsIds.map {
                    MedicationPrescriptionDispensingConditionsCrossRef(
                        medication.medicationInformation.id,
                        it
                    )
                }
            )
        }

        return db.medicationDAO().insert(medications.map { it.medicationInformation })
    }

    fun delete(medication: Medication) {
        db.medicationDAO().delete(medication.medicationInformation)
    }
}
