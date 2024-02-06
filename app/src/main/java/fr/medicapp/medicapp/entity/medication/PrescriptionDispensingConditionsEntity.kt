package fr.medicapp.medicapp.entity.medication

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class PrescriptionDispensingConditionsEntity(
    @Id
    var id: Long = 0L,

    var cisCode: Long = 0L,

    var prescriptionDispensingCondition: String = ""
)
