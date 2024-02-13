package fr.medicapp.medicapp.model

import android.content.Context
import fr.medicapp.medicapp.database.ObjectBox
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
    override fun convert(context: Context): TreatmentEntity {
        val treatment = TreatmentEntity(
            id,
            posology,
            frequency
        )

        val box = ObjectBox.getInstance(context)
        val store = box.boxFor(TreatmentEntity::class.java)
        store.attach(treatment)

        treatment.medication.target = medication?.convert(context)
        treatment.duration.target = duration?.convert(context)
        return treatment
    }
}
