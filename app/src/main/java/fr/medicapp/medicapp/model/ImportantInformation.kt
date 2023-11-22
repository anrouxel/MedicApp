package fr.medicapp.medicapp.model

import java.util.Date

data class ImportantInformation(
    val cisCode: String,
    val safetyInformationStartDate: Date?,
    val safetyInformationEndDate: Date?,
    val safetyInformationLink: String
)