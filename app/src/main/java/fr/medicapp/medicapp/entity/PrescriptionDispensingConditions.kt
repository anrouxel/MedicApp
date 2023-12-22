package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Table nommée PrescriptionDispensingConditions.
 * Définie par son cisCode, elle est utilisée par Medication.
 * */
@Entity(tableName = "PrescriptionDispensingConditions")
data class PrescriptionDispensingConditions(
    @PrimaryKey val cisCode: String,
    val prescriptionDispensingCondition: String
)
