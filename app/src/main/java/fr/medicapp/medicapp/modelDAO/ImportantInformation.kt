package fr.medicapp.medicapp.modelDAO

import java.util.Date

data class ImportantInformation(
    val cisCode: String,
    val safetyInformationStartDate: Date?,
    val safetyInformationEndDate: Date?,
    val safetyInformationLink: String
)