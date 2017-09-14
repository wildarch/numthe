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
     *
     * @param index
     *         current index
     * @param carry
     *         current carry
     */
    @Override
    void solve(int index, boolean carry) {
        // Check if we're done
        if (index < 0) {
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

                return;
            }

            return;
        }

        // SUBTRACT two digits, including the carry if needed
        int sum = carry ? x.get(index) - y.get(index) - 1 : x.get(index) - y.get(index);

        // Set carry if sum is negative
        carry = sum < 0;

        // If the carry is set, add the base to the sum
        if (carry) {
            sum += base;
        }

        result.add(sum);
        solve(index - 1, carry);
    }
}
