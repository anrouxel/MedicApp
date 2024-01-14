package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

/**
 * Entité représentant une prescription dans la base de données.
 *
 * @property id L'identifiant unique de la prescription.
 * @property date La date de la prescription.
 */
@Entity
data class PrescriptionEntity(

    /**
     * L'identifiant unique de la prescription.
     */
    @PrimaryKey
    val id: String,

    /**
     * La date de la prescription.
     */
    var date: LocalDate,
)
