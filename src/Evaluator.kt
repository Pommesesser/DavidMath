object Evaluator {
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
            is Add -> RationalArithmetic.normalize(a)

            is Sub -> RationalArithmetic.mul(
                a,
                Rational.MINUS_ONE
            )

            else -> throw IllegalArgumentException("Illegal unary operator")
        }

    fun applyBinaryOperator(op: OperatorToken, a: Rational, b: Rational): Rational =
        when (op) {
            is Add -> RationalArithmetic.add(a,b)

            is Sub -> RationalArithmetic.sub(a,b)

            is Mul -> RationalArithmetic.mul(a,b)

            is Div -> RationalArithmetic.div(a,b)
        }
}
