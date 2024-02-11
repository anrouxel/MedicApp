package fr.medicapp.medicapp.model

import fr.medicapp.medicapp.database.converter.ModelToEntityMapper
import fr.medicapp.medicapp.database.entity.DurationEntity
import java.time.LocalDate

data class Duration(
    val id: Long = 0L,

    var startDate: LocalDate? = null,

    var endDate: LocalDate? = null
) : ModelToEntityMapper<DurationEntity> {
    override fun toString(): String {
        return "Du ${startDate?.toString()} au ${endDate?.toString()}"
    }

    override fun convert(): DurationEntity {
        return DurationEntity(
            id,
            startDate,
            endDate
        )
    }
}
