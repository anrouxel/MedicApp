package fr.medicapp.medicapp.entity

import fr.medicapp.medicapp.database.EntityToModelMapper
import fr.medicapp.medicapp.database.MutableListDayOfWeekConverter
import fr.medicapp.medicapp.database.MutableListIntConverter
import fr.medicapp.medicapp.model.Notification
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne
import java.time.DayOfWeek

@Entity
data class NotificationEntity(
    @Id
    var id: Long = 0L,

    @Convert(converter = MutableListDayOfWeekConverter::class, dbType = String::class)
    var frequency: MutableList<DayOfWeek> = mutableListOf(),

    @Convert(converter = MutableListIntConverter::class, dbType = String::class)
    var hours: MutableList<Int> = mutableListOf(),

    @Convert(converter = MutableListIntConverter::class, dbType = String::class)
    var minutes: MutableList<Int> = mutableListOf(),

    @Convert(converter = MutableListIntConverter::class, dbType = String::class)
    var alarms: MutableList<Int> = mutableListOf(),
) : EntityToModelMapper<Notification>{
    lateinit var treatment: ToOne<TreatmentEntity>

    override fun convert(): Notification {
        return Notification(
            id,
            frequency,
            hours,
            minutes,
            alarms,
            treatment.target.convert()
        )
    }
}