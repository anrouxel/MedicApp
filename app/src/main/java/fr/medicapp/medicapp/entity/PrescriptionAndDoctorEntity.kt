package fr.medicapp.medicapp.entity

import androidx.room.Embedded
import androidx.room.Relation

data class PrescriptionAndDoctorEntity(
    @Embedded val prescription: PrescriptionEntity,

    @Relation(
        parentColumn = "prescriptionId",
        entityColumn = "doctorId"
    )
    val doctor: DoctorEntity
)