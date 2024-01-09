package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.Prescription

@Dao
interface PrescriptionDAO {

    @Query("SELECT * FROM Prescription")
    fun getPrescriptionAll(): List<Prescription>

    @Query("SELECT * FROM Prescription p WHERE p.id = :id")
    fun getPrescriptionOne(id: Int): Prescription?

    @Insert
    fun addPrescription(prescription: Prescription)

    @Delete
    fun deletePrescription(prescription: Prescription)

    @Update
    fun updatePrescription(prescription: Prescription)
}