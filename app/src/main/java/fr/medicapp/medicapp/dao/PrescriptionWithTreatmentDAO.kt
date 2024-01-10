package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.PrescriptionWithTreatmentEntity

@Dao
interface PrescriptionWithTreatmentDAO {
    @Query("SELECT * FROM PrescriptionWithTreatmentEntity")
    fun getAll(): List<PrescriptionWithTreatmentEntity>

    @Query("SELECT * FROM PrescriptionWithTreatmentEntity WHERE id = :id")
    fun getOne(id: Int): PrescriptionWithTreatmentEntity

    @Insert
    fun add(t: PrescriptionWithTreatmentEntity)

    @Insert
    fun addAll(vararg t: PrescriptionWithTreatmentEntity)

    @Delete
    fun delete(t: PrescriptionWithTreatmentEntity)

    @Delete
    fun deleteAll(vararg t: PrescriptionWithTreatmentEntity)

    @Update
    fun update(t: PrescriptionWithTreatmentEntity)

    @Update
    fun updateAll(vararg t: PrescriptionWithTreatmentEntity)
}