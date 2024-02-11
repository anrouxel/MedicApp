package fr.medicapp.medicapp.entity.medication

import fr.medicapp.medicapp.database.converter.EntityToModelMapper
import fr.medicapp.medicapp.database.converter.ModelToEntityMapper
import fr.medicapp.medicapp.database.entity.medication.TransparencyCommissionOpinionLinksEntity
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

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
