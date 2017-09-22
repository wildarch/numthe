import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by Daan de Graaf on 22/09/17.
 */
class KaratsubaTest {
    @Test
    fun multiplyLeadZero() {
        val ln0 = LargeNumber(10, 0, 2)
        val ln1 = LargeNumber(10, 0, 3)
        val expected = LargeNumber(10, 6).toString()
        val result = ln0.times(ln1).toString()
        assertEquals(expected, result)
    }

    private val MAX_NUM = 100
    private val OFFSETS = intArrayOf(0, 123, 5323, 198323)

    @Test
    fun multiplyVsKaratsuba() {
        for (offset in OFFSETS) {
            for (a in offset..offset+MAX_NUM) {
                for (b in offset..offset+MAX_NUM) {
                    val aL = LargeNumber.parseNumber(a.toString(), 10)
                    val bL = LargeNumber.parseNumber(b.toString(), 10)
                    if (aL.wordCount() != bL.wordCount()) {
                        continue
                    }
                    assertEquals(aL.times(bL).toString(), aL.karatsuba(bL).toString())
                }
            }
        }
    }
}
