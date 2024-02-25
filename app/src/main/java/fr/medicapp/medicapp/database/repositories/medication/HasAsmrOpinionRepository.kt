package fr.medicapp.medicapp.database.repositories.medication

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.model.medication.HasAsmrOpinionInformation

class HasAsmrOpinionRepository(context: Context) : Repository(context) {
    fun getAll(): List<HasAsmrOpinionInformation> {
        return db.HasAsmrOpinionDAO().getAll()
    }

    fun insert(hasAsmrOpinionInformation: HasAsmrOpinionInformation): Long {
        return db.HasAsmrOpinionDAO().insert(hasAsmrOpinionInformation)
    }

    fun insert(hasAsmrOpinionInformations: List<HasAsmrOpinionInformation>): List<Long> {
        return db.HasAsmrOpinionDAO().insert(hasAsmrOpinionInformations)
    }

    fun delete(hasAsmrOpinionInformation: HasAsmrOpinionInformation) {
        db.HasAsmrOpinionDAO().delete(hasAsmrOpinionInformation)
    }
}