package fr.medicapp.medicapp.entity

data class Prescription(
    val doctor: Doctor,
    val treatments: List<Treatment>,
)
