package fr.medicapp.medicapp.entity

import android.net.Uri
import androidx.room.Entity

@Entity(tableName = "TransparencyCommissionOpinionLinks")
data class TransparencyCommissionOpinionLinks(
    val hasDossierCode : String,
    val commissionOpinionLink : Uri?
)