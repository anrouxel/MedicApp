package fr.medicapp.medicapp.database.dao.medication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import fr.medicapp.medicapp.model.medication.MedicationPresentation

@Dao
interface MedicationPresentationDAO {
    @Transaction
    @Query("SELECT * FROM MedicationPresentation")
    fun getAll(): List<MedicationPresentation>

    @Insert
    fun insert(medicationPresentation: MedicationPresentation): Long

    @Insert
    fun insert(medicationPresentation: List<MedicationPresentation>): List<Long>

    @Delete
    fun delete(medicationPresentation: MedicationPresentation)
}
