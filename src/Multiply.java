import java.util.ArrayList;
import java.util.Collections;
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
        //result.add(6);
        //sign = Sign.POSITIVE;
        //base = 10;
        //Collections.reverse(result); // Switch around because we scanned from right to left
        return new LargeNumber(base, sign, result);
    }

    /**
     * Solves the multiplication with the algorithm from lecture notes
     */

    private void solve() {
        System.out.println("x_size " + x.size());
        System.out.println("y_size " + y.size());
        for (int i = 0; i <= x.size() * y.size(); i++) {
            result.add(0);
            System.out.println("added 0");
        }

        for (int i = 0; i <= x.size() - 1; i++) {
            int carry = 0;
            for (int j = 0; j <= y.size() - 1; j++) {
                int t = result.get(result.size() - 1 - (i + j)) + x.get(x.size() - 1 - i) * y.get(y.size() - 1 - j) + carry;
                System.out.println("t: " + t);
                carry = t / base;
                System.out.println("carry: " + carry);
                result.set(result.size() - 1 - (i + j), t - carry * base);
                System.out.println("On index " + (result.size() - 1 - (i + j)) + " we set " + (t - carry * base));
                System.out.println("Index " + (result.size() - 1 - (i + j)) + " has value " + result.get(result.size() - 2 - (i + j)));
            }
            result.set(result.size() - i - y.size() - 1, carry);
        }

        if (x.getSign() == y.getSign()) {
            this.sign = Sign.POSITIVE;
        }
        else if (x.getSign() != y.getSign()) {
            this.sign = Sign.NEGATIVE;
        }
    }
}

