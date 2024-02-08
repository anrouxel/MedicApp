package fr.medicapp.medicapp.entity.medication

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

data class MedicationComposition(
    val id: Long = 0L,

    var cisCode: Long = 0L,

    var pharmaceuticalElementDesignation: String = "",

    var substanceCode: Long = 0L,

    var substanceName: String = "",

    var substanceDosage: String = "",

    var dosageReference: String = "",

    var componentNature: String = "",

    var linkNumber: Int? = null
)
