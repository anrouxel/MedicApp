package fr.medicapp.medicapp.database.dao.medication.crossRef

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import fr.medicapp.medicapp.model.medication.relationship.crossRef.MedicationMedicationCompositionCrossRef

@Dao
interface MedicationMedicationCompositionCrossRefDAO {
    @Transaction
    @Query("SELECT * FROM MedicationMedicationCompositionCrossRef")
    fun getAll(): List<MedicationMedicationCompositionCrossRef>

    @Insert
    fun insert(medicationMedicationCompositionCrossRef: MedicationMedicationCompositionCrossRef): Long

    @Insert
    fun insert(medicationMedicationCompositionCrossRef: List<MedicationMedicationCompositionCrossRef>): List<Long>

    @Delete
    fun delete(medicationMedicationCompositionCrossRef: MedicationMedicationCompositionCrossRef)
}