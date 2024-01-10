package fr.medicapp.medicapp.repository

import fr.medicapp.medicapp.dao.TreatmentDAO
import fr.medicapp.medicapp.entity.TreatmentEntity

class TreatmentRepository(
    private val treatmentDao: TreatmentDAO
) {
    fun getAll(): List<TreatmentEntity> {
        return treatmentDao.getAll()
    }

    fun getOne(id: Int): TreatmentEntity {
        return treatmentDao.getOne(id)
    }

    fun add(t: TreatmentEntity) {
        treatmentDao.add(t)
    }

    fun addAll(vararg t: TreatmentEntity) {
        treatmentDao.addAll(*t)
    }

    fun delete(t: TreatmentEntity) {
        treatmentDao.delete(t)
    }

    fun deleteAll(vararg t: TreatmentEntity) {
        treatmentDao.deleteAll(*t)
    }

    fun update(t: TreatmentEntity) {
        treatmentDao.update(t)
    }

    fun updateAll(vararg t: TreatmentEntity) {
        treatmentDao.updateAll(*t)
    }
}