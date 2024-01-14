package fr.medicapp.medicapp.model

import android.os.Build
import androidx.annotation.RequiresApi
import fr.medicapp.medicapp.entity.DurationEntity
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Modèle représentant une durée.
 *
 * @property startDate La date de début de la durée.
 * @property endDate La date de fin de la durée.
 */
data class Duration(

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
     * Vérifie si la durée est valide.
     *
     * @return `true` si la date de début est avant la date de fin, `false` sinon.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun isValide(): Boolean {
        return startDate.isBefore(endDate)
    }

    /**
     * Renvoie une représentation sous forme de chaîne de caractères de la durée.
     *
     * @return Une chaîne de caractères représentant la durée.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    override fun toString(): String {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return "${startDate.format(formatter)} - ${endDate.format(formatter)}"
    }

    /**
     * Convertit cette durée en une entité DurationEntity.
     *
     * @return Une entité DurationEntity correspondant à cette durée.
     */
    fun toEntity(): DurationEntity {
        return DurationEntity(
            startDate = startDate,
            endDate = endDate
        )
    }
}
