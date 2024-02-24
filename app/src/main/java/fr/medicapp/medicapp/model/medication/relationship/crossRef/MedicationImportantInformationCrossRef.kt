package fr.medicapp.medicapp.model.medication.relationship.crossRef

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["medication_id", "important_information_id"])
data class MedicationImportantInformationCrossRef (
    @ColumnInfo(name = "medication_id")
    var medicationId: Long = 0L,

    @ColumnInfo(name = "important_information_id")
    var importantInformationId: Long = 0L
)
