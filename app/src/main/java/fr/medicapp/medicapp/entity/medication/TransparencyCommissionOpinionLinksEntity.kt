package fr.medicapp.medicapp.entity.medication

import fr.medicapp.medicapp.database.EntityToModelMapper
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

