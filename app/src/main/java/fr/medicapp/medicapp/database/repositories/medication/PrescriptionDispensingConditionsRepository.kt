package fr.medicapp.medicapp.database.repositories.medication

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.model.medication.PrescriptionDispensingConditions

class PrescriptionDispensingConditionsRepository(context: Context) : Repository(context) {
    fun getAll(): List<PrescriptionDispensingConditions> {
        return db.PrescriptionDispensingConditionsDAO().getAll()
    }

    fun insert(prescriptionDispensingConditions: PrescriptionDispensingConditions): Long {
        return db.PrescriptionDispensingConditionsDAO().insert(prescriptionDispensingConditions)
    }

    fun insert(prescriptionDispensingConditionss: List<PrescriptionDispensingConditions>): List<Long> {
        return db.PrescriptionDispensingConditionsDAO().insert(prescriptionDispensingConditionss)
    }

    fun delete(prescriptionDispensingConditions: PrescriptionDispensingConditions) {
        db.PrescriptionDispensingConditionsDAO().delete(prescriptionDispensingConditions)
    }

    fun delete(prescriptionDispensingConditionss: List<PrescriptionDispensingConditions>) {
        prescriptionDispensingConditionss.forEach { delete(it) }
    }
}