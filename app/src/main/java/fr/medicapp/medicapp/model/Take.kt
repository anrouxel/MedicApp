package fr.medicapp.medicapp.model

import java.time.LocalDateTime

data class Take(
    val id: Long,
    val prescription: Prescription,
    val date: LocalDateTime
)
