package fr.medicapp.medicapp.ai.tokenization

import fr.medicapp.medicapp.tokenization.CharChecker
import org.junit.Test

class CharCheckerTest {

    @Test
    fun `test CharChecker isInvalid with valid char`() {
        assert(!CharChecker.isInvalid('a'))
    }

    @Test
    fun `test CharChecker isInvalid with void char`() {
        assert(CharChecker.isInvalid(Char(0)))
    }

    @Test
    fun `test CharChecker isInvalid with invalid char`() {
        val char = Char(0xfffd)
        assert(CharChecker.isInvalid(char))
    }

    @Test
    fun `test isPunctuation point`() {
        assert(CharChecker.isPunctuation('.'))
    }

    @Test
    fun `test isPunctuation dash`() {
        assert(CharChecker.isPunctuation('-'))
    }

}