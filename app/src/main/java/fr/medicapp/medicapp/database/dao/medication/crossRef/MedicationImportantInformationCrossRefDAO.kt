package fr.medicapp.medicapp.database.dao.medication.crossRef

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import fr.medicapp.medicapp.model.medication.relationship.crossRef.MedicationImportantInformationCrossRef

@Dao
interface MedicationImportantInformationCrossRefDAO {
    @Transaction
    @Query("SELECT * FROM MedicationImportantInformationCrossRef")
    fun getAll(): List<MedicationImportantInformationCrossRef>

    @Insert
    fun insert(medicationImportantInformationCrossRef: MedicationImportantInformationCrossRef): Long

    @Insert
    fun insert(medicationImportantInformationCrossRef: List<MedicationImportantInformationCrossRef>): List<Long>

    @Delete
    fun delete(medicationImportantInformationCrossRef: MedicationImportantInformationCrossRef)
}
