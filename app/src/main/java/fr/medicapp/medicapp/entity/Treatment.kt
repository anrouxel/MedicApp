package fr.medicapp.medicapp.entity

import java.util.Date

data class Treatment(
    val medication: Medication,
    val frequencies: List<Frequency>,
    val duration: Duration,
    val lastPlugin: Date
)
