package fr.medicapp.medicapp.entity

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 * Entité représentant un médecin dans la base de données.
 *
 * @property id L'identifiant unique du médecin.
 * @property name Le nom du médecin.
 */
@Entity
data class DoctorEntity(

    /**
     * L'identifiant unique du médecin.
     */
    @Id
    var id: Long = 0L,

    /**
     * Le numéro RPPS du médecin.
     */
    var rpps: Long = 0L,

    /**
     * Le nom de famille du médecin.
     */
    var name: String = "",
)
