package fr.medicapp.medicapp.database.repositories.medication

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.model.medication.ImportantInformation

class ImportantInformationRepository(context: Context) : Repository(context) {
    fun getAll(): List<ImportantInformation> {
        return db.ImportantInformationDAO().getAll()
    }

    fun insert(importantInformation: ImportantInformation): Long {
        return db.ImportantInformationDAO().insert(importantInformation)
    }

    fun insert(importantInformations: List<ImportantInformation>): List<Long> {
        return db.ImportantInformationDAO().insert(importantInformations)
    }

    fun delete(importantInformation: ImportantInformation) {
        db.ImportantInformationDAO().delete(importantInformation)
    }
}