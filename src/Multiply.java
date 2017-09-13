/**
 * Created by Erik van Bennekum on 12-9-2017
 * Based on a file by Thomas Brocken on 11-9-2017.
 */

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class that implements (naive) multiplication
 */
public class Multiply {
    ArrayList<Integer> result;
    Sign sign;
    LargeNumber x;
    LargeNumber y;
    int base;

    public LargeNumber execute(LargeNumber x, LargeNumber y) {
        result = new ArrayList<>();
        this.x = x;
        this.y = y;
        base = x.getBase();
        solve(); // Solve the addition, at the rightmost side of the number
        //Collections.reverse(result); // Switch around because we scanned from right to left
        return new LargeNumber(base, sign, result);
    }

    /**
     * Solves the multiplication with the algorithm from lecture notes
     */
    private void solve() {
        for (int h = 0; h < x.size()*y.size(); h++) {
            result.add(0);
        }
        for (int i = 0; i < x.size() - 1; i++) {
            int c = 0;
            for (int j = 0; j < y.size() - 1; j++) {
                int t = result.get(result.size() - 1 - (i + j)) + x.get(x.size() - 1 - i)*y.get(y.size() - 1 - j) + c;
                c = t / base;
                result.set(result.size() - 1 - (i + j), t - c * base);
            }
            result.set(i + y.size(), c);
        }
        if (x.getSign() == y.getSign()) {
            this.sign = Sign.POSITIVE;
        }
        else if (x.getSign() != y.getSign()) {
            this.sign = Sign.NEGATIVE;
        }
    }
}

