package fr.medicapp.medicapp.database

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalDate

class Converters {
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromLocalDate(localDate: String): LocalDate {
        return LocalDate.parse(localDate)
    }

    @TypeConverter
    fun toLocalDate(localDate: LocalDate): String {
        return localDate.toString()
    }

    @TypeConverter
    fun toMutableList(list: String): MutableList<String> {
        return toList(list).toMutableList()
    }

    @TypeConverter
    fun toList(list: String): List<String> {
        val cleanedList = list.substring(1 until list.length - 1)
        return cleanedList.split(",").map { it.trim() }
    }

    @TypeConverter
    fun fromMutableList(list: MutableList<String>): String {
        return fromList(list)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return list.toString()
    }
}
