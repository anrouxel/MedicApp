package fr.medicapp.medicapp.database

import io.objectbox.converter.PropertyConverter

class MutableListFloatConverter : PropertyConverter<MutableList<Float>, String> {
    override fun convertToDatabaseValue(entityProperty: MutableList<Float>?): String? {
        return entityProperty?.joinToString(separator = ",")
    }

    override fun convertToEntityProperty(databaseValue: String?): MutableList<Float>? {
        return databaseValue?.split(",")?.mapNotNull { it.trim().toFloatOrNull() }?.toMutableList()
    }
}
