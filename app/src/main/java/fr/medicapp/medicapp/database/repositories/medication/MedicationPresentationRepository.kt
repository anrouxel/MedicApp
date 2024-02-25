package fr.medicapp.medicapp.database.repositories.medication

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.model.medication.MedicationPresentation

class MedicationPresentationRepository(context: Context) : Repository(context) {
    fun getAll(): List<MedicationPresentation> {
        return db.MedicationPresentationDAO().getAll()
    }

    fun insert(medicationPresentation: MedicationPresentation): Long {
        return db.MedicationPresentationDAO().insert(medicationPresentation)
    }

    fun insert(medicationPresentations: List<MedicationPresentation>): List<Long> {
        return db.MedicationPresentationDAO().insert(medicationPresentations)
    }

    fun delete(medicationPresentation: MedicationPresentation) {
        db.MedicationPresentationDAO().delete(medicationPresentation)
    }

    fun delete(medicationPresentations: List<MedicationPresentation>) {
        medicationPresentations.forEach { delete(it) }
    }
}