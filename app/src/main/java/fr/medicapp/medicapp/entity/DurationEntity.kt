package fr.medicapp.medicapp.entity

import fr.medicapp.medicapp.database.LocalDateConverter
import fr.medicapp.medicapp.model.Duration
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.time.LocalDate

/**
 * Entité représentant une durée dans l'application.
 *
 * @property startDate La date de début de la durée.
 * @property endDate La date de fin de la durée.
 */
@Entity
data class DurationEntity(

    @Id
    var id: Long = 0L,

    /**
     * La date de début de la durée.
     */
    @Convert(converter = LocalDateConverter::class, dbType = String::class)
    var startDate: LocalDate? = null,

    /**
     * La date de fin de la durée.
     */
    @Convert(converter = LocalDateConverter::class, dbType = String::class)
    var endDate: LocalDate? = null
) {
    override fun toString(): String {
        return "$startDate - $endDate"
    }
}