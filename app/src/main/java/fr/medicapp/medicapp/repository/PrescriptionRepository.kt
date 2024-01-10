package fr.medicapp.medicapp.repository

import fr.medicapp.medicapp.dao.PrescriptionDAO
import fr.medicapp.medicapp.database.IDatabase
import fr.medicapp.medicapp.entity.PrescriptionEntity

class PrescriptionRepository(
    private val prescriptionDao: PrescriptionDAO
) : IDatabase<PrescriptionEntity> {
    override fun getAll(): List<PrescriptionEntity> {
        return prescriptionDao.getAll()
    }

    override fun <E> getOne(id: E): PrescriptionEntity {
        return prescriptionDao.getOne(id)
    }

    override fun add(t: PrescriptionEntity) {
        prescriptionDao.add(t)
    }

    override fun addAll(vararg t: PrescriptionEntity) {
        prescriptionDao.addAll(*t)
    }

    override fun delete(t: PrescriptionEntity) {
        prescriptionDao.delete(t)
    }

    override fun deleteAll(vararg t: PrescriptionEntity) {
        prescriptionDao.deleteAll(*t)
    }

    override fun update(t: PrescriptionEntity) {
        prescriptionDao.update(t)
    }

    override fun updateAll(vararg t: PrescriptionEntity) {
        prescriptionDao.updateAll(*t)
    }
}