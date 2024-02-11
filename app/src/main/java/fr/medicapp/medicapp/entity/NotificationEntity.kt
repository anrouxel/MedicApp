package fr.medicapp.medicapp.entity

import fr.medicapp.medicapp.database.EntityToModelMapper
import fr.medicapp.medicapp.database.MutableListDayOfWeekConverter
import fr.medicapp.medicapp.model.Notification
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany
import java.time.DayOfWeek

@Entity
data class NotificationEntity(
    @Id
    var id: Long = 0L,

    var active: Boolean = false,

    @Convert(converter = MutableListDayOfWeekConverter::class, dbType = String::class)
    var days: MutableList<DayOfWeek> = mutableListOf(),

) : EntityToModelMapper<Notification> {
    lateinit var alarms: ToMany<AlarmEntity>

    override fun convert(): Notification {
        return Notification(
            id,
            active,
            days,
            alarms.map { it.convert() }.toMutableList()
        )
    }
}
