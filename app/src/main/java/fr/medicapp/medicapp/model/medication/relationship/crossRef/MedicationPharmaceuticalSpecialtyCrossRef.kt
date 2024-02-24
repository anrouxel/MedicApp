package fr.medicapp.medicapp.model.medication.relationship.crossRef

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["medication_id", "pharmaceutical_specialty_id"])
data class MedicationPharmaceuticalSpecialtyCrossRef (
    @ColumnInfo(name = "medication_id")
    var medicationId: Long = 0L,

    @ColumnInfo(name = "pharmaceutical_specialty_id")
    var pharmaceuticalSpecialtyId: Long = 0L
)
