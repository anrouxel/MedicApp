package fr.medicapp.medicapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import fr.medicapp.medicapp.model.prescription.Doctor
import fr.medicapp.medicapp.model.prescription.relationship.Prescription

@Dao
interface PrescriptionDAO {
    @Transaction
    @Query("SELECT * FROM PrescriptionInformation")
    fun getAll(): List<Prescription>
}
