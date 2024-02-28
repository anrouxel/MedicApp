package fr.medicapp.medicapp.database.dao.medication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import fr.medicapp.medicapp.model.medication.PrescriptionDispensingConditions

@Dao
interface PrescriptionDispensingConditionsDAO {
    @Transaction
    @Query("SELECT * FROM PrescriptionDispensingConditions")
    fun getAll(): List<PrescriptionDispensingConditions>

    @Insert
    fun insert(prescriptionDispensingConditions: PrescriptionDispensingConditions): Long

    @Insert
    fun insert(prescriptionDispensingConditions: List<PrescriptionDispensingConditions>): List<Long>

    @Delete
    fun delete(prescriptionDispensingConditions: PrescriptionDispensingConditions)
}
