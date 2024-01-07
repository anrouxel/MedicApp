package fr.medicapp.medicapp.entity

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
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
@RequiresApi(Build.VERSION_CODES.O)
data class Treatment(
    var medication: Medication? = null,
    var dosage: Int? = null,
    val frequencies: MutableList<Frequency> = mutableListOf(),
    val duration: Duration? = null,
    var notification: Boolean = false,
    var history: MutableList<Date> = mutableListOf(),
) {
    fun getStartDate(): LocalDate {
        return duration!!.startDate
    }

    fun getEndDate(): LocalDate {
        return duration!!.endDate
    }

    fun getRemainingDays(): Long {
        return LocalDate.now().until(duration!!.endDate).days.toLong()
    }

    fun isOver(): Boolean {
        return LocalDate.now().isAfter(duration!!.endDate)
    }

    fun lastTake(): Date {
        return history.max()
    }

    fun nextInTake(): Date? {
        /* TODO: Implémenter cette fonction. */
        return null
    }

    fun getHistoryByDay(): List<Pair<Date, Int>> {
        /* TODO: Implémenter cette fonction. */
        return emptyList()
    }
}
