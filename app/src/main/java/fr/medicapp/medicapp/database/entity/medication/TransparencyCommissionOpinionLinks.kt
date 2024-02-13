package fr.medicapp.medicapp.database.entity.medication

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class TransparencyCommissionOpinionLinks(
    @Id
    var id: Long = 0L,

    var hasDossierCode: String = "",

    var commissionOpinionLink: String? = null
)
