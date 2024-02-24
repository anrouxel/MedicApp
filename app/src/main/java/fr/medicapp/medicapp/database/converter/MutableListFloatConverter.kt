package fr.medicapp.medicapp.database.converter

import androidx.room.TypeConverter

class MutableListFloatConverter {
    @TypeConverter
    fun fromMutableListFloat(mutableListFloat: String): MutableList<Float> {
        return mutableListFloat.split(",").map { it.toFloat() }.toMutableList()
    }

    @TypeConverter
    fun toMutableListFloat(mutableListFloat: MutableList<Float>): String {
        return mutableListFloat.joinToString(",")
    }
}