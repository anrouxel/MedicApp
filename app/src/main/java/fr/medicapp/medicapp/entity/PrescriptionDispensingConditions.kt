package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PrescriptionDispensingConditions")
data class PrescriptionDispensingConditions(
    @PrimaryKey val cisCode: String,
    val prescriptionDispensingCondition: String
)