package fr.medicapp.medicapp.database.converter

import androidx.room.TypeConverter

class MutableListFloatConverter {
    @TypeConverter
    fun fromMutableListFloat(mutableListFloat: String): MutableList<Float> {
        if (mutableListFloat.isEmpty()) {
            return mutableListOf()
        }
        return mutableListFloat.split(",").map { it.toFloat() }.toMutableList()
    }

    @TypeConverter
    fun toMutableListFloat(mutableListFloat: List<Float>): String {
        return mutableListFloat.joinToString(",")
    }
}