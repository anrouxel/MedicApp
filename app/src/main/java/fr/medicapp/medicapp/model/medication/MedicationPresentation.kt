package fr.medicapp.medicapp.model.medication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class MedicationPresentation(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "medication_presentation_id")
    val id: Long = 0L,

    var cisCode: Long = 0L,

    var cip7Code: Long = 0L,

    var presentationLabel: String = "",

    var presentationStatus: String = "",

    var presentationCommercializationStatus: String = "",

    var commercializationDeclarationDate: LocalDate? = null,

    var cip13Code: Long = 0L,

    var approvalForCommunities: Boolean? = null,

    var reimbursementRates: MutableList<Float> = mutableListOf(),

    var priceWithoutHonoraryInEuro: Float? = null,

    var priceWithHonoraryInEuro: Float? = null,

    var priceHonoraryInEuro: Float? = null,

    var reimbursementIndications: String = "",

    @ColumnInfo(name = "medication_id")
    var medicationId: Long = 0L
)