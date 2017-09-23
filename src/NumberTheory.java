import java.io.*;
import java.util.stream.Stream;


/**
 * @author Ruben Schellekens
 */
public class NumberTheory {

    public static void main(String[] args) {
        try {
            //Stream<String> input = Files.lines(Paths.get("example-karatsuba.txt"));
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            Stream<String> input = in.lines().limit(4);
            InputParser parser = new InputParser(input);
            System.out.println(parser);
            LargeNumber answer = parser.getOperation().execute(parser.getX(), parser.getY());
            System.out.println("answer: " + answer + " (computed)");
            System.out.println(Tracker.stats());

        }
        catch (InputParser.ParseException e) {
            System.err.println("Could not parse: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
