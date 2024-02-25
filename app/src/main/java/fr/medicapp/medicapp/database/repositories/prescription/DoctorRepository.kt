package fr.medicapp.medicapp.database.repositories.prescription

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.model.prescription.Doctor

class DoctorRepository(context: Context) : Repository(context = context) {
    fun getAll(): List<Doctor> {
        return db.doctorDAO().getAll()
    }

    fun insert(doctor: Doctor): Long {
        return db.doctorDAO().insert(doctor)
    }
}
