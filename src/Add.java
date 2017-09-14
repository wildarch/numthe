/**
 * Class that implements addition
 *
 * @author Thomas Brocken
 */
public class Add extends AddSubtract {

    /**
     * Solves the addition recursively
     *
     * @param index
     *         current index
     * @param carry
     *         current carry
     */
    @Override
    void solve(int index, boolean carry) {
        // Check if we're done
        if (index < 0) {
            if (carry) {
                // If done while having carry, add a one
                result.add(1);
            }

            return;
        }

        // ADD two digits together, adding the carry if needed
        int sum = carry ? x.get(index) + y.get(index) + 1 : x.get(index) + y.get(index);

        // Set carry if sum is larger or equal to base
        carry = sum >= base;

        // If the carry is set, subtract the base
        if (carry) {
            sum -= base;
        }
        result.add(sum);

        solve(index - 1, carry);
    }
}
