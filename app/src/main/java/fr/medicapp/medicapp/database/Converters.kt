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
    // MutableList<String> to String
    fun fromEffetsConstates(effetsConstates: MutableList<String>): String {
        return effetsConstates.joinToString(separator = "\"")
    }

    @TypeConverter
    // String to MutableList<String>
    fun toEffetsConstates(effetsConstates: String): MutableList<String> {
        return effetsConstates.split("\"").toMutableList()
    }
}