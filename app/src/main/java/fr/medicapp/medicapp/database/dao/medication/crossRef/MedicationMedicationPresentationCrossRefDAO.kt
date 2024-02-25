package fr.medicapp.medicapp.database.dao.medication.crossRef

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import fr.medicapp.medicapp.model.medication.relationship.crossRef.MedicationMedicationPresentationCrossRef

@Dao
interface MedicationMedicationPresentationCrossRefDAO {
    @Transaction
    @Query("SELECT * FROM MedicationMedicationPresentationCrossRef")
    fun getAll(): List<MedicationMedicationPresentationCrossRef>

    @Insert
    fun insert(medicationMedicationPresentationCrossRef: MedicationMedicationPresentationCrossRef): Long

    @Insert
    fun insert(medicationMedicationPresentationCrossRef: List<MedicationMedicationPresentationCrossRef>): List<Long>

    @Delete
    fun delete(medicationMedicationPresentationCrossRef: MedicationMedicationPresentationCrossRef)
}