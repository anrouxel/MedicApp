package fr.medicapp.medicapp.model

import java.time.LocalDate

data class Prescription(
    var id: Int,
    var date: LocalDate,
    var treatments: List<Treatment> = mutableListOf()
)
