package fr.medicapp.medicapp.database.dao.medication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import fr.medicapp.medicapp.model.medication.PharmaceuticalSpecialty

@Dao
interface PharmaceuticalSpecialtyDAO {
    @Transaction
    @Query("SELECT * FROM PharmaceuticalSpecialty")
    fun getAll(): List<PharmaceuticalSpecialty>

    @Insert
    fun insert(pharmaceuticalSpecialty: PharmaceuticalSpecialty): Long

    @Insert
    fun insert(pharmaceuticalSpecialty: List<PharmaceuticalSpecialty>): List<Long>

    @Delete
    fun delete(pharmaceuticalSpecialty: PharmaceuticalSpecialty)
}
