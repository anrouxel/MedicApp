package fr.medicapp.medicapp.database.repositories.prescription

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.model.prescription.relationship.Prescription

class PrescriptionRepository(context: Context) : Repository(context = context) {
    fun getAll(): List<Prescription> {
        return db.prescriptionDAO().getAll()
    }

    fun getById(id: Long): Prescription {
        return db.prescriptionDAO().getById(id)
    }

    fun insert(prescription: Prescription): Long {
        prescription.prescriptionInformation.doctorId =
            DoctorRepository(context).insert(prescription.doctor!!)
        prescription.prescriptionInformation.medicationId =
            prescription.medication!!.medicationInformation.id
        return db.prescriptionDAO().insert(prescription.prescriptionInformation)
    }

    fun delete(prescription: Prescription) {
        NotificationRepository(context).delete(prescription.notifications)
        db.prescriptionDAO().delete(prescription.prescriptionInformation)
    }
}
