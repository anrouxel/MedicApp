package fr.medicapp.medicapp.model.medication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PrescriptionDispensingConditions(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "prescription_dispensing_conditions_id")
    val id: Long = 0L,

    var cisCode: Long = 0L,

    var prescriptionDispensingCondition: String = "",

    @ColumnInfo(name = "medication_id")
    var medicationId: Long = 0L
)