package fr.medicapp.medicapp.tokenization

class CharChecker {
    companion object {
        /** To judge whether it's an empty or unknown character.  */
        fun isInvalid(ch: Char): Boolean {
            return ch.code == 0 || ch.code == 0xfffd
        }

        /** To judge whether it's a control character(exclude whitespace).  */
        fun isControl(ch: Char): Boolean {
            if (Character.isWhitespace(ch)) {
                return false
            }
            val type = Character.getType(ch)
            return type == Character.CONTROL.toInt() || type == Character.FORMAT.toInt()
        }

        /** To judge whether it can be regarded as a whitespace.  */
        fun isWhitespace(ch: Char): Boolean {
            if (Character.isWhitespace(ch)) {
                return true
            }
            val type = Character.getType(ch)
            return type == Character.SPACE_SEPARATOR.toInt() || type == Character.LINE_SEPARATOR.toInt() || type == Character.PARAGRAPH_SEPARATOR.toInt()
        }

        /** To judge whether it's a punctuation.  */
        fun isPunctuation(ch: Char): Boolean {
            val type = Character.getType(ch)
            return type == Character.CONNECTOR_PUNCTUATION.toInt() || type == Character.DASH_PUNCTUATION.toInt() || type == Character.START_PUNCTUATION.toInt() || type == Character.END_PUNCTUATION.toInt() || type == Character.INITIAL_QUOTE_PUNCTUATION.toInt() || type == Character.FINAL_QUOTE_PUNCTUATION.toInt() || type == Character.OTHER_PUNCTUATION.toInt()
        }
    }
}