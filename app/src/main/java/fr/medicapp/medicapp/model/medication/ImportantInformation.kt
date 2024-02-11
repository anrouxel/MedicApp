package fr.medicapp.medicapp.model.medication

import fr.medicapp.medicapp.database.converter.ModelToEntityMapper
import fr.medicapp.medicapp.database.entity.medication.ImportantInformationEntity
import java.time.LocalDate

data class ImportantInformation(
    val id: Long = 0L,

    var cisCode: Long = 0L,

    var safetyInformationStartDate: LocalDate? = null,

    var safetyInformationEndDate: LocalDate? = null,

    var safetyInformationLink: String = ""
) : ModelToEntityMapper<ImportantInformationEntity> {
    override fun convert(): ImportantInformationEntity {
        return ImportantInformationEntity(
            id,
            cisCode,
            safetyInformationStartDate,
            safetyInformationEndDate,
            safetyInformationLink
        )
    }
}
