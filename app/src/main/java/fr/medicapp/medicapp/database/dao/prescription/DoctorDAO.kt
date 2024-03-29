package fr.medicapp.medicapp.database.dao.prescription

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

    @Transaction
    @Query("SELECT * FROM Doctor WHERE doctor_id = :id")
    fun getById(id: Long): Doctor?

    @Transaction
    @Query("SELECT * FROM Doctor WHERE nationalId = :nationalId")
    fun getByNationalId(nationalId: Long): Doctor?

    @Insert
    fun insert(doctor: Doctor): Long

    @Insert
    fun insert(doctor: List<Doctor>): List<Long>

    @Update
    fun update(doctor: Doctor)

    @Delete
    fun delete(doctor: Doctor)
}
