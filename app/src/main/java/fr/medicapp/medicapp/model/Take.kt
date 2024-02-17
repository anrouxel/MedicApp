package fr.medicapp.medicapp.model

import java.time.LocalDate
import java.time.LocalDateTime

data class Take(
    val prescription: Prescription,
    val date: LocalDateTime
)
