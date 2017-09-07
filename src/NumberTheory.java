/**
 * @author Ruben Schellekens
 */
public class NumberTheory {

    public static void main(String[] args) {
        LargeNumber num = new LargeNumber(4, Sign.NEGATIVE, 1, 0, 0, 0, 2, 3, 1, 0, 0, 1);

        LargeNumber parsed = LargeNumber.parseNumber("1000231001", 4);

        System.out.println(num);
        System.out.println(parsed);
    }
}
