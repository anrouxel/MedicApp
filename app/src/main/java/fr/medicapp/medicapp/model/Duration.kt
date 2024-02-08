package fr.medicapp.medicapp.model

import java.time.LocalDate

data class Duration(
    val id: Long = 0L,

    var startDate: LocalDate? = null,

    var endDate: LocalDate? = null
)
