/**
 * A number of at most [Integer.MAX_VALUE] digits in `base ∈ {2,...,16}`.
 *
 * @author Ruben Schellekens
 */
data class LargeNumber(

        /**
         * The base of the number (duh): `2 <= base <= 16`.
         */
        val base: Int,

        /**
         * The sign of the number.
         *
         * - `1` for positive numbers.
         * - `-1` for negative numbers.
         *
         * See also constants [POSITIVE] and [NEGATIVE].
         *
         * A number of 0 has sign `1`.
         */
        val sign: Int = 1,

        /**
         * A list containing all the digits in the number.
         *
         * - The first digit (index 0) is the _most significant digit_.
         * - The last digit (index size-1) is the _least significant digit_.
         */
        val digits: MutableList<Int>
) {

    /**
     * @author Ruben Schellekens
     */
    companion object {

        /**
         * Instance for number `0` with base `10`.
         */
        @JvmStatic
        val ZERO_BASE_TEN = LargeNumber(10, 0)

        /**
         * Sign of a positive number (`1`).
         */
        @JvmStatic
        val POSITIVE = 1

        /**
         * Sign of a negative number (`-1`).
         */
        @JvmStatic
        val NEGATIVE = -1

        /**
         * Parses a string of digit to a LargeNumer taking account the given base.
         *
         * Accepts negative numbers (start with '`-`') and positive numbers (no prefix).
         */
        @JvmStatic
        fun parseNumber(digitString: String, base: Int): LargeNumber {
            val digitList: MutableList<Int> = ArrayList()
            var sign = POSITIVE

            for (char in digitString) {
                if (char == '-') {
                    sign = NEGATIVE
                    continue
                }

                val parsedDigit = Integer.parseInt(char.toString(), base)
                digitList.add(parsedDigit)
            }

            return LargeNumber(base, sign, digitList)
        }
    }

    /**
     * Creates a new number with a given sign.
     *
     * @param base
     *              The base of the number (`2 <= base <= 16`).
     * @param sign
     *              `1` for positive, `-1` for negative.
     * @param digits
     *              An array of all the digits starting with the _most_ significant bit and ending with the _least_
     *              significant bit.
     */
    constructor(base: Int, sign: Sign, vararg digits: Int) : this(base, sign.sign, digits.toMutableList())

    /**
     * Creates a new *positive* number.
     *
     * @param base
     *              The base of the number (`2 <= base <= 16`).
     * @param digits
     *              An array of all the digits starting with the _most_ significant bit and ending with the _least_
     *              significant bit.
     */
    constructor(base: Int, vararg digits: Int) : this(base, POSITIVE, digits.toMutableList())

    init {
        // Check preconditions.
        require(base in 2..16, { "Base must be >= 2 and <= 16." })
        require(sign == 1 || sign == -1, { "Sign must be either '-1' (negative) or '1' (positive)." })
        require(!digits.filter { it >= base }.any(), { "Not all digits are smaller than (base) $base: ${digits.joinToString("")}." })
    }

    /**
     * @return The total amount of digits excluding leading zeroes.
     */
    fun wordCount(): Int {
        // Count leading zeroes.
        var zeroes = 0
        for (digit in digits) {
            if (digit != 0) {
                break
            }

            zeroes++
        }

        // Exclude leading zeroes.
        return digits.size - zeroes
    }

    /**
     * Multiply to large numbers with the same base using Karatsuba's algorithm.
     *
     * @return `this*other`
     * @throws IllegalArgumentException When the number bases don't match.
     */
    @Throws(IllegalArgumentException::class)
    fun karatsuba(other: LargeNumber): LargeNumber {
        // Check precondition.
        require(base == other.base, { "Numbers don't have the same base: $base vs ${other.base}" })

        // TODO: Implement the karatsuba algorithm in new class. Invoke MyKaratsubaClass(this).multiply(other).

        return ZERO_BASE_TEN
    }

    /**
     * Adds two large numbers with the same base together.
     *
     * @return `this+other`
     * @throws IllegalArgumentException When the number bases don't match.
     */
    @Throws(IllegalArgumentException::class)
    operator fun plus(other: LargeNumber): LargeNumber {
        // Check precondition.
        require(base == other.base, { "Numbers don't have the same base: $base vs ${other.base}" })

        // TODO: Implement primary school addition in new class. Invoke MyAdditionClass(this).add(other).

        return ZERO_BASE_TEN
    }

    /**
     * Subtract the other number from this number (with the same base).
     *
     * @return `this-other`
     * @throws IllegalArgumentException When the number bases don't match.
     */
    @Throws(IllegalArgumentException::class)
    operator fun minus(other: LargeNumber): LargeNumber {
        // Check precondition.
        require(base == other.base, { "Numbers don't have the same base: $base vs ${other.base}" })

        // TODO: Implement primary school subtraction in new class. Invoke MySubtractionClass(this).subtract(other).

        return ZERO_BASE_TEN
    }

    /**
     * Multiply to large numbers with the same base.
     *
     * @return `this*other`
     * @throws IllegalArgumentException When the number bases don't match.
     */
    @Throws(IllegalArgumentException::class)
    operator fun times(other: LargeNumber): LargeNumber {
        // Check precondition.
        require(base == other.base, { "Numbers don't have the same base: $base vs ${other.base}" })

        // TODO: Implement primary school multiplication in new class. Invoke MyMultiplicationClass(this).multiply(other).

        return ZERO_BASE_TEN
    }

    /**
     * Get the `index`th digit in the number.
     *
     * @throws IndexOutOfBoundsException When there is no digit at index `index`.
     * @see LargeNumber.digits
     */
    @Throws(IndexOutOfBoundsException::class)
    operator fun get(index: Int) = digits[index]

    /**
     * Set the value of the `index`th digit to `value`.
     *
     * @throws IllegalArgumentException When the value is `>=base` or `<0`.
     * @throws IndexOutOfBoundsException When there is no digit at index `index`.
     */
    @Throws(IllegalArgumentException::class, IndexOutOfBoundsException::class)
    operator fun set(index: Int, value: Int) {
        if (value >= base || value < 0) {
            throw IllegalArgumentException("value is not between 0 and base (exclusive)")
        }

        digits[index] = value
    }

    /**
     * Prints `base_XXX` where `XXX` is the number without leading zeroes.
     */
    override fun toString(): String {
        val signSign = if (sign == 1) "" else "-"
        return "${base}_$signSign${digits.subList(digits.size - wordCount(), digits.size).joinToString("")}"
    }

    /**
     * @author Ruben Schellekens
     */
    enum class Sign(val sign: Int) {

        POSITIVE(LargeNumber.POSITIVE),
        NEGATIVE(LargeNumber.NEGATIVE)
    }
}