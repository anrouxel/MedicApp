package fr.medicapp.medicapp.tokenization

/**
 * Objet CamemBERT qui contient les tokens spéciaux utilisés dans le modèle de tokenization CamemBERT.
 */
object CamemBERT {
    /**
     * Token de début de séquence.
     */
    val BOS_TOKEN: String = "<s>"

    /**
     * Token de fin de séquence.
     */
    val EOS_TOKEN: String = "</s>"

    /**
     * Token de séparation.
     */
    val SEP_TOKEN: String = "</s>"

    /**
     * Token de classification.
     */
    val CLS_TOKEN: String = "<s>"

    /**
     * Token pour les mots inconnus.
     */
    val UNK_TOKEN: String = "<unk>"

    /**
     * Token de padding.
     */
    val PAD_TOKEN: String = "<pad>"

    /**
     * Token de masquage.
     */
    val MASK_TOKEN: String = "<mask>"
}