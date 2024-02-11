package fr.medicapp.medicapp.model

import fr.medicapp.medicapp.database.converter.EntityToModelMapper
import fr.medicapp.medicapp.database.converter.ModelToEntityMapper
import fr.medicapp.medicapp.database.entity.AlarmEntity

data class Alarm(
    val id: Long = 0L,

    var hour: Int = 0,

    var minute: Int = 0,

    var alarms: Int = 0
) : ModelToEntityMapper<AlarmEntity> {
    override fun toString(): String {
        return "${hour}h${minute}"
    }

    override fun convert(): AlarmEntity {
        return AlarmEntity(
            id,
            hour,
            minute,
            alarms
        )
    }
}
