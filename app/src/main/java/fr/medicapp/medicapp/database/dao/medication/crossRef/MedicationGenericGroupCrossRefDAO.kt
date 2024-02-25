package fr.medicapp.medicapp.database.dao.medication.crossRef

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import fr.medicapp.medicapp.model.medication.relationship.crossRef.MedicationGenericGroupCrossRef

@Dao
interface MedicationGenericGroupCrossRefDAO {
    @Transaction
    @Query("SELECT * FROM MedicationGenericGroupCrossRef")
    fun getAll(): List<MedicationGenericGroupCrossRef>

    @Insert
    fun insert(medicationGenericGroupCrossRef: MedicationGenericGroupCrossRef): Long

    @Insert
    fun insert(medicationGenericGroupCrossRef: List<MedicationGenericGroupCrossRef>): List<Long>

    @Delete
    fun delete(medicationGenericGroupCrossRef: MedicationGenericGroupCrossRef)
}
