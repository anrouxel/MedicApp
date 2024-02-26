package fr.medicapp.medicapp.database.repositories.prescription

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.model.prescription.Duration

class DurationRepository(context: Context) : Repository(context = context) {
    fun getAll(): List<Duration> {
        return db.durationDAO().getAll()
    }

    fun insert(duration: Duration): Long {
        return db.durationDAO().insert(duration)
    }

    fun delete(duration: Duration) {
        db.durationDAO().delete(duration)
    }
}
