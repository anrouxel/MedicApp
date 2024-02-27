package fr.medicapp.medicapp.model.medication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class TransparencyCommissionOpinionLinks(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "transparency_commission_opinion_links_id")
    val id: Long = 0L,

    @SerializedName("HasDossierCode")
    var hasDossierCode: String = "",

    @SerializedName("CommissionOpinionLink")
    var commissionOpinionLink: String? = null,
)
