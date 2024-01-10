package fr.medicapp.medicapp.repository

import fr.medicapp.medicapp.dao.DoctorDAO
import fr.medicapp.medicapp.database.IDatabase
import fr.medicapp.medicapp.entity.DoctorEntity

class DoctorRepository(
    private val doctorDao: DoctorDAO
) : IDatabase<DoctorEntity> {
    override fun getAll(): List<DoctorEntity> {
        return doctorDao.getAll()
    }

    override fun <E> getOne(id: E): DoctorEntity {
        return doctorDao.getOne(id)
    }

    override fun add(t: DoctorEntity) {
        doctorDao.add(t)
    }

    override fun addAll(vararg t: DoctorEntity) {
        doctorDao.addAll(*t)
    }

    override fun delete(t: DoctorEntity) {
        doctorDao.delete(t)
    }

    override fun deleteAll(vararg t: DoctorEntity) {
        doctorDao.deleteAll(*t)
    }

    override fun update(t: DoctorEntity) {
        doctorDao.update(t)
    }

    override fun updateAll(vararg t: DoctorEntity) {
        doctorDao.updateAll(*t)
    }
}