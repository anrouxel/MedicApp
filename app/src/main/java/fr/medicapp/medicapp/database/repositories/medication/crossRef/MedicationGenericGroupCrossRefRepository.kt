package fr.medicapp.medicapp.database.repositories.medication.crossRef

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.model.medication.relationship.crossRef.MedicationGenericGroupCrossRef

class MedicationGenericGroupCrossRefRepository(context: Context) : Repository(context) {
    fun getAll(): List<MedicationGenericGroupCrossRef> {
        return db.MedicationGenericGroupCrossRefDAO().getAll()
    }

    fun insert(medicationGenericGroupCrossRef: MedicationGenericGroupCrossRef): Long {
        return db.MedicationGenericGroupCrossRefDAO().insert(medicationGenericGroupCrossRef)
    }

    fun insert(medicationGenericGroupCrossRefs: List<MedicationGenericGroupCrossRef>): List<Long> {
        return db.MedicationGenericGroupCrossRefDAO().insert(medicationGenericGroupCrossRefs)
    }

    fun delete(medicationGenericGroupCrossRef: MedicationGenericGroupCrossRef) {
        db.MedicationGenericGroupCrossRefDAO().delete(medicationGenericGroupCrossRef)
    }
}
