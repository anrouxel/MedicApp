package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.PrescriptionEntity

@Dao
interface PrescriptionDAO {
    @Query("SELECT * FROM PrescriptionEntity")
    fun getAll(): List<PrescriptionEntity>

    @Query("SELECT * FROM PrescriptionEntity WHERE id = :id")
    fun getOne(id: Int): PrescriptionEntity

    @Insert
    fun add(t: PrescriptionEntity)

    @Insert
    fun addAll(vararg t: PrescriptionEntity)

    @Delete
    fun delete(t: PrescriptionEntity)

    @Delete
    fun deleteAll(vararg t: PrescriptionEntity)

    @Update
    fun update(t: PrescriptionEntity)

    @Update
    fun updateAll(vararg t: PrescriptionEntity)
}