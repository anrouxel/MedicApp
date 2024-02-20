package fr.medicapp.medicapp.ai.tokenization

import org.junit.Assert.assertEquals
import org.junit.Test

class BasicTokenizerCleanTextTest {

    @Test
    fun testCleanTextWithEmptyText() {
        val cleanedText = BasicTokenizer.cleanText("")
        assertEquals("", cleanedText)
    }

    @Test
    fun testCleanTextWithOnlyInvalidCharacters() {
        val cleanedText = BasicTokenizer.cleanText("\u0000\uFFFD")
        assertEquals("", cleanedText)
    }

    @Test
    fun testCleanTextWithOnlyWhitespaceCharacters() {
        val cleanedText = BasicTokenizer.cleanText("   ")
        assertEquals("   ", cleanedText)
    }

    @Test
    fun testCleanTextWithOnlyPunctuationCharacters() {
        val cleanedText = BasicTokenizer.cleanText(",,,.!?")
        assertEquals(",,,.!?", cleanedText)
    }

    @Test
    fun testCleanTextWithOnlyNormalWords() {
        val cleanedText = BasicTokenizer.cleanText("Ceci est un test")
        assertEquals("Ceci est un test", cleanedText)
    }

    @Test
    fun testCleanTextWithSpecialCharacters() {
        val cleanedText = BasicTokenizer.cleanText("C'est l'été à Paris!")
        assertEquals("C'est l'été à Paris!", cleanedText)
    }

    @Test
    fun testCleanTextWithMixedText() {
        val cleanedText = BasicTokenizer.cleanText("Ceci est l'été à Paris, n'est-ce pas?")
        assertEquals("Ceci est l'été à Paris, n'est-ce pas?", cleanedText)
    }
}