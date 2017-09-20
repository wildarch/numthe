import org.junit.Test
import kotlin.test.assertEquals

/**
 * @author Erik van Bennekum
 *
 * Unit test for Multiply
 */

class MultiplyTest {

    @Test
    fun  ArithmeticTest() {
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


}