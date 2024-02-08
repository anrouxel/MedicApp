package fr.medicapp.medicapp.entity.medication

import fr.medicapp.medicapp.database.LocalDateConverter
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.time.LocalDate

data class PharmaceuticalSpecialty(
    val id: Long = 0L,

    var cisCode: Long = 0L,

    var cip13Code: String = "",

    var statusCode: Int = 0,

    var statusLabel: String = "",

    var startDate: LocalDate? = null,

    var updateDate: LocalDate? = null,

    var returnToDate: LocalDate? = null,

    var ansmSiteLink: String = "",
)
