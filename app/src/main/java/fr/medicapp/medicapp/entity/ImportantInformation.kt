package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 * Table nommée ImportantInformation.
 * Définie par le cisCode, elle est utilisée par Medication.
 * */
@Entity(tableName = "ImportantInformation")
data class ImportantInformation(
    @PrimaryKey val cisCode: String,
    val safetyInformationStartDate: Date?,
    val safetyInformationEndDate: Date?,
    val safetyInformationLink: String
)