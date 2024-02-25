package fr.medicapp.medicapp.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import fr.medicapp.medicapp.model.medication.MedicationInformation
import fr.medicapp.medicapp.model.medication.relationship.Medication
import fr.medicapp.medicapp.model.prescription.Doctor
import fr.medicapp.medicapp.model.prescription.Duration

@Dao
interface MedicationDAO {
    @Transaction
    @Query("SELECT * FROM MedicationInformation")
    fun getAll(): List<Medication>

    @Insert
    fun insert(medicationInformation: MedicationInformation): Long

    @Update
    fun update(medicationInformation: MedicationInformation)

    @Delete
    fun delete(medicationInformation: MedicationInformation)
}
