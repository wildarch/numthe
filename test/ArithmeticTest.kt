
import org.junit.Test
import java.math.BigInteger
import java.util.*
import kotlin.test.assertEquals

/**
 * @author Ruben Schellekens
 */
class ArithmeticTest {

    companion object {

        val seed: Long = 912839123
        val maxBits: Int = 30013
    }

    inline fun `Test integer base 2-16`(
            bigIntSum: (BigInteger, BigInteger) -> BigInteger,
            largeNumSum: (LargeNumber, LargeNumber, Int) -> LargeNumber,
            maxBits: Int = Companion.maxBits
    ) {
        val random = Random(seed)

        for (base in 2..16) {
            for (i in 0..25) {
                val bits = random.nextInt(maxBits) + 4
                val int1 = BigInteger(bits, random).add(BigInteger.valueOf(32145))
                var int2 = BigInteger(bits, random)
                while (int2 > int1) {
                    int2 = BigInteger(bits, random)
                }

                val sum = bigIntSum(int1, int2)

                val largeNum1 = LargeNumber.parseNumber(int1.toString(base), base)
                val largeNum2 = LargeNumber.parseNumber(int2.toString(base), base)
                val result = largeNumSum(largeNum1, largeNum2, base)

                val sumResult = "${base}_${sum.toString(base).toUpperCase()}"
                val numberResult = result.toString()

                assertEquals(
                        sumResult,
                        numberResult,
                        "${int1.toString(base)} & ${int2.toString(base)} = ${sum.toString(base)}")
            }
        }
    }

    @Test
    fun `Add random integers base 2-16`() {
        `Test integer base 2-16`(
                { a, b -> a.add(b) },
                { a, b, _ -> a + b }
        )
    }

    @Test
    fun `Add zero base 2-16`() {
        `Test integer base 2-16`(
                { a, _ -> a },
                { a, _, base -> a + LargeNumber.zero(base) }
        )
    }

    @Test
    fun `Subtract random integers base 2-16`() {
        `Test integer base 2-16`(
                { a, b -> a.subtract(b) },
                { a, b, _ -> a - b }
        )
    }

    @Test
    fun `Subtract zero base 2-16`() {
        `Test integer base 2-16`(
                { a, _ -> a },
                { a, _, base -> a - LargeNumber.zero(base) }
        )
    }

    @Test
    fun `Multiply random integers base 2-16`() {
        `Test integer base 2-16`(
                { a, b -> a.multiply(b) },
                { a, b, _ -> a * b },
                2303
        )
    }

    @Test
    fun `Multiply zero base 2-16`() {
        `Test integer base 2-16`(
                { _, _ -> BigInteger.ZERO },
                { a, _, base -> a * LargeNumber.zero(base) },
                2303
        )
    }

    @Test
    fun `Karatsuba random integers base 2-16`() {
        `Test integer base 2-16`(
                { a, b -> a * b },
                { a, b, _ -> a.karatsuba(b) },
                2303
        )
    }

    @Test
    fun `Karatsuba zero base 2-16`() {
        `Test integer base 2-16`(
                { _, _ -> BigInteger.ZERO },
                { a, _, base -> a.karatsuba(LargeNumber.zero(base)) },
                2303
        )
    }
}