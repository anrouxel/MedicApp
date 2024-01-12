package fr.medicapp.medicapp.repository

import fr.medicapp.medicapp.dao.SideEffectDAO
import fr.medicapp.medicapp.dao.UserDAO
import fr.medicapp.medicapp.entity.SideEffectEntity
import fr.medicapp.medicapp.entity.UserEntity

class SideEffectRepository(
    private val userDao: SideEffectDAO
) {
    fun getAll(): List<SideEffectEntity> {
        return userDao.getAll()
    }

    fun getOne(id: String): SideEffectEntity {
        return userDao.getOne(id)
    }

    fun getByMedicament(medicament: String): List<SideEffectEntity> {
        return userDao.getByMedicament(medicament)
    }

    fun add(t: SideEffectEntity) {
        userDao.add(t)
    }

    fun addAll(vararg t: SideEffectEntity) {
        userDao.addAll(*t)
    }

    fun delete(t: SideEffectEntity) {
        userDao.delete(t)
    }

    fun deleteAll(vararg t: SideEffectEntity) {
        userDao.deleteAll(*t)
    }

    fun update(t: SideEffectEntity) {
        userDao.update(t)
    }

    fun updateAll(vararg t: SideEffectEntity) {
        userDao.updateAll(*t)
    }
}