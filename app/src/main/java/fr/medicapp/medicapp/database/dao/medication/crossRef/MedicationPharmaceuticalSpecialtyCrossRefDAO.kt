package fr.medicapp.medicapp.database.dao.medication.crossRef

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import fr.medicapp.medicapp.model.medication.relationship.crossRef.MedicationPharmaceuticalSpecialtyCrossRef

@Dao
interface MedicationPharmaceuticalSpecialtyCrossRefDAO {
    @Transaction
    @Query("SELECT * FROM MedicationPharmaceuticalSpecialtyCrossRef")
    fun getAll(): List<MedicationPharmaceuticalSpecialtyCrossRef>

    @Insert
    fun insert(medicationPharmaceuticalSpecialtyCrossRef: MedicationPharmaceuticalSpecialtyCrossRef): Long

    @Insert
    fun insert(medicationPharmaceuticalSpecialtyCrossRef: List<MedicationPharmaceuticalSpecialtyCrossRef>): List<Long>

    @Delete
    fun delete(medicationPharmaceuticalSpecialtyCrossRef: MedicationPharmaceuticalSpecialtyCrossRef)
}