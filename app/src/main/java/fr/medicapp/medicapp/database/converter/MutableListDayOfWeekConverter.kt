package fr.medicapp.medicapp.database.converter

import androidx.room.TypeConverter
import java.time.DayOfWeek

class MutableListDayOfWeekConverter {
    @TypeConverter
    fun fromMutableListDayOfWeek(mutableListDayOfWeek: String): MutableList<DayOfWeek> {
        if (mutableListDayOfWeek.isEmpty()) {
            return mutableListOf()
        }
        return mutableListDayOfWeek.split(",").map { DayOfWeek.valueOf(it) }.toMutableList()
    }

    @TypeConverter
    fun toMutableListDayOfWeek(mutableListDayOfWeek: List<DayOfWeek>): String {
        return mutableListDayOfWeek.joinToString(",")
    }
}