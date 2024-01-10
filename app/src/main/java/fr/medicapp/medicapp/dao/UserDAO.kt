package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.database.IDatabase
import fr.medicapp.medicapp.entity.UserEntity

@Dao
interface UserDAO : IDatabase<UserEntity> {
    @Query("SELECT * FROM UserEntity")
    override fun getAll(): List<UserEntity>

    @Query("SELECT * FROM UserEntity WHERE id = :id")
    override fun <E> getOne(id: E): UserEntity

    @Insert
    override fun add(t: UserEntity)

    @Insert
    override fun addAll(vararg t: UserEntity)

    @Delete
    override fun delete(t: UserEntity)

    @Delete
    override fun deleteAll(vararg t: UserEntity)

    @Update
    override fun update(t: UserEntity)

    @Update
    override fun updateAll(vararg t: UserEntity)
}