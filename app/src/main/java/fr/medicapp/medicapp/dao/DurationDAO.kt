package fr.medicapp.medicapp.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.Duration

interface DurationDAO {
    @Query("SELECT * FROM Duration")
    fun getDurationAll(): List<Duration>

    @Insert
    fun addDuration(duration: Duration)

    @Delete
    fun deleteDuration(duration: Duration)

    @Update
    fun updateDuration(duration: Duration)
}