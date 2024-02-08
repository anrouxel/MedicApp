package fr.medicapp.medicapp.model

import java.time.LocalDate

data class Prescription(
    val id: Long = 0L,

    var date: LocalDate? = null,

    var doctor: Doctor? = null,

    var treatments: MutableList<Treatment> = mutableListOf(),
)
