package fr.medicapp.medicapp.database.converter

import androidx.room.TypeConverter
import java.time.LocalDateTime

class MutableListLocalDateTimeConverter {
    @TypeConverter
    fun fromMutableListLocalDateTime(mutableListLocalDateTime: String): MutableList<LocalDateTime> {
        if (mutableListLocalDateTime.isEmpty()) {
            return mutableListOf()
        }
        return mutableListLocalDateTime.split(",").map { LocalDateTime.parse(it) }.toMutableList()
    }

    @TypeConverter
    fun toMutableListLocalDateTime(mutableListLocalDateTime: List<LocalDateTime>): String {
        return mutableListLocalDateTime.joinToString(",")
    }
}