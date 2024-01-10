package fr.medicapp.medicapp.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

@Entity
data class PrescriptionWithTreatmentEntity(
    @Embedded
    val prescription: PrescriptionEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val treatments: List<TreatmentEntity>
)