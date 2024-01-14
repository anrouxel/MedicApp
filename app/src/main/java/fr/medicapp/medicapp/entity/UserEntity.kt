package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entité représentant un utilisateur dans la base de données.
 *
 * @property id L'identifiant unique de l'utilisateur.
 * @property lastName Le nom de famille de l'utilisateur.
 * @property firstName Le prénom de l'utilisateur.
 * @property age L'âge de l'utilisateur.
 * @property email L'email de l'utilisateur.
 */
@Entity
data class UserEntity(
    /**
     * L'identifiant unique de l'utilisateur.
     */
    @PrimaryKey
    val id: String,

    /**
     * Le nom de famille de l'utilisateur.
     */
    var lastName: String,

    /**
     * Le prénom de l'utilisateur.
     */
    var firstName: String,

    /**
     * L'âge de l'utilisateur.
     */
    val age: Int,

    /**
     * L'email de l'utilisateur.
     */
    val email: String,
)
