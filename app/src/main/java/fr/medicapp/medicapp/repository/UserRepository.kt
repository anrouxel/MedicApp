package fr.medicapp.medicapp.repository

import fr.medicapp.medicapp.dao.UserDAO
import fr.medicapp.medicapp.database.IDatabase
import fr.medicapp.medicapp.entity.UserEntity

class UserRepository(
    private val userDao: UserDAO
) : IDatabase<UserEntity> {
    override fun getAll(): List<UserEntity> {
        return userDao.getAll()
    }

    override fun <E> getOne(id: E): UserEntity {
        return userDao.getOne(id)
    }

    override fun add(t: UserEntity) {
        userDao.add(t)
    }

    override fun addAll(vararg t: UserEntity){
        userDao.addAll(*t)
    }

    override fun delete(t: UserEntity) {
        userDao.delete(t)
    }

    override fun deleteAll(vararg t: UserEntity) {
        userDao.deleteAll(*t)
    }

    override fun update(t: UserEntity)  {
         userDao.update(t)
    }

    override fun updateAll(vararg t: UserEntity) {
        userDao.updateAll(*t)
    }
}