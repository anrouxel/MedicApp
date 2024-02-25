package fr.medicapp.medicapp.database.repositories.prescription

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.model.prescription.Alarm

class AlarmRepository(context: Context) : Repository(context = context) {
    fun getAll(): List<Alarm> {
        return db.alarmDAO().getAll()
    }

    fun insert(alarm: Alarm): Long {
        return db.alarmDAO().insert(alarm)
    }

    fun insert(alarms: List<Alarm>): List<Long> {
        return db.alarmDAO().insert(alarms)
    }

    fun delete(alarm: Alarm) {
        db.alarmDAO().delete(alarm)
    }
}
