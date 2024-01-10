package fr.medicapp.medicapp.repository

import fr.medicapp.medicapp.dao.TreatmentDAO
import fr.medicapp.medicapp.database.IDatabase
import fr.medicapp.medicapp.entity.TreatmentEntity

class TreatmentRepository(
    private val treatmentDao: TreatmentDAO
) : IDatabase<TreatmentEntity> {
    override fun getAll(): List<TreatmentEntity> {
        return treatmentDao.getAll()
    }

    override fun <E> getOne(id: E): TreatmentEntity {
        return treatmentDao.getOne(id)
    }

    override fun add(t: TreatmentEntity) {
        treatmentDao.add(t)
    }

    override fun addAll(vararg t: TreatmentEntity) {
        treatmentDao.addAll(*t)
    }

    override fun delete(t: TreatmentEntity) {
        treatmentDao.delete(t)
    }

    override fun deleteAll(vararg t: TreatmentEntity) {
        treatmentDao.deleteAll(*t)
    }

    override fun update(t: TreatmentEntity) {
        treatmentDao.update(t)
    }

    override fun updateAll(vararg t: TreatmentEntity) {
        treatmentDao.updateAll(*t)
    }
}