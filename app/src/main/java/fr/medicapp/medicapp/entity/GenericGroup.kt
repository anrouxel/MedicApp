package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Table nommée GenericGroup.
 * Fait partie de la Medication.
 * Elle est définie par le cisCode
 * */
@Entity(tableName = "GenericGroup")
data class GenericGroup(
    val genericGroupId: String,
    val genericGroupLabel: String,
    @PrimaryKey val cisCode: String,
    val genericType: String,
    val sortNumber: Int?
)
