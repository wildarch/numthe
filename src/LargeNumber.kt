import java.util.*

/**
 * A number of at most [Integer.MAX_VALUE] digits in `base ∈ {2,...,16}`.
 *
 * You can use this class as follows:
 * - Parse numbers with [LargeNumber.parseNumber], like `LargeNumber.parseNumber("0AF5341", 16)`.
 * If the first character is `-`, it will be a negative number.
 * - Create numbers with the constructor (first argument is base, rest is vararg digits),
 * like `new LargeNumber(2, 1, 0, 0, 0, 1, 1)` creates `100011` in base `2`.
 * Make negative numbers with added sign: `new LargeNumber(2, Sign.NEGATIVE, 1, 0, 0, 0, 1, 1)`.
 * - Access digits by index (of LargeNumber `ln`): `ln[index]` (Kotlin) or `ln.get(index)` (Java).
 * Does NOT ignore leading zeroes.
 * - Set digits by index (of LargeNumber `ln`): `ln[index] = value` (Kotlin) or `ln.set(index, value)` (Java).
 * - Get the wordCount without leading zeroes with [wordCount]
 * - Get the size of the digits including leading zeroes with [size]
 * - The methods [plus], [minus], [times] and [karatsuba] have to be implemented for the assignment.
 * For the first 3 you can use the operators `+`, `-` and `*` respectively in Kotlin using operator overloading.
 * - The default string representation is `b_XXX...` where `b` is the base and `XXX` are the digits (prefixed with `-`
 * when negative).
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
         */
        val sign: Sign = Sign.POSITIVE,

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
         * Integer representation of a sign of a positive number (`1`).
         */
        @JvmStatic
        val POSITIVE = 1

        /**
         * Integer representation of a sign of a negative number (`-1`).
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
            var sign = Sign.POSITIVE
            var begin = true

            for (char in digitString) {
                // Detect negative sign.
                if (begin && char == '-') {
                    sign = Sign.NEGATIVE
                    continue
                }

                // Check correct characters
                if (char !in '0'..'9' && char !in 'a'..'f' && char !in 'A'..'F') {
                    throw IllegalArgumentException("Digit '$char' in digit string is not [0-9a-fA-F].")
                }

                // Skip leading zeroes.
                if (begin && char == '0') {
                    continue
                }
                else {
                    begin = false
                }

                // Parse digit.
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
     *              [Sign.POSITIVE] for positive, [Sign.NEGATIVE] for negative.
     * @param digits
     *              An array of all the digits starting with the _most_ significant bit and ending with the _least_
     *              significant bit.
     */
    constructor(base: Int, sign: Sign, vararg digits: Int) : this(base, sign, digits.toMutableList())

    /**
     * Creates a new *positive* number.
     *
     * @param base
     *              The base of the number (`2 <= base <= 16`).
     * @param digits
     *              An array of all the digits starting with the _most_ significant bit and ending with the _least_
     *              significant bit.
     */
    constructor(base: Int, vararg digits: Int) : this(base, Sign.POSITIVE, digits.toMutableList())

    init {
        // Check preconditions.
        require(base in 2..16, { "Base must be >= 2 and <= 16." })
        require(!digits.filter { it >= base }.any(), { "Not all digits are smaller than (base) $base: ${digits.joinToString("")}." })
        require(!digits.filter { it < 0 }.any(), { "No negative digits are allowed." })
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
     * @return The total amount of digits in the number including leading zeroes.
     */
    fun size() = digits.size

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
     * Get the `index`th digit in the number. Does not ignore leading zeroes.
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
     * Prints `base_XXX` where `XXX` is the number without leading zeroes. Letters are uppercase.
     */
    override fun toString(): String {
        val strings = digits.map { Integer.toHexString(it).toUpperCase() }
        return "${base}_${sign.character}${strings.subList(strings.size - wordCount(), strings.size).joinToString("")}"
    }
}