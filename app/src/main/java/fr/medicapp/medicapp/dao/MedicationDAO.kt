package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.Medication

@Dao
interface MedicationDAO {

    @Query("SELECT * FROM Medication")
    fun getMedications():List<Medication>

    @Query("SELECT * FROM Medication m WHERE m.cisCode = :cisCode")
    fun getMedication(cisCode: String):Medication

    @Insert
    fun addMedication(medication: Medication)

    @Delete
    fun deleteMedication(medication: Medication)

    @Update
    fun updateMedication(medication: Medication)
}