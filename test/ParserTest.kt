import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ParserTest {
    @Test
    fun parseThrowsAdjacentNumbers() {
        val tokens = listOf(
            NumberToken(1),
            NumberToken(2)
        )

        assertThrows<IllegalArgumentException> {
            Parser.parse(tokens)
        }
    }

    @Test
    fun parseThrowsOnTrailingOperator() {
        val tokens = listOf(
            NumberToken(1),
            Add,
            NumberToken(2),
            Add
        )

        assertThrows<IllegalArgumentException> {
            Parser.parse(tokens)
        }
    }

    @Test
    fun parseThrowsOnEmptyParentheses() {
        val tokens = listOf(
            NumberToken(1),
            Add,
            NumberToken(2),
            OpeningParenthesisToken,
            ClosingParenthesisToken
        )

        assertThrows<IllegalArgumentException> {
            Parser.parse(tokens)
        }
    }

    @Test
    fun parseThrowsOnUnopenedParenthesis() {
        val tokens = listOf(
            NumberToken(1),
            Add,
            NumberToken(2),
            ClosingParenthesisToken
        )

        assertThrows<IllegalArgumentException> {
            Parser.parse(tokens)
        }
    }

    @Test
    fun parseThrowsOnUnclosedParenthesis() {
        val tokens = listOf(
            NumberToken(1),
            Add,
            NumberToken(2),
            OpeningParenthesisToken
        )

        assertThrows<IllegalArgumentException> {
            Parser.parse(tokens)
        }
    }

    @Test
    fun parseCorrectPrecedence() {
        val tokens = listOf(
            NumberToken(1),
            Add,
            NumberToken(2),
            Mul,
            NumberToken(3)
        )

        val result = Parser.parse(tokens)
        assertEquals("(1+(2*3))", result.toString())
    }

    @Test
    fun parseParenthesisExpression() {
        val tokens = listOf(
            OpeningParenthesisToken,
            NumberToken(1),
            Add,
            NumberToken(2),
            ClosingParenthesisToken,
            Mul,
            NumberToken(3)
        )

        val result = Parser.parse(tokens)
        assertEquals("((1+2)*3)", result.toString())
    }

    @Test
    fun parseNestedParentheses() {
        val tokens = listOf(
            NumberToken(1),
            Add,
            OpeningParenthesisToken,
            NumberToken(2),
            Mul,
            OpeningParenthesisToken,
            NumberToken(3),
            Add,
            NumberToken(4),
            ClosingParenthesisToken,
            ClosingParenthesisToken
        )

        val result = Parser.parse(tokens)
        assertEquals("(1+(2*(3+4)))", result.toString())
    }
}