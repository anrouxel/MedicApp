package fr.medicapp.medicapp.database.repositories.prescription

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.model.prescription.SideEffectInformation
import fr.medicapp.medicapp.model.prescription.relationship.SideEffect

class SideEffectRepository(context: Context) : Repository(context = context) {
    fun getAll(): List<SideEffect> {
        return db.sideEffectDAO().getAll()
    }

    fun getById(id: Long): SideEffect {
        return db.sideEffectDAO().getById(id)
    }

    fun insert(sideEffect: SideEffect): Long {
        sideEffect.sideEffectInformation.prescriptionId =
            sideEffect.prescription!!.prescriptionInformation.id
        return db.sideEffectDAO().insert(sideEffect.sideEffectInformation)
    }

    fun insert(sideEffectInformation: List<SideEffectInformation>): List<Long> {
        return db.sideEffectDAO().insert(sideEffectInformation)
    }

    fun remove(sideEffect: SideEffect) {
        db.sideEffectDAO().delete(sideEffect.sideEffectInformation)
    }
}
