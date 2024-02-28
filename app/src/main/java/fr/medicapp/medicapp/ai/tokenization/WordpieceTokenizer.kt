package fr.medicapp.medicapp.tokenization

/**
 * Classe WordpieceTokenizer pour la tokenization du texte en morceaux de mots.
 *
 * @property dic Le dictionnaire pour la conversion des tokens en identifiants.
 * @property MAX_INPUTCHARS_PER_WORD La longueur maximale d'un mot en entrée.
 */
class WordpieceTokenizer(
    vocab: HashMap<String, Int>
) {
    /**
     * Le dictionnaire pour la conversion des tokens en identifiants.
     */
    private var dic: HashMap<String, Int>

    /**
     * La longueur maximale d'un mot en entrée.
     */
    private val MAX_INPUTCHARS_PER_WORD = 200

    /**
     * Initialise une nouvelle instance de la classe WordpieceTokenizer.
     */
    init {
        this.dic = vocab
    }

    /**
     * Tokenize un morceau de texte en ses morceaux de mots. Cela utilise un algorithme glouton de correspondance
     * du plus long d'abord pour effectuer la tokenization en utilisant le vocabulaire donné.
     * Par exemple : entrée = "Doliprane", sortie = ["▁Do", "lip", "ran", "e"].
     *
     * @param text Un seul token ou des tokens séparés par des espaces. Cela devrait déjà avoir été passé par `BasicTokenizer.
     * @return Une liste de tokens de morceaux de mots.
     */
    fun tokenize(text: String): MutableList<String> {
        // Crée une liste mutable pour stocker les tokens de sortie.
        val outputTokens: MutableList<String> = ArrayList()

        // Parcourt chaque token obtenu en divisant le texte en espaces blancs.
        for (token in BasicTokenizer.whitespaceTokenize(text)) {
            // Vérifie si la longueur du token est supérieure à la longueur maximale d'un mot en entrée.
            if (token.length > MAX_INPUTCHARS_PER_WORD) {
                // Si c'est le cas, ajoute le token pour les mots inconnus à la liste des tokens de sortie et passe au token suivant.
                outputTokens.add(CamemBERT.UNK_TOKEN)
                continue
            }

            // Marque si un mot ne peut pas être tokenisé en sous-mots connus.
            var isBad = false

            // Initialise les indices de début et de fin pour la sous-chaîne à extraire du token.
            var start = 0
            var end = token.length

            // Crée une liste mutable pour stocker les sous-tokens.
            val subTokens: MutableList<String> = ArrayList()

            // Continue tant que l'indice de fin est supérieur à 0.
            while (0 < end) {
                var curSubStr = ""

                // Les sous-chaînes plus longues correspondent en premier.
                while (start < end) {
                    val subStr =
                        if (start == 0) {
                            // Si l'indice de début est 0, ajoute un préfixe (espace insécable) à la sous-chaîne.
                            "▁${token.substring(start, end)}"
                        } else {
                            // Sinon, extrait la sous-chaîne du token.
                            token.substring(
                                start,
                                end
                            )
                        }

                    // Vérifie si la sous-chaîne existe dans le dictionnaire.
                    curSubStr = dic[subStr]?.let { subStr } ?: curSubStr

                    // Si la sous-chaîne existe dans le dictionnaire, sort de la boucle.
                    if (curSubStr != "") {
                        break
                    }

                    // Incrémente l'indice de début.
                    start++
                }

                // Si la sous-chaîne n'existe pas dans le dictionnaire, marque le mot comme mauvais et sort de la boucle.
                if ("" == curSubStr) {
                    isBad = true
                    break
                }

                // Ajoute la sous-chaîne à la liste des sous-tokens.
                subTokens.add(0, curSubStr)

                // Poursuit la tokenisation de la chaîne résidente.
                end = start
                start = 0
            }

            if (isBad) {
                // Si le mot est marqué comme mauvais, ajoute le token pour les mots inconnus à la liste des tokens de sortie.
                outputTokens.add(CamemBERT.UNK_TOKEN)
            } else {
                // Sinon, ajoute tous les sous-tokens à la liste des tokens de sortie.
                outputTokens.addAll(subTokens)
            }
        }

        // Retourne la liste des tokens de sortie.
        return outputTokens
    }
}
