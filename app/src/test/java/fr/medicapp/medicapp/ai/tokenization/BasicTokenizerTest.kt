package fr.medicapp.medicapp.ai.tokenization

import fr.medicapp.medicapp.tokenization.BasicTokenizer
import org.junit.Assert.assertEquals
import org.junit.Test

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

}