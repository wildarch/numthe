/**
 * Created by Thomas Brocken on 11-9-2017.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Class that implements addition
 */
public class Subtract extends AddSubtract {

    /**
     * Solves the addition recursively
     * @param i current index
     * @param c current carry
     */
    @Override
    void solve(int i, boolean c) {
        if (i < 0) { // Check if we're done
            if (c) {
                // If the carry is set, we have to subtract our result from the smallest number that has one digit more, to simulate counting below zero
                // ( 100 - 200 = "900", do 1000-900 = "100", add sign: "-100")
                ArrayList<Integer> l = new ArrayList<>(Arrays.asList(1));
                for (int j=0; j < x.size(); j++) {
                    l.add(0);
                }
                Collections.reverse(result);
                result = new ArrayList<> (new LargeNumber(base, Sign.POSITIVE, l).minus(new LargeNumber(base, Sign.POSITIVE, result)).getDigits());
                Collections.reverse(result);
                sign = Sign.NEGATIVE;
                return;
            }
            return;
        }
        // Subtract two digits, including the carry if needed
        int sum = c ? x.get(i) - y.get(i) - 1 : x.get(i) - y.get(i);
        // Set carry if sum is negative
        c = sum < 0;

        // If the carry is set, add the base to the sum
        if (c) sum += base;
        result.add(sum);
        solve(i - 1, c);
    }


}
