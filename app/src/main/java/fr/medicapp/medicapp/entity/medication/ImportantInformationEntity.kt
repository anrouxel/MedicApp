package fr.medicapp.medicapp.entity.medication

import fr.medicapp.medicapp.database.LocalDateConverter
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.time.LocalDate

@Entity
data class ImportantInformationEntity(
    @Id
    var id: Long = 0L,

    var cisCode: Long = 0L,

    @Convert(converter = LocalDateConverter::class, dbType = String::class)
    var safetyInformationStartDate: LocalDate? = null,

    @Convert(converter = LocalDateConverter::class, dbType = String::class)
    var safetyInformationEndDate: LocalDate? = null,

    var safetyInformationLink: String = ""
)
