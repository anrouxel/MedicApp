package fr.medicapp.medicapp.database.converter

import io.objectbox.converter.PropertyConverter
import java.time.DayOfWeek

class MutableListDayOfWeekConverter : PropertyConverter<MutableList<DayOfWeek>, String> {
    override fun convertToDatabaseValue(entityProperty: MutableList<DayOfWeek>?): String? {
        return entityProperty?.joinToString(separator = ",")
    }

    override fun convertToEntityProperty(databaseValue: String?): MutableList<DayOfWeek>? {
        return databaseValue?.split(",")?.map { DayOfWeek.valueOf(it) }?.toMutableList()
    }
}
