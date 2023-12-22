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
    val frequencies: List<Frequency>,
    val duration: Duration,
    var history: List<Date>,
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

    fun lastInTake(): Date {
        return history.last()
    }

    fun nextInTake(): Date? {
        val now = Calendar.getInstance()
        for (frequency in frequencies) {
            val nextInTake = Calendar.getInstance()
            nextInTake.set(Calendar.HOUR_OF_DAY, frequency.hour)
            nextInTake.set(Calendar.DAY_OF_WEEK, frequency.day) // +1 because Calendar.DAY_OF_WEEK starts from 1 (Sunday) to 7 (Saturday)
            if (nextInTake.before(now)) {
                nextInTake.add(Calendar.WEEK_OF_YEAR, 1)
            }
            if (nextInTake.time.before(duration.endDate)) {
                if (history.isEmpty() || nextInTake.time.after(history.last())) {
                    return nextInTake.time
                } else {
                    // Check if the user forgot to take the medication and it's not too late to take it
                    val lastInTake = Calendar.getInstance()
                    lastInTake.time = history.last()
                    lastInTake.add(Calendar.HOUR, 1) // Assuming the user has 1 hour to take the medication after the scheduled time
                    if (lastInTake.after(now)) {
                        return nextInTake.time
                    }
                }
            }
        }
        return null
    }

    fun getRemainingInTake(): Int {
        val totalDays = ((duration.endDate.time - duration.startDate.time) / (1000 * 60 * 60 * 24)).toInt()
        val totalIntakes = totalDays * frequencies.size
        val takenIntakes = history.size
        return totalIntakes - takenIntakes
    }

    fun getHistoryByDay(): List<Pair<Date, Int>> {
        /* TODO: Implémenter cette fonction. */
    }
}
