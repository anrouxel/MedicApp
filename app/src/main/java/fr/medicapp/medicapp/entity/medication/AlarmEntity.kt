package fr.medicapp.medicapp.entity.medication

import fr.medicapp.medicapp.database.EntityToModelMapper
import fr.medicapp.medicapp.database.MutableListIntConverter
import fr.medicapp.medicapp.model.Alarm
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class AlarmEntity(
    @Id
    var id: Long = 0L,

    var hours: Int = 0,

    var minutes: Int = 0,

    var alarms: Int = 0
) : EntityToModelMapper<Alarm> {
    override fun convert(): Alarm {
        return Alarm(
            id,
            hours,
            minutes,
            alarms
        )
    }
}
