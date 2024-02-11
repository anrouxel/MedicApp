package fr.medicapp.medicapp.database.converter

import io.objectbox.converter.PropertyConverter

class MutableListIntConverter : PropertyConverter<MutableList<Int>, String> {
    override fun convertToDatabaseValue(entityProperty: MutableList<Int>?): String? {
        return entityProperty?.joinToString(separator = ",")
    }

    override fun convertToEntityProperty(databaseValue: String?): MutableList<Int>? {
        return databaseValue?.split(",")?.mapNotNull { it.trim().toIntOrNull() }?.toMutableList()
    }
}
