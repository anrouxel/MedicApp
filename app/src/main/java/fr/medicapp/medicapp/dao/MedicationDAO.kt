package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.MedicationEntity
import fr.medicapp.medicapp.entity.UserEntity

@Dao
interface MedicationDAO {
    @Query("SELECT * FROM Medications")
    fun getAll(): List<MedicationEntity>

    @Query("SELECT * FROM Medications WHERE CommercializationStatus NOT LIKE 'NON COMMERCIALISÉE'")
    fun getAllWithoutNotTreadings(): List<MedicationEntity>

    @Query("SELECT * FROM Medications WHERE cisCode = :id")
    fun getOne(id: String): MedicationEntity

    @Insert
    fun add(t: MedicationEntity)

    @Insert
    fun addAll(vararg t: MedicationEntity)

    @Delete
    fun delete(t: MedicationEntity)

    @Delete
    fun deleteAll(vararg t: MedicationEntity)

    @Update
    fun update(t: MedicationEntity)

    @Update
    fun updateAll(vararg t: MedicationEntity)
}