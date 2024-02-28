package fr.medicapp.medicapp.database.dao.medication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import fr.medicapp.medicapp.model.medication.MedicationComposition

@Dao
interface MedicationCompositionDAO {
    @Transaction
    @Query("SELECT * FROM MedicationComposition")
    fun getAll(): List<MedicationComposition>

    @Insert
    fun insert(medicationComposition: MedicationComposition): Long

    @Insert
    fun insert(medicationComposition: List<MedicationComposition>): List<Long>

    @Delete
    fun delete(medicationComposition: MedicationComposition)
}
