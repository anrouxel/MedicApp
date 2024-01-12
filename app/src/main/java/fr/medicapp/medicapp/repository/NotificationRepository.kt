package fr.medicapp.medicapp.repository

import fr.medicapp.medicapp.dao.DoctorDAO
import fr.medicapp.medicapp.dao.NotificationDAO
import fr.medicapp.medicapp.entity.DoctorEntity
import fr.medicapp.medicapp.entity.NotificationEntity

class NotificationRepository(
    private val doctorDao: NotificationDAO
) {
    fun getAll(): List<NotificationEntity> {
        return doctorDao.getAll()
    }

    fun getOne(id: String): NotificationEntity {
        return doctorDao.getOne(id)
    }

    fun getByMedicament(medicament: String): List<NotificationEntity> {
        return doctorDao.getByMedicament(medicament)
    }

    fun add(t: NotificationEntity) {
        doctorDao.add(t)
    }

    fun addAll(vararg t: NotificationEntity) {
        doctorDao.addAll(*t)
    }

    fun delete(t: NotificationEntity) {
        doctorDao.delete(t)
    }

    fun deleteAll(vararg t: NotificationEntity) {
        doctorDao.deleteAll(*t)
    }

    fun update(t: NotificationEntity) {
        doctorDao.update(t)
    }

    fun updateAll(vararg t: NotificationEntity) {
        doctorDao.updateAll(*t)
    }
}