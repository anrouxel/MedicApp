package fr.medicapp.medicapp.model

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import fr.medicapp.medicapp.database.converter.ModelToEntityMapper
import fr.medicapp.medicapp.database.entity.NotificationEntity
import java.time.DayOfWeek

data class Notification(
    val id: Long = 0L,

    var active: Boolean = false,

    var days: MutableList<DayOfWeek> = mutableStateListOf(),

    var alarms: MutableList<Alarm> = mutableStateListOf()
) : ModelToEntityMapper<NotificationEntity> {
    override fun convert(context: Context): NotificationEntity {
        val notification = NotificationEntity(
            id,
            active,
            days
        )
        notification.alarms.addAll(alarms.map { it.convert(context) })
        return notification
    }
}
