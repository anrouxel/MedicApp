package fr.medicapp.medicapp.repository

import fr.medicapp.medicapp.dao.PrescriptionWithTreatmentDAO
import fr.medicapp.medicapp.entity.PrescriptionWithTreatmentEntity

class PrescriptionWithTreatmentRepository(
    private val prescriptionWithTreatmentDao: PrescriptionWithTreatmentDAO
) {
    fun getAll(): List<PrescriptionWithTreatmentEntity> {
        return prescriptionWithTreatmentDao.getAll()
    }

    fun getOne(id: Int): PrescriptionWithTreatmentEntity {
        return prescriptionWithTreatmentDao.getOne(id)
    }

    fun add(t: PrescriptionWithTreatmentEntity) {
        prescriptionWithTreatmentDao.add(t)
    }

    fun addAll(vararg t: PrescriptionWithTreatmentEntity) {
        prescriptionWithTreatmentDao.addAll(*t)
    }

    fun delete(t: PrescriptionWithTreatmentEntity) {
        prescriptionWithTreatmentDao.delete(t)
    }

    fun deleteAll(vararg t: PrescriptionWithTreatmentEntity) {
        prescriptionWithTreatmentDao.deleteAll(*t)
    }

    fun update(t: PrescriptionWithTreatmentEntity) {
        prescriptionWithTreatmentDao.update(t)
    }

    fun updateAll(vararg t: PrescriptionWithTreatmentEntity) {
        prescriptionWithTreatmentDao.updateAll(*t)
    }
}