package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "MedicationPresentation")
data class MedicationPresentation(
    @PrimaryKey val cisCode: String,
    val cip7Code: String,
    val presentationLabel: String,
    val presentationStatus: String,
    val presentationCommercializationStatus: String,
    val commercializationDeclarationDate: Date?,
    val cip13Code: String,
    val approvalForCommunities: Boolean?,
    val reimbursementRates: List<Float>,
    val priceWithoutHonoraryInEuro: Float?,
    val priceWithHonoraryInEuro: Float?,
    val priceHonoraryInEuro: Float?,
    val reimbursementIndications: String
)