package fr.medicapp.medicapp.entity

import fr.medicapp.medicapp.model.Duration
import java.time.LocalDate

/**
 * Entité représentant une durée dans l'application.
 *
 * @property startDate La date de début de la durée.
 * @property endDate La date de fin de la durée.
 */
data class DurationEntity(

    /**
     * La date de début de la durée.
     */
    var startDate: LocalDate,

    /**
     * La date de fin de la durée.
     */
    var endDate: LocalDate
) {

    /**
     * Convertit cette entité en un objet Duration.
     *
     * @return Un objet Duration correspondant à cette entité.
     */
    fun toDuration(): Duration {
        return Duration(
            startDate = startDate,
            endDate = endDate
        )
    }
}