import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Class that implements (naive) multiplication
 *
 * @author Erik van Bennekum
 */
public class Multiply {

    private List<Integer> result;
    private Sign sign;
    private LargeNumber x;
    private LargeNumber y;
    private int base;

    public LargeNumber execute(LargeNumber x, LargeNumber y) {
        result = new ArrayList<>();
        this.x = x;
        this.y = y;
        base = x.getBase();

        solve();
        return new LargeNumber(base, sign, result);
    }

    /**
     * Solves the multiplication with the algorithm from lecture notes
     * The z is replaced by integer list 'result'
     * The tracker keeps track of the amount of elementary operations
     *
     * Note: The zero index from the algorithm is the last index in the program
     */

    private void solve() {
        for (int i = 0; i <= x.size() * y.size(); i++) {
            result.add(0); // make result large enough to hold all digits
        }

        for (int i = 0; i <= x.size() - 1; i++) {
            int carry = 0;
            for (int j = 0; j <= y.size() - 1; j++) {
                int t = result.get(result.size() - 1 - (i + j)) + x.get(x.size() - 1 - i) * y.get(y.size() - 1 - j) + carry;
                Tracker.addition();
                Tracker.addition();
                Tracker.multiplication();
                carry = t / base; // A shift, so for free
                result.set(result.size() - 1 - (i + j), t - carry * base);
                Tracker.subtraction();
            }
            result.set(result.size() - i - y.size() - 1, carry);
        }

        // Determine sign for result
        if (x.getSign() == y.getSign()) {
            this.sign = Sign.POSITIVE;
        }
        else if (x.getSign() != y.getSign()) {
            this.sign = Sign.NEGATIVE;
        }
    }
}

