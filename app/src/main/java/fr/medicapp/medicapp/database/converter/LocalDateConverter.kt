package fr.medicapp.medicapp.database.converter

import androidx.room.TypeConverter
import java.time.LocalDate

class LocalDateConverter {
    @TypeConverter
    fun fromLocalDate(localDate: String): LocalDate {
        return LocalDate.parse(localDate)
    }

    @TypeConverter
    fun toLocalDate(localDate: LocalDate): String {
        return localDate.toString()
    }
}
