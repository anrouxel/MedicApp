package fr.medicapp.medicapp.model.medication

import android.content.Context
import fr.medicapp.medicapp.database.converter.ModelToEntityMapper
import fr.medicapp.medicapp.database.entity.medication.TransparencyCommissionOpinionLinksEntity

data class TransparencyCommissionOpinionLinks(
    val id: Long = 0L,

    var hasDossierCode: String = "",

    var commissionOpinionLink: String? = null
) : ModelToEntityMapper<TransparencyCommissionOpinionLinksEntity> {
    override fun convert(context: Context): TransparencyCommissionOpinionLinksEntity {
        return TransparencyCommissionOpinionLinksEntity(
            id,
            hasDossierCode,
            commissionOpinionLink
        )
    }
}
