package fr.medicapp.medicapp.entity.medication

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class TransparencyCommissionOpinionLinksEntity(
    @Id
    var id: Long = 0L,

    var hasDossierCode: String = "",

    var commissionOpinionLink: String? = null
)
