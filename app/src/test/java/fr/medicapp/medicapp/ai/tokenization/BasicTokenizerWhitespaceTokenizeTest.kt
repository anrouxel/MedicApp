package fr.medicapp.medicapp.ai.tokenization

import fr.medicapp.medicapp.tokenization.BasicTokenizer
import org.junit.Assert.assertEquals
import org.junit.Test

class BasicTokenizerWhitespaceTokenizeTest {
    @Test
    fun testWhitespaceTokenizeWithEmptyText() {
        val tokens = BasicTokenizer.whitespaceTokenize("")
        assertEquals(emptyList<String>(), tokens)
    }

    @Test
    fun testWhitespaceTokenizeWithOnlyWhitespaceCharacters() {
        val tokens = BasicTokenizer.whitespaceTokenize("   ")
        assertEquals(emptyList<String>(), tokens)
    }

    @Test
    fun testWhitespaceTokenizeWithSingleWord() {
        val tokens = BasicTokenizer.whitespaceTokenize("Hello")
        assertEquals(listOf("Hello"), tokens)
    }

    @Test
    fun testWhitespaceTokenizeWithMultipleWords() {
        val tokens = BasicTokenizer.whitespaceTokenize("Hello world!")
        assertEquals(listOf("Hello", "world!"), tokens)
    }

    @Test
    fun testWhitespaceTokenizeWithWordsAndLeadingTrailingWhitespace() {
        val tokens = BasicTokenizer.whitespaceTokenize("  Hello world!   ")
        assertEquals(listOf("", "", "Hello", "world!"), tokens)
    }

    @Test
    fun testWhitespaceTokenizeWithSpecialCharactersAndWords() {
        val tokens = BasicTokenizer.whitespaceTokenize("C'est l'été à Paris!")
        assertEquals(listOf("C'est", "l'été", "à", "Paris!"), tokens)
    }
}
