package fr.medicapp.medicapp.tokenization

import java.util.Arrays
import java.util.Locale


class BasicTokenizer(
    doLowerCase: Boolean = false
) {
    private val doLowerCase: Boolean

    init {
        this.doLowerCase = doLowerCase
    }

    fun tokenize(text: String): MutableList<String> {
        val cleanedText = cleanText(text)
        val origTokens = whitespaceTokenize(cleanedText)
        val stringBuilder = StringBuilder()
        for (token in origTokens) {
            var tokenTemp = token
            if (doLowerCase) {
                tokenTemp = tokenTemp.lowercase()
            }
            val list = runSplitOnPunc(tokenTemp)
            for (subToken in list) {
                stringBuilder.append(subToken).append(" ")
            }
        }
        val splitTokens = whitespaceTokenize(stringBuilder.toString())
        // modifier le premier token en ajoutant un underscore au début
        splitTokens[0] = "▁${splitTokens[0]}"
        return splitTokens
    }

    companion object {
        /* Performs invalid character removal and whitespace cleanup on text. */
        fun cleanText(text: String?): String {
            if (text == null) {
                throw NullPointerException("The input String is null.")
            }
            val stringBuilder = StringBuilder("")
            for (element in text) {
                val ch = element

                // Skip the characters that cannot be used.
                if (CharChecker.isInvalid(ch) || CharChecker.isControl(ch)) {
                    continue
                }
                if (CharChecker.isWhitespace(ch)) {
                    stringBuilder.append(" ")
                } else {
                    stringBuilder.append(ch)
                }
            }
            return stringBuilder.toString()
        }

        /* Runs basic whitespace cleaning and splitting on a piece of text. */
        fun whitespaceTokenize(text: String?): MutableList<String> {
            if (text == null) {
                throw NullPointerException("The input String is null.")
            }
            return mutableListOf(*text.split(" ".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray())
        }

        /* Splits punctuation on a piece of text. */
        fun runSplitOnPunc(text: String?): MutableList<String> {
            if (text == null) {
                throw NullPointerException("The input String is null.")
            }
            val tokens: MutableList<String> = ArrayList()
            var startNewWord = true
            for (element in text) {
                val ch = element
                if (CharChecker.isPunctuation(ch)) {
                    tokens.add(ch.toString())
                    startNewWord = true
                } else {
                    if (startNewWord) {
                        tokens.add("")
                        startNewWord = false
                    }
                    tokens[tokens.size - 1] = tokens.last() + ch
                }
            }
            return tokens
        }
    }
}