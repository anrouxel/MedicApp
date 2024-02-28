package fr.medicapp.medicapp.database.converter

import androidx.room.TypeConverter
import java.time.LocalTime

class LocalTimeConverter {
    @TypeConverter
    fun fromLocalTime(localTime: String): LocalTime {
        return LocalTime.parse(localTime)
    }

    @TypeConverter
    fun toLocalTime(localTime: LocalTime): String {
        return localTime.toString()
    }
}
