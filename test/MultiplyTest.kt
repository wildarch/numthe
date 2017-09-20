import org.junit.Test

/**
 * @author Erik van Bennekum
 *
 * Unit test for Multiply
 */

class MultiplyTest {

    @Test
    fun  ArithmeticTest() {
        val ln0 = LargeNumber(10, 2, 2, 3)
        val ln1 = LargeNumber(10, 3, 4, 1, 2)
        val result = Multiply().execute(ln0, ln1)
        println(result)
    }
}