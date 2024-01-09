package fr.medicapp.medicapp.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.Frequency

interface FrequencyDAO {
    @Query("SELECT * FROM Frequency")
    fun getFrequencyAll():List<Frequency>

    @Insert
    fun addFrequency(frequency: Frequency)

    @Delete
    fun deleteFrequency(frequency: Frequency)

    @Update
    fun updateFrequency(frequency: Frequency)
}