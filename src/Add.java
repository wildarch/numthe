import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class that implements addition
 *
 * @author Thomas Brocken
 */
public class Add extends AddSubtract {

    /**
     * Solves the addition
     */
    List<Integer> solve(List<Integer> x, List<Integer> y) {
        ArrayList<Integer> result = new ArrayList<>();
        boolean carry = false;
        for (int i = x.size() - 1; i >= 0; i--) {
            // ADD two digits together, adding the carry if needed
            int sum = carry ? x.get(i) + y.get(i) + 1 : x.get(i) + y.get(i);

            Tracker.addition();
            if (carry) {
                Tracker.addition();
            }

            // Set carry if sum is larger or equal to base
            carry = sum >= base;

            // If the carry is set, subtract the base
            if (carry) {
                sum -= base;
            }
            result.add(sum);
        }


        if (carry) {
            // If done while having carry, add a one
            result.add(1);
        }

        return result;
    }

}
