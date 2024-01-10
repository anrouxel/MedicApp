package fr.medicapp.medicapp.repository

import fr.medicapp.medicapp.dao.PrescriptionDAO
import fr.medicapp.medicapp.entity.PrescriptionEntity

class PrescriptionRepository(
    private val prescriptionDao: PrescriptionDAO
) {
    fun getAll(): List<PrescriptionEntity> {
        return prescriptionDao.getAll()
    }

    fun getOne(id: String): PrescriptionEntity {
        return prescriptionDao.getOne(id)
    }

    fun add(t: PrescriptionEntity) {
        prescriptionDao.add(t)
    }

    fun addAll(vararg t: PrescriptionEntity) {
        prescriptionDao.addAll(*t)
    }

    fun delete(t: PrescriptionEntity) {
        prescriptionDao.delete(t)
    }

    fun deleteAll(vararg t: PrescriptionEntity) {
        prescriptionDao.deleteAll(*t)
    }

    fun update(t: PrescriptionEntity) {
        prescriptionDao.update(t)
    }

    fun updateAll(vararg t: PrescriptionEntity) {
        prescriptionDao.updateAll(*t)
    }
}