package fr.medicapp.medicapp.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import fr.medicapp.medicapp.model.prescription.Doctor

@Dao
interface DoctorDAO {
    @Transaction
    @Query("SELECT * FROM Doctor")
    fun getAll(): List<Doctor>

    @Insert
    fun insert(doctor: Doctor): Long

    @Update
    fun update(doctor: Doctor)

    @Delete
    fun delete(doctor: Doctor)
}
