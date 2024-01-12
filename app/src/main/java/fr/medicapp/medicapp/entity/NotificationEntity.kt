package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import fr.medicapp.medicapp.model.Notification
import java.time.DayOfWeek

@Entity
data class NotificationEntity(
    @PrimaryKey
    val id: String,

    val medicationName: String,
    val frequency: MutableList<DayOfWeek>,
    val hours: MutableList<Int>,
    val minutes: MutableList<Int>
) {
    fun toNotification(): Notification {
        return Notification(
            id = id,
            medicationName = null,
            frequency = frequency,
            hours = hours,
            minutes = minutes
        )
    }
}