package fr.medicapp.medicapp.ai.tokenization

import fr.medicapp.medicapp.tokenization.CharChecker
import org.junit.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CharCheckerTest {

    @Test
    fun isInvalidNullChar() {
        assert(CharChecker.isInvalid('\u0000'))
    }

    @Test
    fun isInvalidUnknownChar() {
        assert(CharChecker.isInvalid('�'))
    }

    @Test
    fun isValidChar() {
        assert(!CharChecker.isInvalid('a'))
    }

    @Test
    fun isNotControlWhiteSpace() {
        assert(!CharChecker.isControl(' '))
    }

    @ParameterizedTest(name = "Test if controls character are detected")
    @ValueSource(chars = ['\u0000', '\u0001', '\u0005', '\u000A', '\u001F', '\u009F'])
    fun isControlControlsChar(controlChar: Char) {
        assert(CharChecker.isControl(controlChar))
    }

    @ParameterizedTest(name = "Test if format character are detected")
    @ValueSource(chars = ['\u00AD', '\u0600', '\uDC48'])
    fun isControlFormatChar(formatChar: Char) {
        assert(CharChecker.isControl(formatChar))
    }

    @Test
    fun isNotControl() {
        assert(!CharChecker.isControl('a'))
    }

    @ParameterizedTest(name = "Test if WhiteSpace character are detected")
    @ValueSource(chars = [' ', ' ', ' '])
    fun isWhiteSpace(whiteSpace: Char) {
        assert(CharChecker.isWhitespace(whiteSpace))
    }

    @Test
    fun isNotWhiteSpace() {
        assert(!CharChecker.isWhitespace('a'))
    }

    @ParameterizedTest(name = "Test if Punctuation are detected")
    @ValueSource(chars = ['_', '–', '(', ']', '"', '\'', '!'])
    fun isPunctuation(punctuation: Char) {
        assert(CharChecker.isPunctuation(punctuation))
    }

    @Test
    fun isNotPunctuation() {
        assert(!CharChecker.isPunctuation(('a')))
    }
}
