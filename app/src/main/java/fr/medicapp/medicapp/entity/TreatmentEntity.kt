package fr.medicapp.medicapp.entity

import fr.medicapp.medicapp.database.EntityToModelMapper
import fr.medicapp.medicapp.entity.medication.MedicationEntity
import fr.medicapp.medicapp.model.Treatment
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

@Entity
data class TreatmentEntity(
    @Id
    var id: Long = 0L,

    var posology: String = "",

    var quantity: String = "",

    var renew: String = "",

    var notification: Boolean = false
) : EntityToModelMapper<Treatment> {
    lateinit var medication: ToOne<MedicationEntity>

    lateinit var duration: ToOne<DurationEntity>

    override fun convert(): Treatment {
        return Treatment(
            id,
            posology,
            quantity,
            renew,
            notification,
            medication.target.convert(),
            duration.target.convert()
        )
    }
}
