package fr.medicapp.medicapp.entity

import java.time.LocalDate

data class ImportantInformationEntity(
    var cisCode: Long,
    var safetyInformationStartDate: LocalDate?,
    var safetyInformationEndDate: LocalDate?,
    var safetyInformationLink: String
)
