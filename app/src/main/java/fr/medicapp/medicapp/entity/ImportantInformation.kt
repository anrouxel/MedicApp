package fr.medicapp.medicapp.entity

import androidx.room.Entity
import java.util.Date

@Entity(tableName = "ImportantInformation")
data class ImportantInformation(
    val cisCode: String,
    val safetyInformationStartDate: Date?,
    val safetyInformationEndDate: Date?,
    val safetyInformationLink: String
)