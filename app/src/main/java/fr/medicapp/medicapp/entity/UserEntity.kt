package fr.medicapp.medicapp.entity

/**
 * Entité représentant un utilisateur dans la base de données.
 *
 * @property id L'identifiant unique de l'utilisateur.
 * @property lastName Le nom de famille de l'utilisateur.
 * @property firstName Le prénom de l'utilisateur.
 * @property age L'âge de l'utilisateur.
 * @property email L'email de l'utilisateur.
 */
data class UserEntity(
    /**
     * L'identifiant unique de l'utilisateur.
     */
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
