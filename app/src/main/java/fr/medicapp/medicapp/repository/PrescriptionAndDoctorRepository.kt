package fr.medicapp.medicapp.repository

import fr.medicapp.medicapp.dao.PrescriptionAndDoctorDAO
import fr.medicapp.medicapp.database.IDatabase
import fr.medicapp.medicapp.entity.PrescriptionAndDoctorEntity

class PrescriptionAndDoctorRepository(
    private val prescriptionAndDoctorDAO: PrescriptionAndDoctorDAO
) : IDatabase<PrescriptionAndDoctorEntity> {
    override fun getAll(): List<PrescriptionAndDoctorEntity> {
        return prescriptionAndDoctorDAO.getAll()
    }

    override fun <E> getOne(id: E): PrescriptionAndDoctorEntity {
        return prescriptionAndDoctorDAO.getOne(id)
    }

    override fun add(t: PrescriptionAndDoctorEntity) {
        prescriptionAndDoctorDAO.add(t)
    }

    override fun addAll(vararg t: PrescriptionAndDoctorEntity) {
        prescriptionAndDoctorDAO.addAll(*t)
    }

    override fun delete(t: PrescriptionAndDoctorEntity) {
        prescriptionAndDoctorDAO.delete(t)
    }

    override fun deleteAll(vararg t: PrescriptionAndDoctorEntity) {
        prescriptionAndDoctorDAO.deleteAll(*t)
    }

    override fun update(t: PrescriptionAndDoctorEntity) {
        prescriptionAndDoctorDAO.update(t)
    }

    override fun updateAll(vararg t: PrescriptionAndDoctorEntity) {
        prescriptionAndDoctorDAO.updateAll(*t)
    }
}