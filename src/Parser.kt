sealed class Expression

data class NumberExpression(val num: Int): Expression() {
    override fun toString() =
        num.toString()
}

data class BinaryExpression(
    val op: OperatorToken,
    val a: Expression,
    val b: Expression
): Expression() {
    override fun toString() =
        "($a$op$b)"
}

data class UnaryExpression(
    val op: OperatorToken,
    val a: Expression
): Expression() {
    override fun toString() =
        "($op$a)"
}

object Parser {
    fun parse(tokens: List<Token>): Expression {
        val (expression, position) = parseExpression(tokens, 0)
        if (position != tokens.size)
            throw IllegalArgumentException("Invalid expression")

        return expression
    }

    fun parseExpression(tokens: List<Token>, startPos: Int): Pair<Expression, Int> {
        var (left, pos) = parseTerm(tokens, startPos)

        while (pos < tokens.size && (tokens[pos] is Add || tokens[pos] is Sub)) {
            val operator = tokens[pos] as OperatorToken
            val (right, endPos) = parseTerm(tokens, pos + 1)
            left = BinaryExpression(operator, left, right)
            pos = endPos
        }

        return Pair(left, pos)
    }

    fun parseTerm(tokens: List<Token>, startPos: Int): Pair<Expression, Int> {
        var (left, i) = parseFactor(tokens, startPos)

        while (i < tokens.size && (tokens[i] is Mul || tokens[i] is Div)) {
            val op = tokens[i] as OperatorToken
            val (right, endPos) = parseFactor(tokens, i + 1)
            left = BinaryExpression(op, left, right)
            i = endPos
        }

        return Pair(left, i)
    }

    fun parseFactor(tokens: List<Token>, startPos: Int): Pair<Expression, Int> {
        if (startPos >= tokens.size)
            throw IllegalArgumentException("Unexpected end of input")

        return when (val token = tokens[startPos]) {
            is Add -> {
                val op = Add
                val (subExpression, endPos) = parseFactor(tokens, startPos + 1)
                UnaryExpression(op, subExpression) to endPos
            }

            is Sub -> {
                val op = Sub
                val (subExpression, endPos) = parseFactor(tokens, startPos + 1)
                UnaryExpression(op, subExpression) to endPos
            }

            is NumberToken ->
                NumberExpression(token.num) to startPos + 1

            is OpeningParenthesisToken -> {
                val (expr, next) = parseExpression(tokens, startPos + 1)
                if (tokens[next] !is ClosingParenthesisToken)
                    throw IllegalArgumentException("Missing )")
                expr to next + 1
            }

            else -> throw IllegalArgumentException("Unexpected token")
        }
    }
}