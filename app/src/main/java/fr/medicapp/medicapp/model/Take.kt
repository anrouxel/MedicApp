package fr.medicapp.medicapp.model

import fr.medicapp.medicapp.model.prescription.relationship.Prescription
import java.time.LocalDateTime

data class Take(
    val id: Long,
    val prescription: Prescription,
    val date: LocalDateTime
)
