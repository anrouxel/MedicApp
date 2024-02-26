package fr.medicapp.medicapp.model.prescription

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalTime

@Entity
data class Alarm(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "alarm_id")
    val id: Long = 0L,

    val time: LocalTime = LocalTime.now(),

    @ColumnInfo(name = "notification_id")
    val notificationId: Long = 0L
) {
    override fun toString(): String {
        return time.toString()
    }
}
