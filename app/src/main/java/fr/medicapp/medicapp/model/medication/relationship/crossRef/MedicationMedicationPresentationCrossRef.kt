package fr.medicapp.medicapp.model.medication.relationship.crossRef

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["medication_id", "medication_presentation_id"])
data class MedicationMedicationPresentationCrossRef (
    @ColumnInfo(name = "medication_id")
    var medicationId: Long = 0L,

    @ColumnInfo(name = "medication_presentation_id")
    var medicationPresentationId: Long = 0L
)
