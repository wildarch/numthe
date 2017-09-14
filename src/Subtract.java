import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class that implements addition
 *
 * @author Thomas Brocken
 */
public class Subtract extends AddSubtract {

    /**
     * Solves the addition recursively
     */
    @Override
    void solve() {
        // Check if we're done
        boolean carry = false;
        for (int i = x.size() - 1; i >= 0; i--) {

            // SUBTRACT two digits, including the carry if needed
            int sum = carry ? x.get(i) - y.get(i) - 1 : x.get(i) - y.get(i);

            // Set carry if sum is negative
            carry = sum < 0;

            // If the carry is set, add the base to the sum
            if (carry) {
                sum += base;
            }

            result.add(sum);
        }

        if (carry) {
            // If the carry is set, we have to subtract our result from the smallest number that has one digit more,
            // to simulate counting below zero
            // ( 100 - 200 = "900", do 1000-900 = "100", add sign: "-100")
            List<Integer> l = new ArrayList<>(Collections.singletonList(1));
            for (int j = 0; j < x.size(); j++) {
                l.add(0);
            }

            Collections.reverse(result);
            result = new ArrayList<>(
                    new LargeNumber(base, Sign.POSITIVE, l).minus(
                            new LargeNumber(base, Sign.POSITIVE, result)
                    ).getDigits()
            );

            Collections.reverse(result);
            sign = Sign.NEGATIVE;
        }
    }
}
