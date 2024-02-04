package fr.medicapp.medicapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "TransparencyCommissionOpinionLinks", foreignKeys =
    arrayOf(
        ForeignKey(entity = HasAsmrOpinionsEntity::class, parentColumns = ["Id"], childColumns = ["HasAsmrOpinionId"]),
        ForeignKey(entity = HasSmrOpinionsEntity::class, parentColumns = ["Id"], childColumns = ["HasSmrOpinionId"])
    )
)

data class TransparencyCommissionOpinionLinksEntity(

    /**
     * L'identifiant unique du lien de l'opinion de la commission de transparence.
     */
    @PrimaryKey
    @ColumnInfo(name = "Id")
    val id: String,

    /**
     * Code du dossier HAS
     */
    @ColumnInfo(name = "HasDossierCode")
    val hasDossierCode: String,

    /**
     * lien de l'opinion de la commission
     */
    @ColumnInfo(name = "CommissionOpinionLink")
    val commissionOpinionLink: String,

    /**
     * id de l'opinion asmr
     */

    @ColumnInfo(name = "HasAsmrOpinionId")
    val hasAsmrOpinionId: String,

    /**
     * id de l'opinion smr
     */
    @ColumnInfo(name = "HasSmrOpinionId")
    val hasSmrOpinionId: String
)
