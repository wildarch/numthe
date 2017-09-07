import org.junit.Test
import kotlin.test.assertEquals

/**
 * @author Ruben Schellekens
 */
internal class LargeNumberTest {

    // TODO: Write test for add/subtract/multiply/karatsuba.

    @Test(expected = IllegalArgumentException::class)
    fun `Precondition - Base gr 16`() {
        // Too large base (>16).
        LargeNumber(17)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Precondition - Base 1`() {
        // Base 1.
        LargeNumber(1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Precondition - Negative base`() {
        // Negative base.
        LargeNumber(-3)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Precondition - Invalid digits`() {
        // Invalid digits.
        LargeNumber(10, 5, 1, 6, 10, 23, 1, 12)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Precondition - Invalid digits 2`() {
        // Invalid digits 2.
        LargeNumber(2, 0, 1, 1, 0, 0, 2, 0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Precondition - Negative digits`() {
        // Negative digits.
        LargeNumber(3, 0, -2, -51, -Integer.MAX_VALUE)
    }

    @Test
    fun `Word Count`() {
        // Normal.
        val ln0 = LargeNumber(2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0)
        assertEquals(12, ln0.wordCount(), "Normal word count.")

        // Leading zeroes.
        val ln1 = LargeNumber(5, 0, 0, 0, 1, 0, 1)
        assertEquals(3, ln1.wordCount(), "With leading zeroes.")

        // Leading and trailing zeroes.
        val ln2 = LargeNumber(5, 0, 0, 0, 1, 0, 1, 0, 0)
        assertEquals(5, ln2.wordCount(), "With leading zeroes and trailing zeroes.")

        // No digits.
        val ln3 = LargeNumber(7)
        assertEquals(0, ln3.wordCount(), "No digits.")

        // One digit.
        val ln4 = LargeNumber(10, 8)
        assertEquals(1, ln4.wordCount(), "One digit.")

        // One digit w/ leading zeroes.
        val ln5 = LargeNumber(10, 8)
        assertEquals(1, ln5.wordCount(), "One digit.")
    }

    @Test
    fun `Parse large number`() {
        // Positive Binary
        val ln0a = LargeNumber.parseNumber("10101111101010110", 2)
        val ln0b = LargeNumber(2, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0)
        assertEquals(ln0b, ln0a, "2_10101111101010110 object")
        assertEquals("2_10101111101010110", ln0a.toString(), "2_10101111101010110 string")

        // Positive Octal
        val ln1a = LargeNumber.parseNumber("34776035", 8)
        val ln1b = LargeNumber(8, 3, 4, 7, 7, 6, 0, 3, 5)
        assertEquals(ln1b, ln1a, "8_34776035 object")
        assertEquals("8_34776035", ln1a.toString(), "8_34776035 string")

        // Positive Decimal
        val ln2a = LargeNumber.parseNumber("26625960889618", 10)
        val ln2b = LargeNumber(10, 2, 6, 6, 2, 5, 9, 6, 0, 8, 8, 9, 6, 1, 8)
        assertEquals(ln2b, ln2a, "10_26625960889618 object")
        assertEquals("10_26625960889618", ln2a.toString(), "10_26625960889618 string")

        // Positive Hexadecimal
        val ln3a = LargeNumber.parseNumber("2D4D9FE806AC", 16)
        val ln3b = LargeNumber(16, 2, 13, 4, 13, 9, 15, 14, 8, 0, 6, 10, 12)
        assertEquals(ln3b, ln3a, "16_2D4D9FE806AC object")
        assertEquals("16_2D4D9FE806AC", ln3a.toString(), "16_2D4D9FE806AC string")

        // Negative Hexadecimal
        val ln4a = LargeNumber.parseNumber("-2D4D9FE806AC", 16)
        val ln4b = LargeNumber(16, Sign.NEGATIVE, 2, 13, 4, 13, 9, 15, 14, 8, 0, 6, 10, 12)
        assertEquals(ln4b, ln4a, "16_-2D4D9FE806AC object")
        assertEquals("16_-2D4D9FE806AC", ln4a.toString(), "16_-2D4D9FE806AC string")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Parse large number exception - Base gr 16`() {
        // Too large base (>16).
        LargeNumber.parseNumber("110100101", 17)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Parse large number exception - Base 1`() {
        // Base 1.
        LargeNumber.parseNumber("000000000", 1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Parse large number exception - Negative base`() {
        // Negative base.
        LargeNumber.parseNumber("110100101", -1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Parse large number exception - Invalid digits`() {
        // Invalid digits.
        LargeNumber.parseNumber("aa2138178", 3)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Parse large number exception - Invalid digits 2`() {
        // Invalid digits 2.
        LargeNumber.parseNumber("010102100101", 2)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Parse large number exception - Negative digits`() {
        // Negative digits.
        LargeNumber.parseNumber("-231-2312-32", 8)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Parse large number exception - Bullshit`() {
        // Bullshit.
        LargeNumber.parseNumber("-1283;k43jckj34il63", 10)
    }

    @Test
    fun `Get value by index`() {
        val string = "00234190780699"
        val ln = LargeNumber.parseNumber(string, 10)

        // Check all digits in the array, ignore leading zeroes.
        for (i in 2 until string.length) {
            assertEquals(string[i].toString(), ln[i].toString(), "Compare digits")
        }
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun `Get value outside range - Negative`() {
        val ln = LargeNumber.parseNumber("00234190780699", 10)
        ln[-1]
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun `Get value outside range - Too large`() {
        val ln = LargeNumber.parseNumber("00234190780699", 10)
        ln[12]
    }

    @Test
    fun `Set value by index`() {
        val ln = LargeNumber.parseNumber("00234190780699", 10)

        ln[0] = 4
        assertEquals("00434190780699", ln.toString(), "Set digit")

        ln[0] = 5
        assertEquals("00534190780699", ln.toString(), "Set digit")

        ln[ln.wordCount() - 1] = 0
        assertEquals("00534190780690", ln.toString(), "Set digit")
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun `Set value outside range - Negative`() {
        val ln = LargeNumber.parseNumber("00234190780699", 10)
        ln[-1] = 3
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun `Set value outside range - Too large`() {
        val ln = LargeNumber.parseNumber("00234190780699", 10)
        ln[34] = 3
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun `Set value outside range - Wrong base digit`() {
        val ln = LargeNumber.parseNumber("00234190780699", 10)
        ln[3] = 17
    }

    @Test
    fun `Convert to string`() {
        // Binary
        val ln0b = LargeNumber(2, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1)
        assertEquals("2_1010111110101011", ln0b.toString(), "ToString base 2")

        // Octal
        val ln1b = LargeNumber(8, 3, 4, 7, 7, 6, 0, 3)
        assertEquals("8_3477603", ln1b.toString(), "ToString base 8")

        // Decimal
        val ln2b = LargeNumber(10, 2, 6, 6, 2, 5, 9, 6, 0, 8, 8, 9, 6, 1)
        assertEquals("10_2662596088961", ln2b.toString(), "ToString base 10")

        // Hexadecimal
        val ln3b = LargeNumber(16, 2, 13, 4, 13, 9, 15, 14, 8, 0, 6, 10)
        assertEquals("16_2D4D9FE806A", ln3b.toString(), "ToString base 16")

        // Hexadecimal negative.
        val ln4b = LargeNumber(16, Sign.NEGATIVE, 2, 13, 4, 13, 9, 15, 14, 8, 0, 6, 10)
        assertEquals("16_-2D4D9FE806A", ln4b.toString(), "ToString base 16 negative")
    }
}