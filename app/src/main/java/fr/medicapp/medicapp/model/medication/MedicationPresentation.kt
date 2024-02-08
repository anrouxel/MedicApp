package fr.medicapp.medicapp.entity.medication

import fr.medicapp.medicapp.database.LocalDateConverter
import fr.medicapp.medicapp.database.MutableListFloatConverter
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.time.LocalDate

data class MedicationPresentation(
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
)
