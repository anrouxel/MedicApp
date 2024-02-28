package fr.medicapp.medicapp.database.dao.medication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import fr.medicapp.medicapp.model.medication.HasSmrOpinionInformation

@Dao
interface HasSmrOpinionDAO {
    @Transaction
    @Query("SELECT * FROM hassmropinioninformation")
    fun getAll(): List<HasSmrOpinionInformation>

    @Insert
    fun insert(hasSmrOpinionInformation: HasSmrOpinionInformation): Long

    @Insert
    fun insert(hasSmrOpinionInformation: List<HasSmrOpinionInformation>): List<Long>

    @Delete
    fun delete(hasSmrOpinionInformation: HasSmrOpinionInformation)
}
