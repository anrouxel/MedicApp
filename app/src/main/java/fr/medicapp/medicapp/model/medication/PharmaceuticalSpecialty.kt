package fr.medicapp.medicapp.model.medication

import fr.medicapp.medicapp.database.converter.ModelToEntityMapper
import fr.medicapp.medicapp.database.entity.medication.PharmaceuticalSpecialtyEntity
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
) : ModelToEntityMapper<PharmaceuticalSpecialtyEntity> {
    override fun convert(): PharmaceuticalSpecialtyEntity {
        return PharmaceuticalSpecialtyEntity(
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
