package fr.medicapp.medicapp.entity

import fr.medicapp.medicapp.database.LocalDateConverter
import fr.medicapp.medicapp.database.MutableListFloatConverter
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.time.LocalDate

@Entity
data class MedicationPresentationEntity(
    @Id
    var id: Long = 0L,

    var cisCode: Long = 0L,

    var cip7Code: Long = 0L,

    var presentationLabel: String = "",

    var presentationStatus: String = "",

    var presentationCommercializationStatus: String = "",

    @Convert(converter = LocalDateConverter::class, dbType = String::class)
    var commercializationDeclarationDate: LocalDate? = null,

    var cip13Code: Long = 0L,

    var approvalForCommunities: Boolean? = null,

    @Convert(converter = MutableListFloatConverter::class, dbType = String::class)
    var reimbursementRates: MutableList<Float> = mutableListOf(),

    var priceWithoutHonoraryInEuro: Float? = null,

    var priceWithHonoraryInEuro: Float? = null,

    var priceHonoraryInEuro: Float? = null,

    var reimbursementIndications: String = "",
)