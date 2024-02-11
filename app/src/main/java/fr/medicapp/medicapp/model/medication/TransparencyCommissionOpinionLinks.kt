package fr.medicapp.medicapp.model.medication

import fr.medicapp.medicapp.database.converter.ModelToEntityMapper
import fr.medicapp.medicapp.database.entity.medication.TransparencyCommissionOpinionLinksEntity

data class TransparencyCommissionOpinionLinks(
    val id: Long = 0L,

    var hasDossierCode: String = "",

    var commissionOpinionLink: String? = null
) : ModelToEntityMapper<TransparencyCommissionOpinionLinksEntity> {
    override fun convert(): TransparencyCommissionOpinionLinksEntity {
        return TransparencyCommissionOpinionLinksEntity(
            id,
            hasDossierCode,
            commissionOpinionLink
        )
    }
}
