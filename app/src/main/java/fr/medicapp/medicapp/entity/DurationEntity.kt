package fr.medicapp.medicapp.entity

import fr.medicapp.medicapp.model.Duration
import java.time.LocalDate

data class DurationEntity(
    var startDate: LocalDate,

    var endDate: LocalDate
) {
    fun toDuration(): Duration {
        return Duration(
            startDate = startDate,
            endDate = endDate
        )
    }
}