package fr.medicapp.medicapp.database.entity

import fr.medicapp.medicapp.database.EntityToModelMapper
import fr.medicapp.medicapp.database.LocalDateConverter
import fr.medicapp.medicapp.model.Notification
import fr.medicapp.medicapp.model.Prescription
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany
import io.objectbox.relation.ToOne
import java.time.LocalDate

@Entity
data class PrescriptionEntity(
    @Id
    var id: Long = 0L,

    @Convert(converter = LocalDateConverter::class, dbType = String::class)
    var date: LocalDate? = null,
) : EntityToModelMapper<Prescription> {
    lateinit var doctor: ToOne<DoctorEntity>

    lateinit var treatment: ToOne<TreatmentEntity>

    lateinit var notifications : ToMany<NotificationEntity>

    override fun convert(): Prescription {
        return Prescription(
            id,
            date,
            doctor.target.convert(),
            treatment.target.convert(),
            notifications.map { it.convert() }.toMutableList()
        )
    }
}
