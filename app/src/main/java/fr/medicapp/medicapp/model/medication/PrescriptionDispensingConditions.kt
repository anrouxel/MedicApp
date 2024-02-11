package fr.medicapp.medicapp.entity.medication

import fr.medicapp.medicapp.database.converter.EntityToModelMapper
import fr.medicapp.medicapp.database.converter.ModelToEntityMapper
import fr.medicapp.medicapp.database.entity.medication.PrescriptionDispensingConditionsEntity
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

data class PrescriptionDispensingConditions(
    val id: Long = 0L,

    var cisCode: Long = 0L,

    var prescriptionDispensingCondition: String = ""
) : ModelToEntityMapper<PrescriptionDispensingConditionsEntity> {
    override fun convert(): PrescriptionDispensingConditionsEntity {
        return PrescriptionDispensingConditionsEntity(
            id,
            cisCode,
            prescriptionDispensingCondition
        )
    }
}
