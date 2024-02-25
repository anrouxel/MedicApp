package fr.medicapp.medicapp.database.repositories

import android.content.Context
import fr.medicapp.medicapp.model.prescription.Alarm
import fr.medicapp.medicapp.model.prescription.Doctor
import fr.medicapp.medicapp.model.prescription.relationship.Prescription
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
}
