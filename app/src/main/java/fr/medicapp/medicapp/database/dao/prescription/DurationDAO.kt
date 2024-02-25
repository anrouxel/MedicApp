package fr.medicapp.medicapp.database.dao.prescription

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import fr.medicapp.medicapp.model.prescription.Duration

@Dao
interface DurationDAO {
    @Transaction
    @Query("SELECT * FROM Duration")
    fun getAll(): List<Duration>

    @Insert
    fun insert(duration: Duration): Long

    @Update
    fun update(duration: Duration)

    @Delete
    fun delete(duration: Duration)
}
