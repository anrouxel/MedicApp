package fr.medicapp.medicapp.database.repositories.prescription.crossRef

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.model.prescription.relationship.crossRef.NotificationAlarmCrossRef

class NotificationAlarmCrossRefRepository(context: Context) : Repository(context = context) {
    fun getAll(): List<NotificationAlarmCrossRef> {
        return db.notificationAlarmCrossRefDAO().getAll()
    }

    fun insert(notificationAlarmCrossRef: NotificationAlarmCrossRef): Long {
        return db.notificationAlarmCrossRefDAO().insert(notificationAlarmCrossRef)
    }

    fun insert(notificationAlarmCrossRefs: List<NotificationAlarmCrossRef>): List<Long> {
        return db.notificationAlarmCrossRefDAO().insert(notificationAlarmCrossRefs)
    }

    fun delete(notificationAlarmCrossRef: NotificationAlarmCrossRef) {
        db.notificationAlarmCrossRefDAO().delete(notificationAlarmCrossRef)
    }
}
