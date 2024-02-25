package fr.medicapp.medicapp.database.repositories.medication.crossRef

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.model.medication.relationship.crossRef.MedicationPharmaceuticalSpecialtyCrossRef

class MedicationPharmaceuticalSpecialtyCrossRefRepository (context: Context) : Repository(context) {
    fun getAll(): List<MedicationPharmaceuticalSpecialtyCrossRef> {
        return db.MedicationPharmaceuticalSpecialtyCrossRefDAO().getAll()
    }

    fun insert(medicationPharmaceuticalSpecialtyCrossRef: MedicationPharmaceuticalSpecialtyCrossRef): Long {
        return db.MedicationPharmaceuticalSpecialtyCrossRefDAO().insert(
            medicationPharmaceuticalSpecialtyCrossRef
        )
    }

    fun insert(medicationPharmaceuticalSpecialtyCrossRef:
    List<MedicationPharmaceuticalSpecialtyCrossRef>): List<Long> {
        return db.MedicationPharmaceuticalSpecialtyCrossRefDAO().insert(
            medicationPharmaceuticalSpecialtyCrossRef
        )
    }

    fun delete(medicationPharmaceuticalSpecialtyCrossRef: MedicationPharmaceuticalSpecialtyCrossRef) {
        db.MedicationPharmaceuticalSpecialtyCrossRefDAO().delete(
            medicationPharmaceuticalSpecialtyCrossRef
        )
    }
}