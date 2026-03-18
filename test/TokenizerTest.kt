import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TokenizerTest {
    @Test
    fun tokenizeEmptyInput() {
        val expected = emptyList<Token>()
        val result = Tokenizer.tokenize("")

        assertEquals(expected, result)
    }

    @Test
    fun tokenizeSimpleCheck() {
        val tokens = Tokenizer.tokenize("1+1")
        val expected = listOf(
            NumberToken(1),
            Add,
            NumberToken(1)
        )

        assertEquals(expected, tokens)
    }

    @Test
    fun tokenizeComplexCheck() {
        val tokens = Tokenizer.tokenize("98+1*88/51")
        val expected = listOf(
            NumberToken(98),
            Add,
            NumberToken(1),
            Mul,
            NumberToken(88),
            Div,
            NumberToken(51)
        )

        assertEquals(expected, tokens)
    }

    @Test
    fun trailingZeroCheck() {
        val tokens = Tokenizer.tokenize("1+0")
        val expected = listOf(
            NumberToken(1),
            Add,
            NumberToken(0)
        )

        assertEquals(expected, tokens)
    }

    @Test
    fun combineDigitsCheck() {
        val tokens = Tokenizer.tokenize("19")
        val expected = listOf(NumberToken(19))

        assertEquals(expected, tokens)
    }
}