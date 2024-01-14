package fr.medicapp.medicapp.model

/**
 * Modèle représentant un utilisateur.
 *
 * @property id L'identifiant unique de l'utilisateur.
 * @property lastName Le nom de famille de l'utilisateur.
 * @property firstName Le prénom de l'utilisateur.
 * @property age L'âge de l'utilisateur.
 * @property email L'email de l'utilisateur.
 */
data class User(

    /**
     * L'identifiant unique de l'utilisateur.
     */
    var id: String = "",

    /**
     * Le nom de famille de l'utilisateur.
     */
    val lastName: String,

    /**
     * Le prénom de l'utilisateur.
     */
    val firstName: String,

    /**
     * L'âge de l'utilisateur.
     */
    val age: Int,

    /**
     * L'email de l'utilisateur.
     */
    val email: String,
)
