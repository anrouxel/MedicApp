package fr.medicapp.medicapp.database.repositories

import android.content.Context
import fr.medicapp.medicapp.model.prescription.Alarm
import fr.medicapp.medicapp.model.prescription.Doctor
import fr.medicapp.medicapp.model.prescription.relationship.Prescription

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
}
