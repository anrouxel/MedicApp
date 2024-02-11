package fr.medicapp.medicapp.model

import fr.medicapp.medicapp.database.converter.ModelToEntityMapper
import fr.medicapp.medicapp.database.entity.TreatmentEntity
import fr.medicapp.medicapp.model.medication.Medication

data class Treatment(
    var id: Long = 0L,

    var posology: String = "",

    var frequency: String = "",

    var medication: Medication? = null,

    var duration: Duration? = null
) : ModelToEntityMapper<TreatmentEntity> {
    override fun convert(): TreatmentEntity {
        val treatment = TreatmentEntity(
            id,
            posology,
            frequency
        )
        treatment.medication.target = medication?.convert()
        treatment.duration.target = duration?.convert()
        return treatment
    }
}
