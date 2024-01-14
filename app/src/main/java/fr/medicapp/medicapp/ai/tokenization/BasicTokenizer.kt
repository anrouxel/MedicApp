package fr.medicapp.medicapp.tokenization

/**
 * Classe BasicTokenizer pour la tokenization de base du texte.
 *
 * @property doLowerCase Indique si les tokens doivent être convertis en minuscules.
 */
class BasicTokenizer(
    doLowerCase: Boolean = false
) {
    /**
     * Indique si les tokens doivent être convertis en minuscules.
     */
    private val doLowerCase: Boolean

    /**
     * Initialise la propriété doLowerCase.
     *
     * @property doLowerCase Indique si les tokens doivent être convertis en minuscules.
     */
    init {
        this.doLowerCase = doLowerCase
    }

    /**
     * Tokenize le texte donné.
     *
     * @param text Le texte à tokenizer.
     * @return Une liste de tokens.
     */
    fun tokenize(text: String): MutableList<String> {
        // Nettoie le texte en supprimant les caractères invalides et en nettoyant les espaces blancs.
        val cleanedText = cleanText(text)

        // Divise le texte nettoyé en tokens en utilisant les espaces blancs comme séparateurs.
        val origTokens = whitespaceTokenize(cleanedText)

        // Crée un StringBuilder pour construire la chaîne de tokens.
        val stringBuilder = StringBuilder()

        // Parcourt chaque token.
        for (token in origTokens) {
            // Crée une copie temporaire du token.
            var tokenTemp = token

            // Si doLowerCase est vrai, convertit le token en minuscules.
            if (doLowerCase) {
                tokenTemp = tokenTemp.lowercase()
            }

            // Divise le token sur la ponctuation.
            val list = runSplitOnPunc(tokenTemp)

            // Parcourt chaque sous-token.
            for (subToken in list) {
                // Ajoute le sous-token au StringBuilder, suivi d'un espace.
                stringBuilder.append(subToken).append(" ")
            }
        }

        // Divise le StringBuilder en tokens en utilisant les espaces blancs comme séparateurs.
        val splitTokens = whitespaceTokenize(stringBuilder.toString())

        // Retourne la liste des tokens.
        return splitTokens
    }

    companion object {
        /**
         * Effectue la suppression des caractères invalides et le nettoyage des espaces blancs sur le texte.
         *
         * @param text Le texte à nettoyer.
         * @return Le texte nettoyé.
         */
        fun cleanText(text: String): String {
            // Crée un StringBuilder pour construire la chaîne de texte nettoyée.
            val stringBuilder = StringBuilder("")

            // Parcourt chaque caractère du texte.
            for (element in text) {
                val ch = element

                // Vérifie si le caractère est invalide ou un caractère de contrôle.
                if (CharChecker.isInvalid(ch) || CharChecker.isControl(ch)) {
                    // Si le caractère est invalide ou un caractère de contrôle, passe au caractère suivant.
                    continue
                }

                // Vérifie si le caractère est un espace blanc.
                if (CharChecker.isWhitespace(ch)) {
                    // Si le caractère est un espace blanc, ajoute un espace au StringBuilder.
                    stringBuilder.append(" ")
                } else {
                    // Si le caractère n'est pas un espace blanc, ajoute le caractère au StringBuilder.
                    stringBuilder.append(ch)
                }
            }

            // Retourne la chaîne de texte nettoyée.
            return stringBuilder.toString()
        }

        /**
         * Effectue un nettoyage et une division de base des espaces blancs sur un morceau de texte.
         *
         * @param text Le texte à diviser.
         * @return Une liste de tokens.
         */
        fun whitespaceTokenize(text: String): MutableList<String> {

            // Divise le texte en tokens en utilisant les espaces blancs comme séparateurs.
            return mutableListOf(
                // Supprime les tokens vides à la fin de la liste.
                *text.split(" ".toRegex()).dropLastWhile { it.isEmpty() }
                    // Convertit la liste de tokens en MutableList.
                    .toTypedArray())
        }

        /**
         * Divise la ponctuation sur un morceau de texte.
         *
         * @param text Le texte à diviser.
         * @return Une liste de tokens.
         */
        fun runSplitOnPunc(text: String): MutableList<String> {
            // Crée une liste mutable pour stocker les tokens.
            val tokens: MutableList<String> = ArrayList()

            // Indicateur pour commencer un nouveau mot.
            var startNewWord = true

            // Parcourt chaque caractère du texte.
            for (element in text) {
                val ch = element

                // Vérifie si le caractère est une ponctuation.
                if (CharChecker.isPunctuation(ch)) {
                    // Si le caractère est une ponctuation, ajoute le caractère à la liste des tokens et indique de commencer un nouveau mot.
                    tokens.add(ch.toString())
                    startNewWord = true
                } else {
                    // Si le caractère n'est pas une ponctuation, vérifie si un nouveau mot doit être commencé.
                    if (startNewWord) {
                        // Si un nouveau mot doit être commencé, ajoute un nouveau token vide à la liste des tokens et indique de ne pas commencer un nouveau mot.
                        tokens.add("")
                        startNewWord = false
                    }

                    // Ajoute le caractère au dernier token de la liste.
                    tokens[tokens.size - 1] = tokens.last() + ch
                }
            }

            // Retourne la liste des tokens.
            return tokens
        }
    }
}