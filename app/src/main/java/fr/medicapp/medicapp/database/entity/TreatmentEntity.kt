package fr.medicapp.medicapp.database.entity

import fr.medicapp.medicapp.database.converter.EntityToModelMapper
import fr.medicapp.medicapp.database.entity.medication.MedicationEntity
import fr.medicapp.medicapp.model.Treatment
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

@Entity
data class TreatmentEntity(
    @Id
    var id: Long = 0L,

    var posology: String = "",

    var frequency: String = "",
) : EntityToModelMapper<Treatment> {
    lateinit var medication: ToOne<MedicationEntity>

    lateinit var duration: ToOne<DurationEntity>

    override fun convert(): Treatment {
        return Treatment(
            id,
            posology,
            frequency,
            medication.target?.convert(),
            duration.target?.convert()
        )
    }
}
