package fr.medicapp.medicapp.tokenization

import java.util.Collections

/**
 * Classe FeatureConverter pour convertir les caractéristiques d'un texte.
 *
 * @property tokenizer Le tokenizer pour la tokenization du texte.
 * @property maxQueryLen La longueur maximale de la requête.
 * @property maxSeqLen La longueur maximale de la séquence.
 */
class FeatureConverter(
    inputDic: HashMap<String, Int>,
    doLowerCase: Boolean,
    maxQueryLen: Int,
    maxSeqLen: Int
) {
    /**
     * Le tokenizer pour la tokenization du texte.
     */
    private val tokenizer: FullTokenizer

    /**
     * La longueur maximale de la requête.
     */
    private val maxQueryLen: Int

    /**
     * La longueur maximale de la séquence.
     */
    private val maxSeqLen: Int

    /**
     * Initialise une nouvelle instance de la classe FeatureConverter.
     */
    init {
        tokenizer = FullTokenizer(inputDic, doLowerCase)
        this.maxQueryLen = maxQueryLen
        this.maxSeqLen = maxSeqLen
    }

    /**
     * Convertit le contexte en une caractéristique.
     *
     * @param context Le contexte à convertir.
     * @return Une caractéristique.
     */
    fun convert(context: String): Feature {
        // Crée une liste mutable pour stocker les tokens originaux.
        val origTokens = mutableListOf(
            // Supprime les espaces en début et en fin de la chaîne de caractères.
            *context.trim { it <= ' ' }
                // Divise la chaîne de caractères en plusieurs tokens en utilisant les espaces comme séparateurs.
                .split("\\s+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        )

        // Crée une liste mutable pour stocker les indices des tokens originaux.
        val tokenToOrigIndex: MutableList<Int> = ArrayList()

        // Crée une liste mutable pour stocker tous les tokens du document.
        var allDocTokens: MutableList<String> = ArrayList()

        // Parcourt chaque token original.
        for (i in origTokens.indices) {
            val token = origTokens[i]

            // Tokenize le token original en sous-tokens.
            val subTokens = tokenizer.tokenize(token)

            // Parcourt chaque sous-token.
            for (subToken in subTokens) {
                // Ajoute l'indice du token original à la liste des indices.
                tokenToOrigIndex.add(i)

                // Ajoute le sous-token à la liste de tous les tokens du document.
                allDocTokens.add(subToken)
            }
        }

        // -3 est pour prendre en compte les tokens [CLS], [SEP] et [SEP].
        val maxContextLen = maxSeqLen - 3

        // Si la taille de tous les tokens du document dépasse la longueur maximale du contexte, on ne garde que les premiers tokens.
        if (allDocTokens.size > maxContextLen) {
            allDocTokens = allDocTokens.subList(0, maxContextLen)
        }

        // Crée une liste mutable pour stocker les tokens.
        val tokens: MutableList<String> = ArrayList()

        // Crée une liste mutable pour stocker les identifiants de segment.
        val segmentIds: MutableList<Int> = ArrayList()

        // Crée une map pour associer chaque indice de token à son indice original.
        val tokenToOrigMap: MutableMap<Int, Int> = HashMap()

        // Ajoute le token [CLS] au début de la liste des tokens.
        tokens.add(CamemBERT.CLS_TOKEN)

        // Ajoute l'identifiant de segment 0 au début de la liste des identifiants de segment.
        segmentIds.add(0)

        // Pour l'entrée de texte.
        for (i in allDocTokens.indices) {
            val docToken = allDocTokens[i]

            // Ajoute le token du document à la liste des tokens.
            tokens.add(docToken)

            // Ajoute l'identifiant de segment 0 à la liste des identifiants de segment.
            segmentIds.add(0)

            // Associe l'indice du dernier token ajouté à la liste des tokens à son indice original.
            tokenToOrigMap[tokens.size - 1] = tokenToOrigIndex[i]
        }

        // Ajoute le token [SEP] à la fin de la liste des tokens.
        tokens.add(CamemBERT.SEP_TOKEN)

        // Ajoute l'identifiant de segment 0 à la fin de la liste des identifiants de segment.
        segmentIds.add(0)

        // Convertit les tokens en identifiants d'entrée.
        val inputIds = tokenizer.convertTokensToIds(tokens)

        // Crée une liste mutable pour stocker le masque d'entrée.
        val inputMask: MutableList<Int> = ArrayList(
            // Remplit la liste avec des 1 de la même taille que la liste des identifiants d'entrée.
            Collections.nCopies(
                inputIds.size,
                1
            )
        )

        // Tant que la taille de la liste des tokens est inférieure à la longueur maximale de la séquence.
        while (tokens.size < maxSeqLen) {
            // Ajoute le token [PAD] à la fin de la liste des tokens.
            tokens.add(CamemBERT.PAD_TOKEN)

            // Ajoute l'identifiant 1 à la fin de la liste des identifiants d'entrée.
            inputIds.add(1)

            // Ajoute l'identifiant de segment 0 à la fin de la liste des identifiants de segment.
            segmentIds.add(0)

            // Ajoute le masque 0 à la fin de la liste des masques d'entrée.
            inputMask.add(0)
        }

        // Retourne une nouvelle instance de la classe Feature avec les identifiants d'entrée, le masque d'entrée, les identifiants de segment, les tokens originaux et la map des tokens à leurs indices originaux.
        return Feature(inputIds, inputMask, segmentIds, origTokens, tokenToOrigMap)
    }

    companion object {
        /**
         * Aligne les identifiants de mots.
         *
         * @param feature La caractéristique à aligner.
         * @param labelAllTokens Indique si tous les tokens doivent être étiquetés.
         * @return Une liste d'identifiants de mots alignés.
         */
        fun align_word_ids(feature: Feature, labelAllTokens: Boolean = false): MutableList<Int> {
            // Récupère les identifiants d'entrée de la caractéristique.
            val inputIds = feature.inputIds

            // Récupère la carte de token à l'original de la caractéristique.
            val tokenToOrigMap = feature.tokenToOrigMap

            // Initialise l'indice du mot précédent à null.
            var previousWordIdx: Int? = null

            // Crée une liste mutable pour stocker les identifiants de label.
            val labelIds: MutableList<Int> = mutableListOf()

            // Parcourt chaque identifiant d'entrée avec son indice.
            for ((index, _) in inputIds.withIndex()) {
                // Récupère l'indice du mot correspondant à l'indice de token.
                val wordIdx = tokenToOrigMap[index]

                // Vérifie si l'indice du mot est null.
                if (tokenToOrigMap[index] == null) {
                    // Si l'indice du mot est null, ajoute -100 à la liste des identifiants de label.
                    labelIds.add(-100)
                } else if (wordIdx != previousWordIdx) {
                    // Si l'indice du mot est différent de l'indice du mot précédent, tente d'ajouter 1 à la liste des identifiants de label.
                    try {
                        labelIds.add(1)
                    } catch (e: Exception) {
                        // Si une exception est levée, ajoute -100 à la liste des identifiants de label.
                        labelIds.add(-100)
                    }
                } else {
                    // Si l'indice du mot est le même que l'indice du mot précédent, tente d'ajouter 1 ou -100 à la liste des identifiants de label en fonction de la valeur de labelAllTokens.
                    try {
                        labelIds.add(if (labelAllTokens) 1 else -100)
                    } catch (e: Exception) {
                        // Si une exception est levée, ajoute -100 à la liste des identifiants de label.
                        labelIds.add(-100)
                    }
                }

                // Met à jour l'indice du mot précédent avec l'indice du mot actuel.
                previousWordIdx = wordIdx
            }

            // Retourne la liste des identifiants de label.
            return labelIds
        }
    }
}
