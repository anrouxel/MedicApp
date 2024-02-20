package fr.medicapp.medicapp.database.entity

import androidx.compose.runtime.toMutableStateList
import fr.medicapp.medicapp.database.converter.EntityToModelMapper
import fr.medicapp.medicapp.database.converter.LocalDateConverter
import fr.medicapp.medicapp.database.converter.MutableListLocalDateTimeConverter
import fr.medicapp.medicapp.model.Prescription
import io.objectbox.annotation.Backlink
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany
import io.objectbox.relation.ToOne
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
data class PrescriptionEntity(
    @Id
    var id: Long = 0L,

    @Convert(converter = LocalDateConverter::class, dbType = String::class)
    var date: LocalDate? = null,

    @Convert(converter = MutableListLocalDateTimeConverter::class, dbType = String::class)
    var takes: MutableList<LocalDateTime> = mutableListOf()

) : EntityToModelMapper<Prescription> {
    lateinit var doctor: ToOne<DoctorEntity>

    lateinit var treatment: ToOne<TreatmentEntity>

    lateinit var notifications: ToMany<NotificationEntity>

    @Backlink(to = "prescription")
    lateinit var sideEffects: ToMany<SideEffectEntity>

    override fun convert(): Prescription {
        return Prescription(
            id,
            date,
            takes,
            doctor.target?.convert(),
            treatment.target.convert(),
            notifications.map { it.convert() }.toMutableStateList(),
            sideEffects.map { it.convertBacklink() }.toMutableStateList()
        )
    }

    fun convertBacklink(): Prescription {
        return Prescription(
            id,
            date,
            takes,
            doctor.target?.convert(),
            treatment.target.convert(),
            notifications.map { it.convert() }.toMutableStateList(),
        )
    }
}
