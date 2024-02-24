package fr.medicapp.medicapp.model.medication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TransparencyCommissionOpinionLinks(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "transparency_commission_opinion_links_id")
    val id: Long = 0L,

    var hasDossierCode: String = "",

    var commissionOpinionLink: String? = null
)
