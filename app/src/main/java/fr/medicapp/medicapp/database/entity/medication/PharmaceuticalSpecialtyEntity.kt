package fr.medicapp.medicapp.database.entity.medication

import fr.medicapp.medicapp.database.converter.EntityToModelMapper
import fr.medicapp.medicapp.database.converter.LocalDateConverter
import fr.medicapp.medicapp.entity.medication.PharmaceuticalSpecialty
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.time.LocalDate

@Entity
data class PharmaceuticalSpecialtyEntity(
    @Id
    var id: Long = 0L,

    var cisCode: Long = 0L,

    var cip13Code: String = "",

    var statusCode: Int = 0,

    var statusLabel: String = "",

    @Convert(converter = LocalDateConverter::class, dbType = String::class)
    var startDate: LocalDate? = null,

    @Convert(converter = LocalDateConverter::class, dbType = String::class)
    var updateDate: LocalDate? = null,

    @Convert(converter = LocalDateConverter::class, dbType = String::class)
    var returnToDate: LocalDate? = null,

    var ansmSiteLink: String = "",
) : EntityToModelMapper<PharmaceuticalSpecialty> {
    override fun convert(): PharmaceuticalSpecialty {
        return PharmaceuticalSpecialty(
            id,
            cisCode,
            cip13Code,
            statusCode,
            statusLabel,
            startDate,
            updateDate,
            returnToDate,
            ansmSiteLink
        )
    }
}
