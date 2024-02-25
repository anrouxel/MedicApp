package fr.medicapp.medicapp.database.repositories

import android.content.Context
import fr.medicapp.medicapp.model.prescription.relationship.Notification
import fr.medicapp.medicapp.model.prescription.relationship.crossRef.NotificationAlarmCrossRef

class NotificationRepository(context: Context) : Repository(context = context) {
    fun getAll(): List<Notification> {
        return db.notificationDAO().getAll()
    }

    fun insert(notifications: List<Notification>): List<Long> {
        notifications.forEach { notification ->
            val alarmIds = AlarmRepository(context).insert(notification.alarms)
            NotificationAlarmCrossRefRepository(context).insert(
                alarmIds.map { alarmId ->
                    NotificationAlarmCrossRef(
                        notificationId = notification.notificationInformation!!.id,
                        alarmId = alarmId
                    )
                }
            )
        }
        return db.notificationDAO().insert(notifications.map { it.notificationInformation!! })
    }

    fun update(notification: Notification) {
        db.notificationDAO().update(notification.notificationInformation!!)
    }
}
