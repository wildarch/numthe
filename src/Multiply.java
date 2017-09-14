import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements (naive) multiplication
 *
 * @author Erik van Bennekum
 */
public class Multiply {

    List<Integer> result;
    Sign sign;
    LargeNumber x;
    LargeNumber y;
    int base;

    public LargeNumber execute(LargeNumber x, LargeNumber y) {
        result = new ArrayList<>();
        this.x = x;
        this.y = y;
        base = x.getBase();

        solve();
        //Collections.reverse(result); // Switch around because we scanned from right to left
        return new LargeNumber(base, sign, result);
    }

    /**
     * Solves the multiplication with the algorithm from lecture notes
     */
    private void solve() {
        for (int i = 0; i < x.size() * y.size(); i++) {
            result.add(0);
        }

        for (int i = 0; i < x.size() - 1; i++) {
            int carry = 0;
            for (int j = 0; j < y.size() - 1; j++) {
                int t = result.get(result.size() - 1 - (i + j)) + x.get(x.size() - 1 - i) * y.get(y.size() - 1 - j) + carry;
                carry = t / base;
                result.set(result.size() - 1 - (i + j), t - carry * base);
            }
            result.set(i + y.size(), carry);
        }

        if (x.getSign() == y.getSign()) {
            this.sign = Sign.POSITIVE;
        }
        else if (x.getSign() != y.getSign()) {
            this.sign = Sign.NEGATIVE;
        }
    }
}

