package fr.medicapp.medicapp.database.repositories.medication

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.model.medication.PharmaceuticalSpecialty

class PharmaceuticalSpecialtyRepository(context: Context) : Repository(context) {
    fun getAll(): List<PharmaceuticalSpecialty> {
        return db.PharmaceuticalSpecialtyDAO().getAll()
    }

    fun insert(pharmaceuticalSpecialty: PharmaceuticalSpecialty): Long {
        return db.PharmaceuticalSpecialtyDAO().insert(pharmaceuticalSpecialty)
    }

    fun insert(pharmaceuticalSpecialties: List<PharmaceuticalSpecialty>): List<Long> {
        return db.PharmaceuticalSpecialtyDAO().insert(pharmaceuticalSpecialties)
    }

    fun delete(pharmaceuticalSpecialty: PharmaceuticalSpecialty) {
        db.PharmaceuticalSpecialtyDAO().delete(pharmaceuticalSpecialty)
    }

    fun delete(pharmaceuticalSpecialties: List<PharmaceuticalSpecialty>) {
        pharmaceuticalSpecialties.forEach { delete(it) }
    }
}
