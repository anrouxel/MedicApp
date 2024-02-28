package fr.medicapp.medicapp.model.prescription.relationship.crossRef

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["notification_id", "alarm_id"])
data class NotificationAlarmCrossRef(
    @ColumnInfo(name = "notification_id")
    val notificationId: Long = 0L,

    @ColumnInfo(name = "alarm_id")
    val alarmId: Long = 0L
)
