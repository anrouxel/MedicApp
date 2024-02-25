package fr.medicapp.medicapp.database.repositories.medication.crossRef

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.model.medication.relationship.crossRef.MedicationPrescriptionDispensingConditionsCrossRef

class MedicationPrescriptionDispensingConditionsCrossRefRepository(context: Context) : Repository(context) {
    fun getAll(): List<MedicationPrescriptionDispensingConditionsCrossRef> {
        return db.MedicationPrescriptionDispensingConditionsCrossRefDAO().getAll()
    }

    fun insert(medicationPrescriptionDispensingConditionsCrossRef: MedicationPrescriptionDispensingConditionsCrossRef): Long {
        return db.MedicationPrescriptionDispensingConditionsCrossRefDAO().insert(
            medicationPrescriptionDispensingConditionsCrossRef
        )
    }

    fun insert(
        medicationPrescriptionDispensingConditionsCrossRef:
        List<MedicationPrescriptionDispensingConditionsCrossRef>
    ): List<Long> {
        return db.MedicationPrescriptionDispensingConditionsCrossRefDAO().insert(
            medicationPrescriptionDispensingConditionsCrossRef
        )
    }

    fun delete(medicationPrescriptionDispensingConditionsCrossRef: MedicationPrescriptionDispensingConditionsCrossRef) {
        db.MedicationPrescriptionDispensingConditionsCrossRefDAO().delete(
            medicationPrescriptionDispensingConditionsCrossRef
        )
    }
}