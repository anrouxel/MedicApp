package fr.medicapp.medicapp.ai.tokenization

import fr.medicapp.medicapp.tokenization.BasicTokenizer
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.jupiter.api.assertThrows

class BasicTokenizerTest {

    val basicTokenizer = BasicTokenizer()

    @Test
    fun `test tokenize valide en français`() {
        val response = basicTokenizer.tokenize("Je suis une loutre.")
        assertEquals(mutableListOf<String>("Je","suis","une","loutre","."),response)
    }

    @Test
    fun `test tokenize valide en anglais`() {
        val response = basicTokenizer.tokenize("This is in English.")
        assertEquals(mutableListOf<String>("This","is","in","English","."),response)
    }

    @Test
    fun `test tokenize en français avec des mots inconnus`() {
        val response = basicTokenizer.tokenize("Ceci est gjaeorgn.")
        assertEquals(mutableListOf<String>("Ceci","est","gjaeorgn","."), response)
    }

    @Test
    fun `test tokenize string vide`() {
        val response = basicTokenizer.tokenize("")
        assertEquals(mutableListOf<String>(), response)
    }

    @Test
    fun `test runSplitOnPunc NullPointerException`() {
        assertThrows<NullPointerException> {
            BasicTokenizer.runSplitOnPunc(null)
        }
    }

    @Test
    fun `test runSplitOnPunc Valide sans points`() {
        val response = BasicTokenizer.runSplitOnPunc("Salut y a pas de points")
        assertEquals(mutableListOf("Salut y a pas de points"), response)
    }

    @Test
    fun `test runSplitOnPunc Valide avec points`() {
        val response = BasicTokenizer.runSplitOnPunc("Salut le point.")
        assertEquals(mutableListOf("Salut le point","."), response)
    }

    @Test
    fun `test runSplitOnPunc valide trois points`() {
        val response = BasicTokenizer.runSplitOnPunc("Trois points il y a...")
        assertEquals(mutableListOf("Trois points il y a",".",".","."),response)
    }

    @Test
    fun `test whitespaceTokenize NullPointerException`() {
        assertThrows<NullPointerException> {
            BasicTokenizer.whitespaceTokenize(null)
        }
    }

    @Test
    fun `test cleanText NullPointerException`() {
        assertThrows<NullPointerException> {
            BasicTokenizer.cleanText(null)
        }
    }
}