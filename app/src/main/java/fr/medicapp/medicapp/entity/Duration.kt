package fr.medicapp.medicapp.entity

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import java.time.LocalDate
import java.util.Date

/**
 * Représente une durée avec une date de début et de fin.
 *
 * @property startDate La date de début de la durée.
 * @property endDate La date de fin de la durée.
 */
@Entity
@RequiresApi(Build.VERSION_CODES.O)
data class Duration(
    var startDate: LocalDate,
    var endDate: LocalDate
) {
    init {
        require(startDate.isBefore(endDate) || startDate == endDate) { "La date de début doit être avant la date de fin" }
        require(endDate.isAfter(startDate) || endDate == startDate) { "La date de fin doit être après la date de début" }
    }

    override fun toString(): String {
        return "Du $startDate au $endDate"
    }
}