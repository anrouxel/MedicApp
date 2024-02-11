package fr.medicapp.medicapp.database.entity.medication

import fr.medicapp.medicapp.database.converter.EntityToModelMapper
import fr.medicapp.medicapp.model.medication.TransparencyCommissionOpinionLinks
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class TransparencyCommissionOpinionLinksEntity(
    @Id
    var id: Long = 0L,

    var hasDossierCode: String = "",

    var commissionOpinionLink: String? = null
) : EntityToModelMapper<TransparencyCommissionOpinionLinks> {
    override fun convert(): TransparencyCommissionOpinionLinks {
        return TransparencyCommissionOpinionLinks(
            id,
            hasDossierCode,
            commissionOpinionLink
        )
    }
}
