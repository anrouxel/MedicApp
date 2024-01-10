package fr.medicapp.medicapp.repository

import fr.medicapp.medicapp.dao.UserDAO
import fr.medicapp.medicapp.entity.UserEntity

class UserRepository(
    private val userDao: UserDAO
) {
    fun getAll(): List<UserEntity> {
        return userDao.getAll()
    }

    fun getOne(id: String): UserEntity {
        return userDao.getOne(id)
    }

    fun add(t: UserEntity) {
        userDao.add(t)
    }

    fun addAll(vararg t: UserEntity) {
        userDao.addAll(*t)
    }

    fun delete(t: UserEntity) {
        userDao.delete(t)
    }

    fun deleteAll(vararg t: UserEntity) {
        userDao.deleteAll(*t)
    }

    fun update(t: UserEntity) {
        userDao.update(t)
    }

    fun updateAll(vararg t: UserEntity) {
        userDao.updateAll(*t)
    }
}