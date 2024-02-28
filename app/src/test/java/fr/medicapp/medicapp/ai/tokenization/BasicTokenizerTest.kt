package fr.medicapp.medicapp.ai.tokenization

import org.junit.Assert.assertEquals
import org.junit.Test

class BasicTokenizerTest {
    @Test
    fun testTokenizeWithEmptyText() {
        val tokenizer = BasicTokenizer()
        val tokens = tokenizer.tokenize("")
        assertEquals(emptyList<String>(), tokens)
    }

    @Test
    fun testTokenizeWithOnlyInvalidCharacters() {
        val tokenizer = BasicTokenizer()
        val tokens = tokenizer.tokenize("\u0000\uFFFD")
        assertEquals(emptyList<String>(), tokens)
    }

    @Test
    fun testTokenizeWithOnlyWhitespaceCharacters() {
        val tokenizer = BasicTokenizer()
        val tokens = tokenizer.tokenize("   ")
        assertEquals(emptyList<String>(), tokens)
    }

    @Test
    fun testTokenizeWithOnlyPunctuationCharacters() {
        val tokenizer = BasicTokenizer()
        val tokens = tokenizer.tokenize(",,,.!?")
        assertEquals(listOf(",", ",", ",", ".", "!", "?"), tokens)
    }

    @Test
    fun testTokenizeWithOnlyNormalWords() {
        val tokenizer = BasicTokenizer()
        val tokens = tokenizer.tokenize("Ceci est un test")
        assertEquals(listOf("Ceci", "est", "un", "test"), tokens)
    }

    @Test
    fun testTokenizeWithMixedText() {
        val tokenizer = BasicTokenizer()
        val tokens = tokenizer.tokenize("Bonjour, le monde!")
        assertEquals(listOf("Bonjour", ",", "le", "monde", "!"), tokens)
    }

    @Test
    fun testTokenizeWithUpperCaseWordsAndLowerCaseConversion() {
        val tokenizer = BasicTokenizer(doLowerCase = true)
        val tokens = tokenizer.tokenize("BONJOUR Le Monde")
        assertEquals(listOf("bonjour", "le", "monde"), tokens)
    }

    @Test
    fun testTokenizeWithUpperCaseWordsAndPunctuationAndLowerCaseConversion() {
        val tokenizer = BasicTokenizer(doLowerCase = true)
        val tokens = tokenizer.tokenize("BONJOUR, Le Monde!")
        assertEquals(listOf("bonjour", ",", "le", "monde", "!"), tokens)
    }
}
