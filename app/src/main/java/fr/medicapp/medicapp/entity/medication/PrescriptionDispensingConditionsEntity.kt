package fr.medicapp.medicapp.entity.medication

import fr.medicapp.medicapp.database.EntityToModelMapper
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class PrescriptionDispensingConditionsEntity(
    @Id
    var id: Long = 0L,

    var cisCode: Long = 0L,

    var prescriptionDispensingCondition: String = ""
) : EntityToModelMapper<PrescriptionDispensingConditions> {
    override fun convert(): PrescriptionDispensingConditions {
        return PrescriptionDispensingConditions(
            id,
            cisCode,
            prescriptionDispensingCondition
        )
    }
}
