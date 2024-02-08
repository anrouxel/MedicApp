package fr.medicapp.medicapp.entity.medication

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

data class TransparencyCommissionOpinionLinks(
    val id: Long = 0L,

    var hasDossierCode: String = "",

    var commissionOpinionLink: String? = null
)
