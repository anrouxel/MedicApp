package fr.medicapp.medicapp.entity

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TransparencyCommissionOpinionLinks")
data class TransparencyCommissionOpinionLinks(
    @PrimaryKey val hasDossierCode : String,
    val commissionOpinionLink : Uri?
)