import kotlin.Pair;

import java.util.List;

/**
 * Created by Thomas Brocken on 20-9-2017.
 */
public class Karatsuba {
    /**
     * Performs multiplication using the karatsuba method.
     * @param x first number
     * @param y second number
     * @return answer
     */
    public LargeNumber execute(LargeNumber x, LargeNumber y) {
        if (x.getDigits().size() == 1) {
            return x.times(y);
        }
        // Compute the sign
        Sign sign = Sign.POSITIVE;
        if (x.getSign() != y.getSign()) {
            sign = Sign.NEGATIVE;
        }
        // First we split the input numbers in two, if the length is odd,
        // we add a leading zero and split.
        Pair<LargeNumber, LargeNumber> xsplit = x.split();
        Pair<LargeNumber, LargeNumber> ysplit = y.split();

        // Store the numbers
        LargeNumber x1 = xsplit.getFirst();
        LargeNumber x0 = xsplit.getSecond();
        LargeNumber y1 = ysplit.getFirst();
        LargeNumber y0 = ysplit.getSecond();

        // Set n to be the original word length
        int n = x1.wordCount()*2;

        // Because of Karatsuba: xy = x1y1*b^n+(x1y0+x0y1)*b^(n/2)+x0y0

        // Compute 3 half length multiplications
        LargeNumber z2 = x1.karatsuba(y1); // z2 = xhi * yhi
        LargeNumber z0 = x0.karatsuba(y0); // z0 = xlo * ylo
        LargeNumber z1 = (x1.plus(x0)).karatsuba(y1.plus(y0)); // z1 = (xhiylo + xloyhi)

        z1 = z1.minus(z0).minus(z2); // xhiylo + xloyhi = (xhi +xlo)(yhi +ylo) - xhiyhi - xloylo

        // Compose results by shifitng and additions
        z2.shift(n);
        z1.shift(n/2);
        LargeNumber result = z2.plus(z0).plus(z1);

        result.setSign(sign);

        return result;

    }
}
