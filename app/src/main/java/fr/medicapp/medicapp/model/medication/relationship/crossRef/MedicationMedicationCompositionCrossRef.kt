package fr.medicapp.medicapp.model.medication.relationship.crossRef

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["medication_id", "medication_composition_id"])
data class MedicationMedicationCompositionCrossRef (
    @ColumnInfo(name = "medication_id")
    var medicationId: Long = 0L,

    @ColumnInfo(name = "medication_composition_id")
    var medicationCompositionId: Long = 0L
)
