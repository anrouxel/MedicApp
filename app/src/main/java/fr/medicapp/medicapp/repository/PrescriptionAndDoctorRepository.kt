package fr.medicapp.medicapp.repository

import fr.medicapp.medicapp.entity.PrescriptionAndDoctorEntity

class PrescriptionAndDoctorRepository(
    private val prescriptionAndDoctorDAO: PrescriptionAndDoctorDAO
) {
    fun getAll(): List<PrescriptionAndDoctorEntity> {
        return prescriptionAndDoctorDAO.getAll()
    }

    fun getOne(id: String): PrescriptionAndDoctorEntity {
        return prescriptionAndDoctorDAO.getOne(id)
    }

    fun add(t: PrescriptionAndDoctorEntity) {
        prescriptionAndDoctorDAO.add(t)
    }

    fun addAll(vararg t: PrescriptionAndDoctorEntity) {
        prescriptionAndDoctorDAO.addAll(*t)
    }

    fun delete(t: PrescriptionAndDoctorEntity) {
        prescriptionAndDoctorDAO.delete(t)
    }

    fun deleteAll(vararg t: PrescriptionAndDoctorEntity) {
        prescriptionAndDoctorDAO.deleteAll(*t)
    }

    fun update(t: PrescriptionAndDoctorEntity) {
        prescriptionAndDoctorDAO.update(t)
    }

    fun updateAll(vararg t: PrescriptionAndDoctorEntity) {
        prescriptionAndDoctorDAO.updateAll(*t)
    }
}