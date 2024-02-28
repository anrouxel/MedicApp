package fr.medicapp.medicapp.tokenization

/**
 * Classe CharChecker pour vérifier les caractères spécifiques.
 */
class CharChecker {
    companion object {
        /**
         * Pour juger si c'est un caractère vide ou inconnu.
         *
         * @param ch Le caractère à vérifier.
         * @return Vrai si le caractère est invalide, faux sinon.
         */
        fun isInvalid(ch: Char): Boolean {
            return ch.code == 0 || ch.code == 0xfffd
        }

        /**
         * Pour juger si c'est un caractère de contrôle (exclut l'espace blanc).
         *
         * @param ch Le caractère à vérifier.
         * @return Vrai si le caractère est un caractère de contrôle, faux sinon.
         */
        fun isControl(ch: Char): Boolean {
            if (Character.isWhitespace(ch)) {
                return false
            }
            val type = Character.getType(ch)
            return type == Character.CONTROL.toInt() || type == Character.FORMAT.toInt()
        }

        /**
         * Pour juger si cela peut être considéré comme un espace blanc.
         *
         * @param ch Le caractère à vérifier.
         * @return Vrai si le caractère est un espace blanc, faux sinon.
         */
        fun isWhitespace(ch: Char): Boolean {
            if (Character.isWhitespace(ch)) {
                return true
            }
            val type = Character.getType(ch)
            return type == Character.SPACE_SEPARATOR.toInt() || type == Character.LINE_SEPARATOR.toInt() || type == Character.PARAGRAPH_SEPARATOR.toInt()
        }

        /**
         * Pour juger si c'est une ponctuation.
         *
         * @param ch Le caractère à vérifier.
         * @return Vrai si le caractère est une ponctuation, faux sinon.
         */
        fun isPunctuation(ch: Char): Boolean {
            val type = Character.getType(ch)
            return type == Character.CONNECTOR_PUNCTUATION.toInt() || type == Character.DASH_PUNCTUATION.toInt() || type == Character.START_PUNCTUATION.toInt() || type == Character.END_PUNCTUATION.toInt() || type == Character.INITIAL_QUOTE_PUNCTUATION.toInt() || type == Character.FINAL_QUOTE_PUNCTUATION.toInt() || type == Character.OTHER_PUNCTUATION.toInt()
        }
    }
}