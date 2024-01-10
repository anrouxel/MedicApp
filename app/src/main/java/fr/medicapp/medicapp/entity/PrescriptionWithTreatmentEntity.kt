package fr.medicapp.medicapp.entity

import androidx.room.Embedded
import androidx.room.Relation

data class PrescriptionWithTreatmentEntity(
    @Embedded
    val prescription: PrescriptionEntity,

    @Relation(
        parentColumn = "prescriptionId",
        entityColumn = "treatmentId"
    )
    val treatments: List<TreatmentEntity>
)