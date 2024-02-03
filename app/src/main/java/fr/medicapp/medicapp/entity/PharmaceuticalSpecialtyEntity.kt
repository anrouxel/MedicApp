package fr.medicapp.medicapp.entity

import java.time.LocalDate

data class PharmaceuticalSpecialtyEntity(
    var cisCode: Long,
    var cip13Code: String,
    var statusCode: Int,
    var statusLabel: String,
    var startDate: LocalDate?,
    var updateDate: LocalDate?,
    var returnToDate: LocalDate?,
    var ansmSiteLink: String
)
