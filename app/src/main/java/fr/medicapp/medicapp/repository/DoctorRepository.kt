package fr.medicapp.medicapp.repository

import fr.medicapp.medicapp.dao.DoctorDAO
import fr.medicapp.medicapp.entity.DoctorEntity

class DoctorRepository(
    private val doctorDao: DoctorDAO
) {
    fun getAll(): List<DoctorEntity> {
        return doctorDao.getAll()
    }

    fun getOne(id: Int): DoctorEntity {
        return doctorDao.getOne(id)
    }

    fun add(t: DoctorEntity) {
        doctorDao.add(t)
    }

    fun addAll(vararg t: DoctorEntity) {
        doctorDao.addAll(*t)
    }

    fun delete(t: DoctorEntity) {
        doctorDao.delete(t)
    }

    fun deleteAll(vararg t: DoctorEntity) {
        doctorDao.deleteAll(*t)
    }

    fun update(t: DoctorEntity) {
        doctorDao.update(t)
    }

    fun updateAll(vararg t: DoctorEntity) {
        doctorDao.updateAll(*t)
    }
}