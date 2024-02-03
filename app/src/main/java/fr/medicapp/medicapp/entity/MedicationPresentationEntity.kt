package fr.medicapp.medicapp.entity

import java.time.LocalDate

data class MedicationPresentationEntity(
    var cisCode: Long,
    var cip7Code: Long,
    var presentationLabel: String,
    var presentationStatus: String,
    var presentationCommercializationStatus: String,
    var commercializationDeclarationDate: LocalDate?,
    var cip13Code: Long,
    var approvalForCommunities: Boolean?,
    var reimbursementRates: MutableList<Float>,
    var priceWithoutHonoraryInEuro: Float?,
    var priceWithHonoraryInEuro: Float?,
    var priceHonoraryInEuro: Float?,
    var reimbursementIndications: String
)
