package fr.medicapp.medicapp.database.repositories.prescription

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.model.OptionDialog
import fr.medicapp.medicapp.model.prescription.relationship.Prescription

class PrescriptionRepository(context: Context) : Repository(context = context) {
    fun getAll(): List<Prescription> {
        return db.prescriptionDAO().getAll()
    }

    fun getById(id: Long): Prescription {
        return db.prescriptionDAO().getById(id)
    }

    fun search(search: String): List<OptionDialog> {
        return db.prescriptionDAO().search(search).map { it.toOptionDialog() }
    }

    fun insert(prescription: Prescription): Long {
        prescription.prescriptionInformation.medicationId =
            prescription.medication!!.medicationInformation.id
        prescription.prescriptionInformation.durationId =
            DurationRepository(context).insert(prescription.duration!!)

        val id = db.prescriptionDAO().insert(prescription.prescriptionInformation)

        prescription.notifications.forEach {
            it.notificationInformation.prescriptionId = id
        }
        NotificationRepository(context).insert(prescription.notifications)
        return id
    }

    fun delete(prescription: Prescription) {
        NotificationRepository(context).delete(prescription.notifications)
        SideEffectRepository(context).delete(prescription.sideEffects)
        DurationRepository(context).delete(prescription.duration!!)
        db.prescriptionDAO().delete(prescription.prescriptionInformation)
    }
}
