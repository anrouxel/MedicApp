package fr.medicapp.medicapp.ai.tokenization

import fr.medicapp.medicapp.tokenization.BasicTokenizer
import org.junit.Test

class BasicTokenizerTest {

    val basicTokenizer = BasicTokenizer()

    @Test
    fun `testTokenize`() {
        val response = basicTokenizer.tokenize("Je suis une loutre.")
        assert(response== mutableListOf<String>("Je","suis","une","loutre","."))
    }
}