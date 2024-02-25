package fr.medicapp.medicapp.model.prescription.relationship

import androidx.compose.runtime.mutableStateListOf
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import fr.medicapp.medicapp.model.prescription.Alarm
import fr.medicapp.medicapp.model.prescription.NotificationInformation
import fr.medicapp.medicapp.model.prescription.relationship.crossRef.NotificationAlarmCrossRef

data class Notification(
    @Embedded
    val notificationInformation: NotificationInformation = NotificationInformation(),

    @Relation(
        parentColumn = "notification_id",
        entityColumn = "alarm_id",
        associateBy = Junction(NotificationAlarmCrossRef::class)
    )
    var alarms: MutableList<Alarm> = mutableStateListOf()
)
