package fr.medicapp.medicapp.database.repositories.medication.crossRef

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.model.medication.relationship.crossRef.MedicationMedicationPresentationCrossRef

class MedicationMedicationPresentationCrossRefRepository(context: Context) : Repository(context) {
    fun getAll(): List<MedicationMedicationPresentationCrossRef> {
        return db.MedicationMedicationPresentationCrossRefDAO().getAll()
    }

    fun insert(medicationMedicationPresentationCrossRef: MedicationMedicationPresentationCrossRef): Long {
        return db.MedicationMedicationPresentationCrossRefDAO().insert(
            medicationMedicationPresentationCrossRef
        )
    }

    fun insert(
        medicationMedicationPresentationCrossRef:
        List<MedicationMedicationPresentationCrossRef>
    ): List<Long> {
        return db.MedicationMedicationPresentationCrossRefDAO().insert(
            medicationMedicationPresentationCrossRef
        )
    }

    fun delete(medicationMedicationPresentationCrossRef: MedicationMedicationPresentationCrossRef) {
        db.MedicationMedicationPresentationCrossRefDAO().delete(
            medicationMedicationPresentationCrossRef
        )
    }
}
