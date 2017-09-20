import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * @author Ruben Schellekens
 */

@RunWith(Suite::class)
@Suite.SuiteClasses(
        ArithmeticTest::class,
        LargeNumberTest::class
)
object NumberTheoryTestSuite