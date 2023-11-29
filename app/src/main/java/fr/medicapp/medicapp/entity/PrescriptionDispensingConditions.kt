package fr.medicapp.medicapp.entity

import androidx.room.Entity

@Entity(tableName = "PrescriptionDispensingConditions")
data class PrescriptionDispensingConditions(
    val cisCode: String,
    val prescriptionDispensingCondition: String
)