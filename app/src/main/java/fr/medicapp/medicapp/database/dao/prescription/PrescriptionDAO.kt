package fr.medicapp.medicapp.database.dao.prescription

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import fr.medicapp.medicapp.model.prescription.PrescriptionInformation
import fr.medicapp.medicapp.model.prescription.relationship.Prescription

@Dao
interface PrescriptionDAO {
    @Transaction
    @Query("SELECT * FROM PrescriptionInformation")
    fun getAll(): List<Prescription>

    @Transaction
    @Query("SELECT * FROM PrescriptionInformation WHERE prescription_id = :id")
    fun getById(id: Long): Prescription

    @Insert
    fun insert(prescriptionInformation: PrescriptionInformation): Long

    @Update
    fun update(prescriptionInformation: PrescriptionInformation)

    @Delete
    fun delete(prescriptionInformation: PrescriptionInformation)
}
