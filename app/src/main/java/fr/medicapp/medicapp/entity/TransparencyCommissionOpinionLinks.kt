package fr.medicapp.medicapp.entity

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Table nommée TransparencyCommissionOpinionLinks.
 * Attention : Utilise un Uri.
 * */
@Entity(tableName = "TransparencyCommissionOpinionLinks")
data class TransparencyCommissionOpinionLinks(
    @PrimaryKey val hasDossierCode: String,
    val commissionOpinionLink: Uri?
)
