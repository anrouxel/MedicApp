package fr.medicapp.medicapp.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.PrescriptionAndDoctorEntity

interface PrescriptionAndDoctorDAO {
    @Query("SELECT * FROM PrescriptionAndDoctorEntity")
    fun getAll(): List<PrescriptionAndDoctorEntity>

    @Query("SELECT * FROM PrescriptionAndDoctorEntity WHERE id = :id")
    fun getOne(id: String): PrescriptionAndDoctorEntity

    @Insert
    fun add(t: PrescriptionAndDoctorEntity)

    @Insert
    fun addAll(vararg t: PrescriptionAndDoctorEntity)

    @Delete
    fun delete(t: PrescriptionAndDoctorEntity)

    @Delete
    fun deleteAll(vararg t: PrescriptionAndDoctorEntity)

    @Update
    fun update(t: PrescriptionAndDoctorEntity)

    @Update
    fun updateAll(vararg t: PrescriptionAndDoctorEntity)
}