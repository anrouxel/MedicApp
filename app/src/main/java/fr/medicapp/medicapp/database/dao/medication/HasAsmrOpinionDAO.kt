package fr.medicapp.medicapp.database.dao.medication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import fr.medicapp.medicapp.model.medication.HasAsmrOpinionInformation

@Dao
interface HasAsmrOpinionDAO {
    @Transaction
    @Query("SELECT * FROM HasAsmrOpinionInformation")
    fun getAll(): List<HasAsmrOpinionInformation>

    @Insert
    fun insert(hasAsmrOpinionInformation: HasAsmrOpinionInformation): Long

    @Insert
    fun insert(hasAsmrOpinionInformation: List<HasAsmrOpinionInformation>): List<Long>

    @Delete
    fun delete(hasAsmrOpinionInformation: HasAsmrOpinionInformation)
}
