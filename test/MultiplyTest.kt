import org.junit.Test
import kotlin.test.assertEquals

/**
 * @author Erik van Bennekum
 *
 * Unit test for Multiply
 */

class MultiplyTest {
    @Test
    fun  multiplyTest() {
        var ln0 = LargeNumber(10, 2)
        var ln1 = LargeNumber(10, 3)
        var expected = LargeNumber(10, 6).toString()
        var result = ln0.times(ln1).toString()
        assertEquals(expected, result, "basic 2*3=6 base 10")

        ln0 = LargeNumber(10, 8, 2)
        ln1 = LargeNumber( 10, 2, 3)
        expected =  LargeNumber( 10, 1, 8, 8, 6).toString()
        result = ln0.times(ln1).toString()
        assertEquals(expected, result, "basic 82*23 base 10")

        ln0 = LargeNumber(10, 4, 7, 9)
        ln1 = LargeNumber( 10, 5, 1, 3)
        expected =  LargeNumber( 10, 2, 4, 5, 7, 2, 7).toString()
        result = ln0.times(ln1).toString()
        assertEquals(expected, result, "basic 479*513 base 10")
    }

    @Test
    fun multiplyLeadZero() {
        var ln0 = LargeNumber(10, 0, 2)
        var ln1 = LargeNumber(10, 0, 3)
        var expected = LargeNumber(10, 6).toString()
        var result = ln0.times(ln1).toString()
        assertEquals(expected, result)
    }

    @Test
    fun multiplyVsKaratsuba() {
        for (a in 0..1000) {
            for (b in 0..1000) {
                var aL = LargeNumber.parseNumber(a.toString(), 10)
                var bL = LargeNumber.parseNumber(b.toString(), 10)
                if (aL.wordCount() != bL.wordCount()) {
                    continue
                }
                println(aL.toString() + " " + bL.toString())
                assertEquals(aL.times(bL).toString(), aL.karatsuba(bL).toString())
            }
        }
    }
}