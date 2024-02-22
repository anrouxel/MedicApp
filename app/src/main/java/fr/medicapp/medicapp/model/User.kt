package fr.medicapp.medicapp.model

import java.time.LocalDate

data class User(
    val lastName: String = "",
    val firstName: String = "",
    val birthday: LocalDate = LocalDate.now(),
    val gender: String = "",
    val pregnant: Boolean = false,
    val allergies: MutableList<String>? = null
)
