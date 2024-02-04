package fr.medicapp.medicapp.database

import android.os.Build
import androidx.annotation.RequiresApi
import io.objectbox.converter.PropertyConverter
import java.time.LocalDate

class LocalDateConverter : PropertyConverter<LocalDate, String> {
    override fun convertToDatabaseValue(entityProperty: LocalDate?): String? {
        return entityProperty?.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun convertToEntityProperty(databaseValue: String?): LocalDate? {
        return databaseValue?.let { LocalDate.parse(it) }
    }
}
