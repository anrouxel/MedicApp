package fr.medicapp.medicapp.model.medication.relationship.crossRef

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["medication_id", "prescription_dispensing_conditions_id"])
data class MedicationPrescriptionDispensingConditionsCrossRef (
    @ColumnInfo(name = "medication_id")
    var medicationId: Long = 0L,

    @ColumnInfo(name = "prescription_dispensing_conditions_id")
    var prescriptionDispensingConditionsId: Long = 0L
)
