package fr.medicapp.medicapp.database.repositories.prescription

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.database.repositories.prescription.crossRef.NotificationAlarmCrossRefRepository
import fr.medicapp.medicapp.model.prescription.relationship.Notification
import fr.medicapp.medicapp.model.prescription.relationship.crossRef.NotificationAlarmCrossRef

class NotificationRepository(context: Context) : Repository(context = context) {
    fun getAll(): List<Notification> {
        return db.notificationDAO().getAll()
    }

    fun insert(notification: Notification): Long {
        val alarmIds = AlarmRepository(context).insert(notification.alarms)
        val id = db.notificationDAO().insert(notification.notificationInformation)
        NotificationAlarmCrossRefRepository(context).insert(
            alarmIds.map { alarmId ->
                NotificationAlarmCrossRef(
                    notificationId = id,
                    alarmId = alarmId
                )
            }
        )
        return id
    }

    fun insert(notifications: List<Notification>): List<Long> {
        return notifications.map { notification ->
            insert(notification)
        }
    }

    fun update(notification: Notification) {
        db.notificationDAO().update(notification.notificationInformation)
    }

    fun delete(notification: Notification) {
        notification.alarms.forEach { alarm ->
            NotificationAlarmCrossRefRepository(context).delete(
                NotificationAlarmCrossRef(
                    notificationId = notification.notificationInformation.id,
                    alarmId = alarm.id
                )
            )
            AlarmRepository(context).delete(alarm)
        }
        db.notificationDAO().delete(notification.notificationInformation)
    }

    fun delete(notifications: List<Notification>) {
        notifications.forEach { notification ->
            delete(notification)
        }
    }
}
