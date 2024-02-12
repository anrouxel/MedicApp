package fr.medicapp.medicapp.model.medication

import android.content.Context
import fr.medicapp.medicapp.database.converter.ModelToEntityMapper
import fr.medicapp.medicapp.database.entity.medication.PrescriptionDispensingConditionsEntity

data class PrescriptionDispensingConditions(
    val id: Long = 0L,

    var cisCode: Long = 0L,

    var prescriptionDispensingCondition: String = ""
) : ModelToEntityMapper<PrescriptionDispensingConditionsEntity> {
    override fun convert(context: Context): PrescriptionDispensingConditionsEntity {
        return PrescriptionDispensingConditionsEntity(
            id,
            cisCode,
            prescriptionDispensingCondition
        )
    }
}
