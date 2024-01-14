package fr.medicapp.medicapp.tokenization

/**
 * Classe FullTokenizer pour la tokenization complète du texte.
 *
 * @property basicTokenizer Le tokenizer de base pour la tokenization de base du texte.
 * @property wordpieceTokenizer Le tokenizer de morceau de mot pour la tokenization du texte en morceaux de mots.
 * @property dic Le dictionnaire pour la conversion des tokens en identifiants.
 */
class FullTokenizer(
    inputDic: HashMap<String, Int>,
    doLowerCase: Boolean
) {
    /**
     * Le tokenizer de base pour la tokenization de base du texte.
     */
    private val basicTokenizer: BasicTokenizer

    /**
     * Le tokenizer de morceau de mot pour la tokenization du texte en morceaux de mots.
     */
    private val wordpieceTokenizer: WordpieceTokenizer

    /**
     * Le dictionnaire pour la conversion des tokens en identifiants.
     */
    private val dic: HashMap<String, Int>

    /**
     * Initialise une nouvelle instance de la classe FullTokenizer.
     */
    init {
        this.dic = inputDic
        this.basicTokenizer = BasicTokenizer(doLowerCase)
        this.wordpieceTokenizer = WordpieceTokenizer(inputDic)
    }

    /**
     * Tokenize le texte en une liste de tokens.
     *
     * @param text Le texte à tokenizer.
     * @return Une liste de tokens.
     */
    fun tokenize(text: String): MutableList<String> {
        // Crée une liste mutable pour stocker les tokens divisés.
        val splitTokens: MutableList<String> = ArrayList()

        // Parcourt chaque token obtenu en tokenisant le texte avec le tokenizer de base.
        for (token in basicTokenizer.tokenize(text)) {
            // Ajoute tous les sous-tokens obtenus en tokenisant le token avec le tokenizer de morceau de mot à la liste des tokens divisés.
            splitTokens.addAll(wordpieceTokenizer.tokenize(token))
        }

        // Retourne la liste des tokens divisés.
        return splitTokens
    }

    /**
     * Convertit une liste de tokens en une liste d'identifiants.
     *
     * @param tokens La liste de tokens à convertir.
     * @return Une liste d'identifiants.
     */
    fun convertTokensToIds(tokens: MutableList<String>): MutableList<Int> {
        // Crée une liste mutable pour stocker les identifiants de sortie.
        val outputIds: MutableList<Int> = ArrayList()

        // Parcourt chaque token dans la liste des tokens.
        for (token in tokens) {
            // Ajoute l'identifiant correspondant au token dans le dictionnaire à la liste des identifiants de sortie.
            outputIds.add(dic[token]!!)
        }

        // Retourne la liste des identifiants de sortie.
        return outputIds
    }
}