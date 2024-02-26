package fr.medicapp.medicapp.database.repositories.medication

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.model.OptionDialog
import fr.medicapp.medicapp.model.medication.relationship.Medication

class MedicationRepository(context: Context) : Repository(context) {
    fun getAll(): List<Medication> {
        return db.medicationDAO().getAll()
    }

    fun getById(id: Long): Medication {
        return db.medicationDAO().getById(id)
    }

    fun search(search: String): List<OptionDialog> {
        return db.medicationDAO().search(search).map { it.toOptionDialog() }
    }

    fun insert(medication: Medication): Long {
        medication.genericGroups.forEach {
            it.medicationId = medication.medicationInformation.id
        }
        GenericGroupRepository(context).insert(medication.genericGroups)

        medication.hasAsmrOpinions.forEach {
            it.hasAsmrOpinionInformation.medicationId = medication.medicationInformation.id
        }
        HasAsmrOpinionRepository(context).insert(medication.hasAsmrOpinions)

        medication.hasSmrOpinions.forEach {
            it.hasSmrOpinionInformation.medicationId = medication.medicationInformation.id
        }
        HasSmrOpinionRepository(context).insert(medication.hasSmrOpinions)

        medication.importantInformations.forEach {
            it.medicationId = medication.medicationInformation.id
        }
        ImportantInformationRepository(context).insert(medication.importantInformations)

        medication.medicationCompositions.forEach {
            it.medicationId = medication.medicationInformation.id
        }
        MedicationCompositionRepository(context).insert(medication.medicationCompositions)

        medication.medicationPresentations.forEach {
            it.medicationId = medication.medicationInformation.id
        }
        MedicationPresentationRepository(context).insert(medication.medicationPresentations)

        medication.pharmaceuticalSpecialties.forEach {
            it.medicationId = medication.medicationInformation.id
        }
        PharmaceuticalSpecialtyRepository(context).insert(medication.pharmaceuticalSpecialties)

        medication.prescriptionDispensingConditions.forEach {
            it.medicationId = medication.medicationInformation.id
        }
        PrescriptionDispensingConditionsRepository(context).insert(medication.prescriptionDispensingConditions)

        return db.medicationDAO().insert(medication.medicationInformation)
    }

    fun insert(medications: List<Medication>): List<Long> {
        medications.forEach { medication ->
            insert(medication = medication)
        }

        return db.medicationDAO().insert(medications.map { it.medicationInformation })
    }

    fun delete(medication: Medication) {
        GenericGroupRepository(context).delete(medication.genericGroups)
        HasAsmrOpinionRepository(context).delete(medication.hasAsmrOpinions)
        HasSmrOpinionRepository(context).delete(medication.hasSmrOpinions)
        ImportantInformationRepository(context).delete(medication.importantInformations)
        MedicationCompositionRepository(context).delete(medication.medicationCompositions)
        MedicationPresentationRepository(context).delete(medication.medicationPresentations)
        PharmaceuticalSpecialtyRepository(context).delete(medication.pharmaceuticalSpecialties)
        PrescriptionDispensingConditionsRepository(context).delete(medication.prescriptionDispensingConditions)

        db.medicationDAO().delete(medication.medicationInformation)
    }
}
