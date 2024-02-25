package fr.medicapp.medicapp.database.dao.medication.crossRef

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import fr.medicapp.medicapp.model.medication.relationship.crossRef.MedicationPrescriptionDispensingConditionsCrossRef

@Dao
interface MedicationPrescriptionDispensingConditionsCrossRefDAO {
    @Transaction
    @Query("SELECT * FROM MedicationPrescriptionDispensingConditionsCrossRef")
    fun getAll(): List<MedicationPrescriptionDispensingConditionsCrossRef>

    @Insert
    fun insert(medicationPrescriptionDispensingConditionsCrossRef: MedicationPrescriptionDispensingConditionsCrossRef): Long

    @Insert
    fun insert(medicationPrescriptionDispensingConditionsCrossRef: List<MedicationPrescriptionDispensingConditionsCrossRef>): List<Long>

    @Delete
    fun delete(medicationPrescriptionDispensingConditionsCrossRef: MedicationPrescriptionDispensingConditionsCrossRef)
}