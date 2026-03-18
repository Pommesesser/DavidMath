sealed class Token

data class NumberToken(val num: Int): Token() {
    override fun toString() =
        num.toString()
}

sealed class ParenthesisToken: Token()

object OpeningParenthesisToken: ParenthesisToken() {
    override fun toString() =
        "("
}

object ClosingParenthesisToken: ParenthesisToken() {
    override fun toString() =
        ")"
}

sealed class OperatorToken: Token()

object Add: OperatorToken() {
    override fun toString() =
        "+"
}

object Sub: OperatorToken() {
    override fun toString() =
        "-"
}

object Mul: OperatorToken() {
    override fun toString() =
        "*"
}

object Div: OperatorToken() {
    override fun toString() =
        "/"
}

class TokenizerException(
    val char: Char,
    val index: Int
) : Exception("Invalid token '$char' at index $index")

object Tokenizer {
    fun tokenize(input: String): List<Token> {
        val tokens = mutableListOf<Token>()
        var pos = 0
        while (pos < input.length) {
            if (input[pos].isWhitespace()) {
                pos++
                continue
            }

            val (token, endPos) = matchToken(input, pos)
            tokens.add(token)
            pos = endPos
        }

        return tokens
    }

    fun matchToken(input: String, startPos: Int): Pair<Token, Int> {
        return when {
            input[startPos].isDigit() -> parseNumber(input, startPos)
            else -> parseSymbol(input, startPos)
        }
    }

    fun parseNumber(input: String, startPos: Int): Pair<NumberToken, Int> {
        val adjacentNumbers = input.substring(startPos)
            .takeWhile { it.isDigit() }

        return Pair(
            NumberToken(adjacentNumbers.toInt()),
            startPos + adjacentNumbers.length
        )
    }

    fun parseSymbol(input: String, startPos: Int): Pair<Token, Int> {
        val token = when (input[startPos]) {
            '+' -> Add
            '-' -> Sub
            '*' -> Mul
            '/' -> Div
            '(' -> OpeningParenthesisToken
            ')' -> ClosingParenthesisToken
            else -> throw TokenizerException(input[startPos], startPos)
        }
        return Pair(
            token,
            startPos + 1
        )
    }
}