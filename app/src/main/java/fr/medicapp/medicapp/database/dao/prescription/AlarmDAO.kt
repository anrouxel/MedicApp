package fr.medicapp.medicapp.database.dao.prescription

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import fr.medicapp.medicapp.model.prescription.Alarm

@Dao
interface AlarmDAO {
    @Transaction
    @Query("SELECT * FROM Alarm")
    fun getAll(): List<Alarm>

    @Insert
    fun insert(alarm: Alarm): Long

    @Insert
    fun insert(alarm: List<Alarm>): List<Long>

    @Update
    fun update(alarm: Alarm)

    @Delete
    fun delete(alarm: Alarm)
}
