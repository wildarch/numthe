import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.stream.Stream;


/**
 * @author Ruben Schellekens
 */
public class NumberTheory {

    public static void main(String[] args) {
        Stream<String> input = null;
        if (args.length == 2 && args[0].equals("-f")) {
            String filename = args[1];
            try {
            input = Files.lines(Paths.get(filename)); }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (args.length != 0) {
            throw new InputMismatchException("Invalid arguments");
        } else if (args.length == 0) {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            input = in.lines().limit(4);
        }
        try {
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
