package fr.medicapp.medicapp.entity.medication

import fr.medicapp.medicapp.database.LocalDateConverter
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.time.LocalDate

data class ImportantInformation(
    val id: Long = 0L,

    var cisCode: Long = 0L,

    var safetyInformationStartDate: LocalDate? = null,

    var safetyInformationEndDate: LocalDate? = null,

    var safetyInformationLink: String = ""
)
