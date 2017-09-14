import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Thomas Brocken
 */
public abstract class AddSubtract {

    List<Integer> result;
    List<Integer> x;
    List<Integer> y;
    int base;
    Sign sign = Sign.POSITIVE;

    public LargeNumber execute(LargeNumber n1, LargeNumber n2) {
        result = new ArrayList<>();
        this.x = new ArrayList<>(n1.getDigits());
        this.y = new ArrayList<>(n2.getDigits());

        // Make sure the numbers have the same word size.
        while (x.size() > y.size()) {
            y.add(0, 0);
        }
        while (x.size() < y.size()) {
            x.add(0, 0);
        }

        base = n1.getBase();
        solve();

        // Switch around because we scanned from right to left
        Collections.reverse(result);

        return new LargeNumber(base, sign, result);
    }

    abstract void solve();
}
