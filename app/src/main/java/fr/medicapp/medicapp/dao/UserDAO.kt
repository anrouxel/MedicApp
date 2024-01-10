package fr.medicapp.medicapp.dao

import android.database.sqlite.SQLiteException
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
    @Throws(SQLiteException::class)
    override fun getAll(): List<UserEntity>

    @Query("SELECT * FROM UserEntity WHERE id = :id")
    @Throws(SQLiteException::class)
    override fun <E> getOne(id: E): UserEntity
    @Insert
    @Throws(SQLiteException::class)
    override fun add(t: UserEntity)

    @Insert
    @Throws(SQLiteException::class)
    override fun addAll(vararg t: UserEntity)

    @Delete
    @Throws(SQLiteException::class)
    override fun delete(t: UserEntity)

    @Delete
    @Throws(SQLiteException::class)
    override fun deleteAll(vararg t: UserEntity)

    @Update
    @Throws(SQLiteException::class)
    override fun update(t: UserEntity)

    @Update
    @Throws(SQLiteException::class)
    override fun updateAll(vararg t: UserEntity)
}