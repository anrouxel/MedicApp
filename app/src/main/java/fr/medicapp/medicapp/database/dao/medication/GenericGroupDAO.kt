package fr.medicapp.medicapp.database.dao.medication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import fr.medicapp.medicapp.model.medication.GenericGroup

@Dao
interface GenericGroupDAO {
    @Transaction
    @Query("SELECT * FROM GenericGroup")
    fun getAll(): List<GenericGroup>

    @Insert
    fun insert(genericGroup: GenericGroup): Long

    @Insert
    fun insert(genericGroup: List<GenericGroup>): List<Long>

    @Delete
    fun delete(genericGroup: GenericGroup)
}