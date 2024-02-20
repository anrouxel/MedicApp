package fr.medicapp.medicapp.database.converter

import io.objectbox.converter.PropertyConverter
import java.time.LocalDateTime

class MutableListLocalDateTimeConverter : PropertyConverter<MutableList<LocalDateTime>, String> {
    override fun convertToDatabaseValue(entityProperty: MutableList<LocalDateTime>): String {
        return entityProperty.joinToString(separator = ",")
    }

    override fun convertToEntityProperty(databaseValue: String): MutableList<LocalDateTime> {
        return if (databaseValue.isNotEmpty()) databaseValue.split(",").map {
            LocalDateTime.parse(it)
        }.toMutableList()
        else mutableListOf()
    }
}
