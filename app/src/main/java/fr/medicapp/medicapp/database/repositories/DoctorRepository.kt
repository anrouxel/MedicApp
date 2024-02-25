package fr.medicapp.medicapp.database.repositories

import android.content.Context
import fr.medicapp.medicapp.model.prescription.Doctor
import fr.medicapp.medicapp.model.prescription.relationship.Prescription

class DoctorRepository(context: Context) : Repository(context = context) {
    fun getAll(): List<Doctor> {
        return db.doctorDAO().getAll()
    }

    fun insert(doctor: Doctor): Long {
        return db.doctorDAO().insert(doctor)
    }
}
