package fr.medicapp.medicapp.model

/**
 * Modèle représentant une option de dialogue.
 *
 * @property id L'identifiant unique de l'option de dialogue.
 * @property title Le titre de l'option de dialogue.
 * @property description La description de l'option de dialogue.
 */
data class OptionDialog(

    /**
     * L'identifiant unique de l'option de dialogue.
     */
    val id: String,

    /**
     * Le titre de l'option de dialogue.
     */
    val title: String,

    /**
     * La description de l'option de dialogue.
     */
    val description: String? = null
)
