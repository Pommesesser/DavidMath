import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class EvaluatorTest {
    @Test
    fun throwsOnDivideByZero() {
        val expression = BinaryExpression(
            Div,
            NumberExpression(1),
            NumberExpression(0)
        )

        assertThrows<IllegalArgumentException> {
            Evaluator.evaluate(expression)
        }
    }
}