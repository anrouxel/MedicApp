package fr.medicapp.medicapp.database

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.DayOfWeek
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

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toMutableListDayOfWeek(list: String): MutableList<DayOfWeek> {
        return toListDayOfWeek(list).toMutableList()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toListDayOfWeek(list: String): List<DayOfWeek> {
        val cleanedList = list.substring(1 until list.length - 1)
        return cleanedList.split(",").map { DayOfWeek.of(it.trim().toInt()) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromMutableListDayOfWeek(list: MutableList<DayOfWeek>): String {
        return fromListDayOfWeek(list)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromListDayOfWeek(list: List<DayOfWeek>): String {
        return list.map { it.value }.toString()
    }

    @TypeConverter
    fun toMutableListInt(list: String): MutableList<Int> {
        return toListInt(list).toMutableList()
    }

    @TypeConverter
    fun toListInt(list: String): List<Int> {
        val cleanedList = list.substring(1 until list.length - 1)
        return cleanedList.split(",").map { it.trim().toInt() }
    }

    @TypeConverter
    fun fromMutableListInt(list: MutableList<Int>): String {
        return fromListInt(list)
    }

    @TypeConverter
    fun fromListInt(list: List<Int>): String {
        return list.toString()
    }
}
