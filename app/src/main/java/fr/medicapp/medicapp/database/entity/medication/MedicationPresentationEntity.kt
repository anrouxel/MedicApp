package fr.medicapp.medicapp.database.entity.medication

import fr.medicapp.medicapp.database.converter.EntityToModelMapper
import fr.medicapp.medicapp.database.converter.LocalDateConverter
import fr.medicapp.medicapp.database.converter.MutableListFloatConverter
import fr.medicapp.medicapp.entity.medication.MedicationPresentation
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
) : EntityToModelMapper<MedicationPresentation> {
    override fun convert(): MedicationPresentation {
        return MedicationPresentation(
            id,
            cisCode,
            cip7Code,
            presentationLabel,
            presentationStatus,
            presentationCommercializationStatus,
            commercializationDeclarationDate,
            cip13Code,
            approvalForCommunities,
            reimbursementRates,
            priceWithoutHonoraryInEuro,
            priceWithHonoraryInEuro,
            priceHonoraryInEuro,
            reimbursementIndications
        )
    }
}

