import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Thomas Brocken on 13-9-2017.
 */
public abstract class AddSubtract {
    ArrayList<Integer> result;
    ArrayList<Integer> x;
    ArrayList<Integer> y;
    int base;
    Sign sign = Sign.POSITIVE;

    public LargeNumber execute(LargeNumber n1, LargeNumber n2) {
        result = new ArrayList<>();
        this.x = new ArrayList<>(n1.getDigits());
        this.y = new ArrayList<>(n2.getDigits());
        while (x.size() > y.size()) {
            y.add(0, 0);
        }
        while (x.size() < y.size()) {
            x.add(0, 0);
        }
        base = n1.getBase();
        solve(x.size() - 1, false); // Solve the operation
        Collections.reverse(result); // Switch around because we scanned from right to left
        return new LargeNumber(base, sign, result);
    }

    abstract void solve(int i, boolean c);
}
