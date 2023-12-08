package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.Doctor

@Dao
interface DoctorDAO {

    @Query("SELECT * FROM Doctor")
    fun getDoctorAll():List<Doctor>

    @Query("SELECT * FROM Doctor d WHERE d.id = :id")
    fun getDoctorOne(id:Int):Doctor

    @Query("SELECT * FROM Doctor d WHERE d.email = :email")
    fun getDoctorOneByEmail(email:String):Doctor

    @Insert
    fun addDoctor(doctor: Doctor)

    @Delete
    fun deleteDoctor(doctor: Doctor)

    @Update
    fun updateDoctor(doctor: Doctor)

}