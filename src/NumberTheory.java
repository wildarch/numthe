import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


/**
 * @author Ruben Schellekens
 */
public class NumberTheory {

    public static void main(String[] args) {
        LargeNumber answer = null;
        try {
            Stream<String> input = Files.lines(Paths.get("example-add.txt"));
            InputParser parser = new InputParser(input);
            System.out.println(parser);
            switch (parser.getOperation()) {
                case Add:
                    answer = parser.getX().plus(parser.getY());
                    break;
                case Subtract:
                    answer = parser.getX().minus(parser.getY());
                    break;
                case Multiply:
                    answer = parser.getX().times(parser.getY());
                    break;
                case Karatsuba:
                    answer = parser.getX().karatsuba(parser.getY());
                    break;

            }
            System.out.println("computed answer: "+answer);
            if (!answer.equals(parser.getAnswer())) {
                throw new ResultMismatchException("The computed result does not match the result in the input.");
            }
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
