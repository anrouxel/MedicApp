package fr.medicapp.medicapp.database.dao.medication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import fr.medicapp.medicapp.model.medication.MedicationInformation
import fr.medicapp.medicapp.model.medication.relationship.Medication

@Dao
interface MedicationDAO {
    @Transaction
    @Query("SELECT * FROM MedicationInformation")
    fun getAll(): List<Medication>

    @Insert
    fun insert(medicationInformation: MedicationInformation): Long

    @Insert
    fun insert(medicationInformation: List<MedicationInformation>): List<Long>

    @Update
    fun update(medicationInformation: MedicationInformation)

    @Delete
    fun delete(medicationInformation: MedicationInformation)
}
