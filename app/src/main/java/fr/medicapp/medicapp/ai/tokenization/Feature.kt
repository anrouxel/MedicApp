package fr.medicapp.medicapp.tokenization

/**
 * Classe Feature pour représenter une caractéristique d'un texte.
 *
 * @property inputIds Les identifiants d'entrée.
 * @property inputMask Le masque d'entrée.
 * @property segmentIds Les identifiants de segment.
 * @property origTokens Les tokens originaux.
 * @property tokenToOrigMap La carte de token à l'original.
 */
class Feature(
    inputIds: List<Int>,
    inputMask: List<Int>,
    segmentIds: List<Int>,
    origTokens: List<String>,
    tokenToOrigMap: Map<Int, Int>
) {
    /**
     * Les identifiants d'entrée.
     */
    val inputIds: IntArray

    /**
     * Le masque d'entrée.
     */
    val inputMask: IntArray

    /**
     * Les identifiants de segment.
     */
    val segmentIds: IntArray

    /**
     * Les tokens originaux.
     */
    val origTokens: List<String>

    /**
     * La carte de token à l'original.
     */
    val tokenToOrigMap: Map<Int, Int>

    /**
     * Initialise une nouvelle instance de la classe Feature.
     */
    init {
        this.inputIds = inputIds.toIntArray()
        this.inputMask = inputMask.toIntArray()
        this.segmentIds = segmentIds.toIntArray()
        this.origTokens = origTokens
        this.tokenToOrigMap = tokenToOrigMap
    }
}