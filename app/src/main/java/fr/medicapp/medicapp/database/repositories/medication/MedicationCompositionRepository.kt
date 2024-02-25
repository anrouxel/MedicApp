package fr.medicapp.medicapp.database.repositories.medication

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.model.medication.MedicationComposition

class MedicationCompositionRepository(context: Context) : Repository(context) {
    fun getAll(): List<MedicationComposition> {
        return db.MedicationCompositionDAO().getAll()
    }

    fun insert(medicationComposition: MedicationComposition): Long {
        return db.MedicationCompositionDAO().insert(medicationComposition)
    }

    fun insert(medicationCompositions: List<MedicationComposition>): List<Long> {
        return db.MedicationCompositionDAO().insert(medicationCompositions)
    }

    fun delete(medicationComposition: MedicationComposition) {
        db.MedicationCompositionDAO().delete(medicationComposition)
    }
}