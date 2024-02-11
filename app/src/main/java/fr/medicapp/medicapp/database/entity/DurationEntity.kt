package fr.medicapp.medicapp.database.entity

import fr.medicapp.medicapp.database.converter.EntityToModelMapper
import fr.medicapp.medicapp.database.converter.LocalDateConverter
import fr.medicapp.medicapp.model.Duration
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.time.LocalDate

@Entity
data class DurationEntity(
    @Id
    var id: Long = 0L,

    @Convert(converter = LocalDateConverter::class, dbType = String::class)
    var startDate: LocalDate? = null,

    @Convert(converter = LocalDateConverter::class, dbType = String::class)
    var endDate: LocalDate? = null
) : EntityToModelMapper<Duration> {
    override fun convert(): Duration {
        return Duration(
            id,
            startDate,
            endDate
        )
    }
}
