package fr.medicapp.medicapp.model.medication

import android.content.Context
import fr.medicapp.medicapp.database.converter.ModelToEntityMapper
import fr.medicapp.medicapp.database.entity.medication.MedicationPresentationEntity
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
) : ModelToEntityMapper<MedicationPresentationEntity> {
    override fun convert(context: Context): MedicationPresentationEntity {
        return MedicationPresentationEntity(
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
