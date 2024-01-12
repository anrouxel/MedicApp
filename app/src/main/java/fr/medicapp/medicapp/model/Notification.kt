package fr.medicapp.medicapp.model

import androidx.compose.runtime.mutableStateListOf
import fr.medicapp.medicapp.entity.NotificationEntity
import java.time.DayOfWeek
import java.util.UUID

data class Notification(
    var id : String = "",
    var medicationName : Treatment? = null,
    var frequency : MutableList<DayOfWeek> = mutableStateListOf(),
    var hours : MutableList<Int> = mutableStateListOf(),
    var minutes : MutableList<Int> = mutableStateListOf(),
    var alarms: MutableList<Int> = mutableStateListOf(),
) {
    fun toEntity(): NotificationEntity {
        return NotificationEntity(
            id = if (id.isEmpty()) UUID.randomUUID().toString() else id,
            medicationName = medicationName!!.id,
            frequency = frequency.toMutableList(),
            hours = hours.toMutableList(),
            minutes = minutes.toMutableList(),
            alarms = alarms.toMutableList()
        )
    }
}