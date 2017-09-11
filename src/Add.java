/**
 * Created by Thomas Brocken on 11-9-2017.
 */

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class that implements addition
 */
public class Add implements Operation {
    ArrayList<Integer> result;
    LargeNumber x;
    LargeNumber y;
    int base;

    @Override
    public LargeNumber execute(LargeNumber x, LargeNumber y) {
        result = new ArrayList<>();
        this.x = x;
        this.y = y;
        base = x.getBase();
        solve(x.size() - 1, false); // Solve the addition, at the rightmost side of the number
        Collections.reverse(result); // Switch around because we scanned from right to left
        return new LargeNumber(base, Sign.POSITIVE, result);
    }

    /**
     * Solves the addition recursively
     * @param i current index
     * @param c current carry
     */
    private void solve(int i, boolean c) {
        if (i < 0) { // Check if we're done
            if (c) result.add(1); // If done while having carry, add a one
            return;
        }
        // Add two digits together, adding the carry if needed
        int sum = c ? x.get(i) + y.get(i) + 1 : x.get(i) + y.get(i);
        // Set carry if sum is larger or equal to base
        c = sum >= base ? true : false;

        // If the carry is set, subtract the base
        if (c) sum -= base;
        result.add(sum);
        solve(i - 1, c);
    }


}
