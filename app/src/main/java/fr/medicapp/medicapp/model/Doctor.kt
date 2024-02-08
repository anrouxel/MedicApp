package fr.medicapp.medicapp.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

data class Doctor(
    val id: Long = 0L,

    var rpps: Long = 0L,

    var name: String = "",
)
