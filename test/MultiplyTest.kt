import org.junit.Test

/**
 * @author Erik van Bennekum
 *
 * Unit test for Multiply
 */

class MultiplyTest {

    @Test
    fun  ArithmeticTest() {
        val ln0 = LargeNumber(10, 2)
        val ln1 = LargeNumber(10, 3)
        val result = Multiply().execute(ln0, ln1)
    }
}