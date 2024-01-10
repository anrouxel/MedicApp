package fr.medicapp.medicapp.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

@Entity
data class PrescriptionWithTreatmentEntity(
    @Embedded
    val prescription: PrescriptionAndDoctorEntity,

    @Relation(
        parentColumn = "prescriptionId",
        entityColumn = "treatmentId"
    )
    val treatments: List<TreatmentEntity>
)