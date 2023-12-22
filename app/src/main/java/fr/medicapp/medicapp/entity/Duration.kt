package fr.medicapp.medicapp.entity

import java.util.Date

/**
 * Représente une durée avec une date de début et de fin.
 *
 * @property startDate La date de début de la durée.
 * @property endDate La date de fin de la durée.
 */
data class Duration(
    val startDate: Date,
    val endDate: Date
) {
    init {
        require(startDate.before(endDate)) { "La date de début doit être avant la date de fin" }
        require(endDate.after(startDate)) { "La date de fin doit être après la date de début" }
    }
}