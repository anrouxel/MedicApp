package fr.medicapp.medicapp.repository

import fr.medicapp.medicapp.dao.PrescriptionWithTreatmentDAO
import fr.medicapp.medicapp.database.IDatabase
import fr.medicapp.medicapp.entity.PrescriptionWithTreatmentEntity

class PrescriptionWithTreatmentRepository(
    private val prescriptionWithTreatmentDao: PrescriptionWithTreatmentDAO
) : IDatabase<PrescriptionWithTreatmentEntity> {
    override fun getAll(): List<PrescriptionWithTreatmentEntity> {
        return prescriptionWithTreatmentDao.getAll()
    }

    override fun <E> getOne(id: E): PrescriptionWithTreatmentEntity {
        return prescriptionWithTreatmentDao.getOne(id)
    }

    override fun add(t: PrescriptionWithTreatmentEntity) {
        prescriptionWithTreatmentDao.add(t)
    }

    override fun addAll(vararg t: PrescriptionWithTreatmentEntity) {
        prescriptionWithTreatmentDao.addAll(*t)
    }

    override fun delete(t: PrescriptionWithTreatmentEntity) {
        prescriptionWithTreatmentDao.delete(t)
    }

    override fun deleteAll(vararg t: PrescriptionWithTreatmentEntity) {
        prescriptionWithTreatmentDao.deleteAll(*t)
    }

    override fun update(t: PrescriptionWithTreatmentEntity) {
        prescriptionWithTreatmentDao.update(t)
    }

    override fun updateAll(vararg t: PrescriptionWithTreatmentEntity) {
        prescriptionWithTreatmentDao.updateAll(*t)
    }
}