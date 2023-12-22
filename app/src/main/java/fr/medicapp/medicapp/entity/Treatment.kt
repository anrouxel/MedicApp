package fr.medicapp.medicapp.entity

import java.util.Calendar
import java.util.Date

/**
 * Classe de données représentant un traitement.
 *
 * @property medication Le médicament associé au traitement.
 * @property frequencies La liste des fréquences du traitement.
 * @property duration La durée du traitement.
 * @property history La liste des dates de prise du traitement.
 */
data class Treatment(
    val medication: Medication,
    val frequencies: List<Frequency> = emptyList(),
    val duration: Duration,
    var history: List<Date> = emptyList(),
) {
    fun getStartDate(): Date {
        return duration.startDate
    }

    fun getEndDate(): Date {
        return duration.endDate
    }

    fun getRemainingDays(): Long {
        return (duration.endDate.time - Date().time) / (1000 * 60 * 60 * 24)
    }

    fun isOver(): Boolean {
        return Date().after(duration.endDate)
    }

    fun lastTake(): Date {
        return history.max()
    }

    fun nextInTake(): Date? {
        val now = Date()
        if (now.after(duration.endDate)) return null
        val nextTake = frequencies.map { frequency ->
            val calendar = Calendar.getInstance()
            calendar.time = now
            calendar.set(Calendar.HOUR_OF_DAY, frequency.hour)
            calendar.set(Calendar.DAY_OF_WEEK, frequency.day)
            calendar.time
        }.filter { it.after(now) }.min()
        return nextTake
    }

    fun getRemainingInTake(): Int {
        val totalDays = ((duration.endDate.time - duration.startDate.time) / (1000 * 60 * 60 * 24)).toInt()
        val totalIntakes = totalDays * frequencies.size
        val takenIntakes = history.size
        return totalIntakes - takenIntakes
    }

    fun getHistoryByDay(): List<Pair<Date, Int>> {
        /* TODO: Implémenter cette fonction. */
        return emptyList()
    }
}
