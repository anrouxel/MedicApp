package fr.medicapp.medicapp.database.repositories.medication.crossRef

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.model.medication.relationship.crossRef.MedicationMedicationCompositionCrossRef

class MedicationMedicationCompositionCrossRefRepository (context: Context) : Repository(context) {
    fun getAll(): List<MedicationMedicationCompositionCrossRef> {
        return db.MedicationMedicationCompositionCrossRefDAO().getAll()
    }

    fun insert(medicationMedicationCompositionCrossRef: MedicationMedicationCompositionCrossRef): Long {
        return db.MedicationMedicationCompositionCrossRefDAO().insert(
            medicationMedicationCompositionCrossRef
        )
    }

    fun insert(medicationMedicationCompositionCrossRef:
    List<MedicationMedicationCompositionCrossRef>): List<Long> {
        return db.MedicationMedicationCompositionCrossRefDAO().insert(
            medicationMedicationCompositionCrossRef
        )
    }

    fun delete(medicationMedicationCompositionCrossRef: MedicationMedicationCompositionCrossRef) {
        db.MedicationMedicationCompositionCrossRefDAO().delete(
            medicationMedicationCompositionCrossRef
        )
    }
}