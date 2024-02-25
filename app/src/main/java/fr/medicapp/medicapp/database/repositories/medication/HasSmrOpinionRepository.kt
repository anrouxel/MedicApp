package fr.medicapp.medicapp.database.repositories.medication

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.model.medication.HasSmrOpinionInformation

class HasSmrOpinionRepository(context: Context) : Repository(context) {
    fun getAll(): List<HasSmrOpinionInformation> {
        return db.HasSmrOpinionDAO().getAll()
    }

    fun insert(hasSmrOpinionInformation: HasSmrOpinionInformation): Long {
        return db.HasSmrOpinionDAO().insert(hasSmrOpinionInformation)
    }

    fun insert(hasSmrOpinionInformations: List<HasSmrOpinionInformation>): List<Long> {
        return db.HasSmrOpinionDAO().insert(hasSmrOpinionInformations)
    }

    fun delete(hasSmrOpinionInformation: HasSmrOpinionInformation) {
        db.HasSmrOpinionDAO().delete(hasSmrOpinionInformation)
    }
}