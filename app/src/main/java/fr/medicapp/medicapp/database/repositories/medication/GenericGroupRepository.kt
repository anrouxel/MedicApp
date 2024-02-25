package fr.medicapp.medicapp.database.repositories.medication

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.model.medication.GenericGroup

class GenericGroupRepository(context: Context) : Repository(context) {

    fun getAll(): List<GenericGroup> {
        return db.GenericGroupDAO().getAll()
    }

    fun insert(genericGroup: GenericGroup): Long {
        return db.GenericGroupDAO().insert(genericGroup)
    }

    fun insert(genericGroups: List<GenericGroup>): List<Long> {
        return db.GenericGroupDAO().insert(genericGroups)
    }

    fun delete(genericGroup: GenericGroup) {
        db.GenericGroupDAO().delete(genericGroup)
    }

    fun delete(genericGroups: List<GenericGroup>) {
        genericGroups.forEach { delete(it) }
    }
}
