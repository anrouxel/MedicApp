package fr.medicapp.medicapp.database.dao.medication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import fr.medicapp.medicapp.model.medication.ImportantInformation

@Dao
interface ImportantInformationDAO {
    @Transaction
    @Query("SELECT * FROM ImportantInformation")
    fun getAll(): List<ImportantInformation>

    @Insert
    fun insert(importantInformation: ImportantInformation): Long

    @Insert
    fun insert(importantInformation: List<ImportantInformation>): List<Long>

    @Delete
    fun delete(importantInformation: ImportantInformation)
}
