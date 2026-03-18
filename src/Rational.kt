import kotlin.math.abs

data class Rational(
    val numerator: Int,
    val denominator: Int
) {
    companion object {
        val ZERO = Rational(0,1)
        val MINUS_ONE = Rational(-1, 1)
        val ONE = Rational(1, 1)
    }

    init {
        if (denominator == 0)
            throw IllegalArgumentException("Illegal division by zero")
    }

    override fun toString() =
        when {
            numerator == 0 -> "0"
            denominator == 1 -> "$numerator"
            else -> "$numerator/$denominator"
        }
}

object RationalArithmetic {
    fun add(a: Rational, b: Rational): Rational {
        val (aExpanded, bExpanded) = expandToCommonDenominator(a, b)
        val expandedResult = Rational(
            aExpanded.numerator + bExpanded.numerator,
            aExpanded.denominator
        )

        return normalize(expandedResult)
    }

    fun sub(a: Rational, b: Rational): Rational {
        val (aExpanded, bExpanded) = expandToCommonDenominator(a, b)
        val expandedResult = Rational(
            aExpanded.numerator - bExpanded.numerator,
            aExpanded.denominator
        )

        return normalize(expandedResult)
    }

    fun mul(a: Rational, b: Rational): Rational {
        val rawResult = Rational(
            a.numerator * b.numerator,
            a.denominator * b.denominator
        )

        return normalize(rawResult)
    }

    fun div(a: Rational, b: Rational): Rational {
        if (b.numerator == 0)
            throw IllegalArgumentException("Illegal division by zero")

        val rawResult = Rational(
            a.numerator * b.denominator,
            a.denominator * b.numerator
        )

        return normalize(rawResult)
    }

    fun normalize(rational: Rational): Rational {
        val gcd = greatestCommonDenominator(rational.numerator, rational.denominator)
        val reducedRational = Rational(
            rational.numerator / gcd,
            rational.denominator / gcd
        )

        return when {
            reducedRational.denominator < 0 -> Rational(
                -reducedRational.numerator,
                -reducedRational.denominator
            )

            else -> reducedRational
        }
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
