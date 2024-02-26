package fr.medicapp.medicapp.database.converter

import androidx.room.TypeConverter

class MutableListStringConverter {
    @TypeConverter
    fun fromMutableListString(mutableListString: String): MutableList<String> {
        return mutableListString.split(",").toMutableList()
    }

    @TypeConverter
    fun toMutableListString(mutableListString: MutableList<String>): String {
        return mutableListString.joinToString(",")
    }
}