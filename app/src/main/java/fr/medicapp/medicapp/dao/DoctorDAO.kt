package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.DoctorEntity

@Dao
interface DoctorDAO {
    @Query("SELECT * FROM DoctorEntity")
    fun getAll(): List<DoctorEntity>

    @Query("SELECT * FROM DoctorEntity WHERE id = :id")
    fun getOne(id: Int): DoctorEntity

    @Insert
    fun add(t: DoctorEntity)

    @Insert
    fun addAll(vararg t: DoctorEntity)

    @Delete
    fun delete(t: DoctorEntity)

    @Delete
    fun deleteAll(vararg t: DoctorEntity)

    @Update
    fun update(t: DoctorEntity)

    @Update
    fun updateAll(vararg t: DoctorEntity)
}
