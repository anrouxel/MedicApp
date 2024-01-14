package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entité représentant un médecin dans la base de données.
 *
 * @property id L'identifiant unique du médecin.
 * @property lastName Le nom de famille du médecin.
 * @property firstName Le prénom du médecin.
 */
@Entity
data class DoctorEntity(

    /**
     * L'identifiant unique du médecin.
     */
    @PrimaryKey
    val id: String,

    /**
     * Le nom de famille du médecin.
     */
    val lastName: String,

    /**
     * Le prénom du médecin.
     */
    var firstName: String,
)
