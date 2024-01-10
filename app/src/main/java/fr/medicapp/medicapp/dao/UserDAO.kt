package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.UserEntity

@Dao
interface UserDAO {
    @Query("SELECT * FROM UserEntity")
    fun getAll(): List<UserEntity>

    @Query("SELECT * FROM UserEntity WHERE id = :id")
    fun getOne(id: String): UserEntity

    @Insert
    fun add(t: UserEntity)

    @Insert
    fun addAll(vararg t: UserEntity)

    @Delete
    fun delete(t: UserEntity)

    @Delete
    fun deleteAll(vararg t: UserEntity)

    @Update
    fun update(t: UserEntity)

    @Update
    fun updateAll(vararg t: UserEntity)
}