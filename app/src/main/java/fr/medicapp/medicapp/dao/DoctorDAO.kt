package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.database.IDatabase
import fr.medicapp.medicapp.entity.DoctorEntity

@Dao
interface DoctorDAO : IDatabase<DoctorEntity> {
    @Query("SELECT * FROM DoctorEntity")
    override fun getAll(): List<DoctorEntity>

    @Query("SELECT * FROM DoctorEntity WHERE id = :id")
    override fun <E> getOne(id: E): DoctorEntity

    @Insert
    override fun add(t: DoctorEntity)

    @Insert
    override fun addAll(vararg t: DoctorEntity)

    @Delete
    override fun delete(t: DoctorEntity)

    @Delete
    override fun deleteAll(vararg t: DoctorEntity)

    @Update
    override fun update(t: DoctorEntity)

    @Update
    override fun updateAll(vararg t: DoctorEntity)
}
