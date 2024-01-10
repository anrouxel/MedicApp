package fr.medicapp.medicapp.database

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalDate
import java.util.UUID

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
    fun fromList(list: List<*>): String {
        return list.toString()
    }

    @TypeConverter
    fun ToString(list: List<*>): String {
        return list.toString()
    }

    @TypeConverter
    fun toList(datastring: String): MutableList<String> {
        if(datastring == null || datastring.isEmpty()) {
            return mutableListOf()
        }
        return mutableListOf(datastring)
    }
}