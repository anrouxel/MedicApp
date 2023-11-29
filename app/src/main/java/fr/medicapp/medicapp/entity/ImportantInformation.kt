package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "ImportantInformation")
data class ImportantInformation(
    @PrimaryKey val cisCode: String,
    val safetyInformationStartDate: Date?,
    val safetyInformationEndDate: Date?,
    val safetyInformationLink: String
)