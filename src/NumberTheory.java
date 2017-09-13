import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


/**
 * @author Ruben Schellekens
 */
public class NumberTheory {

    public static void main(String[] args) {
        try {
            Stream<String> input = Files.lines(Paths.get("example-subtract.txt"));
            InputParser parser = new InputParser(input);
            System.out.println(parser);
            LargeNumber answer = parser.getOperation().execute(parser.getX(), parser.getY());
            System.out.println("answer: "+answer + " (computed)");
        } catch (InputParser.ParseException e) {
            System.err.println("Could not parse: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ResultMismatchException extends RuntimeException {
        ResultMismatchException(String message) {
            super(message);
        }
    }
}
