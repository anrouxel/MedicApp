package fr.medicapp.medicapp.database.entity

import fr.medicapp.medicapp.database.converter.EntityToModelMapper
import fr.medicapp.medicapp.model.Alarm
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class AlarmEntity(
    @Id
    var id: Long = 0L,

    var hour: Int = 0,

    var minute: Int = 0,

    var alarms: Int = 0
) : EntityToModelMapper<Alarm> {
    override fun convert(): Alarm {
        return Alarm(
            id,
            hour,
            minute,
            alarms
        )
    }
}
