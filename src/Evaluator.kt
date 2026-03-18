import kotlin.math.abs

data class Rational(
    val numerator: Int,
    val denominator: Int
) {
    init {
        if (denominator == 0)
            throw IllegalArgumentException("Illegal division by zero")
    }

    override fun toString() =
        "$numerator/$denominator"
}

object RationalCalculator {
    fun add(a: Rational, b: Rational): Rational {
        val (aExpanded, bExpanded) = expandToCommonDenominator(a, b)
        val expandedResult = Rational(
            aExpanded.numerator - bExpanded.numerator,
            aExpanded.denominator
        )

        TODO()
    }

    fun sub(a: Rational, b: Rational): Rational {
        TODO()
    }

    fun mul(a: Rational, b: Rational): Rational {
        TODO()
    }

    fun div(a: Rational, b: Rational): Rational {
        TODO()
    }

    fun normalize(a: Rational): Rational {
        val gcd = greatestCommonDenominator(a.numerator, a.denominator)
        
    }

    fun greatestCommonDenominator(a: Int, b: Int): Int =
        when {
            b == 0 -> abs(a)
            else -> greatestCommonDenominator(b, a % b)
        }

    fun expandToCommonDenominator(a: Rational, b: Rational) =
        Pair(
            Rational(a.numerator * b.denominator, a.denominator * b.denominator),
            Rational(b.numerator * a.denominator, b.denominator * a.denominator)
        )
}

object ExpressionEvaluator {
    fun evaluate(expression: Expression): Rational =
        when (expression) {
            is NumberExpression -> Rational(expression.num, 1)

            is UnaryExpression -> {
                val a = evaluate(expression.a)
                applyUnaryOperator(expression.op, a)
            }

            is BinaryExpression -> {
                val a = evaluate(expression.a)
                val b = evaluate(expression.b)
                applyBinaryOperator(expression.op, a, b)
            }
        }

    fun applyUnaryOperator(op: OperatorToken, a: Rational): Rational =
        when (op) {
            is Add -> a

            is Sub -> Rational(-a.numerator, a.denominator)

            else -> throw IllegalArgumentException("Illegal unary operator")
        }

    fun applyBinaryOperator(op: OperatorToken, a: Rational, b: Rational): Rational =
        when (op) {
            is Add -> TODO()

            is Sub -> TODO()

            is Mul -> TODO()

            is Div -> TODO()
        }
}
