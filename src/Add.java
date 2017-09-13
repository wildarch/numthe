/**
 * Created by Thomas Brocken on 11-9-2017.
 */

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class that implements addition
 */
public class Add extends AddSubtract{

    /**
     * Solves the addition recursively
     * @param i current index
     * @param c current carry
     */
    @Override
    void solve(int i, boolean c) {
        if (i < 0) { // Check if we're done
            if (c) result.add(1); // If done while having carry, add a one
            return;
        }
        // Add two digits together, adding the carry if needed
        int sum = c ? x.get(i) + y.get(i) + 1 : x.get(i) + y.get(i);
        // Set carry if sum is larger or equal to base
        c = sum >= base;

        // If the carry is set, subtract the base
        if (c) sum -= base;
        result.add(sum);
        solve(i - 1, c);
    }


}
