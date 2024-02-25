package fr.medicapp.medicapp.database.repositories.medication.crossRef

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.model.medication.relationship.crossRef.MedicationImportantInformationCrossRef

class MedicationImportantInformationCrossRefRepository (context: Context) : Repository(context) {
    fun getAll(): List<MedicationImportantInformationCrossRef> {
        return db.MedicationImportantInformationCrossRefDAO().getAll()
    }

    fun insert(medicationImportantInformationCrossRef: MedicationImportantInformationCrossRef): Long {
        return db.MedicationImportantInformationCrossRefDAO().insert(medicationImportantInformationCrossRef)
    }

    fun insert(medicationImportantInformationCrossRefs: List<MedicationImportantInformationCrossRef>): List<Long> {
        return db.MedicationImportantInformationCrossRefDAO().insert(medicationImportantInformationCrossRefs)
    }

    fun delete(medicationImportantInformationCrossRef: MedicationImportantInformationCrossRef) {
        db.MedicationImportantInformationCrossRefDAO().delete(medicationImportantInformationCrossRef)
    }
}