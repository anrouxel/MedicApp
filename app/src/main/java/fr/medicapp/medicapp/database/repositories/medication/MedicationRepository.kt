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
        val id = db.medicationDAO().insert(medication.medicationInformation)

        medication.genericGroups.forEach {
            it.medicationId = id
        }
        GenericGroupRepository(context).insert(medication.genericGroups)

        medication.hasAsmrOpinions.forEach {
            it.hasAsmrOpinionInformation.medicationId = id
        }
        HasAsmrOpinionRepository(context).insert(medication.hasAsmrOpinions)

        medication.hasSmrOpinions.forEach {
            it.hasSmrOpinionInformation.medicationId = id
        }
        HasSmrOpinionRepository(context).insert(medication.hasSmrOpinions)

        medication.importantInformations.forEach {
            it.medicationId = id
        }
        ImportantInformationRepository(context).insert(medication.importantInformations)

        medication.medicationCompositions.forEach {
            it.medicationId = id
        }
        MedicationCompositionRepository(context).insert(medication.medicationCompositions)

        medication.medicationPresentations.forEach {
            it.medicationId = id
        }
        MedicationPresentationRepository(context).insert(medication.medicationPresentations)

        medication.pharmaceuticalSpecialties.forEach {
            it.medicationId = id
        }
        PharmaceuticalSpecialtyRepository(context).insert(medication.pharmaceuticalSpecialties)

        medication.prescriptionDispensingConditions.forEach {
            it.medicationId = id
        }
        PrescriptionDispensingConditionsRepository(context).insert(medication.prescriptionDispensingConditions)

        return id
    }

    fun insert(medications: List<Medication>): List<Long> {
        medications.forEach { medication ->
            insert(medication = medication)
        }

        return medications.map { it.medicationInformation.id }
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
