import java.util.stream.Stream;

/**
 * Created by Daan de Graaf on 07/09/17.
 */
public class InputParser {

    private int radix = 0;
    private Operation operation;
    private LargeNumber x;
    private LargeNumber y;
    private LargeNumber answer;

    public InputParser(Stream<String> input) throws ParseException {
        input
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(s -> s.split(" "))
                .forEach(parts -> {
                    switch (parts[0]) {
                        case "#":
                            if (parts.length > 2 && parts[1].equals("[answer]")) {
                                if (radix == 0) {
                                    throw new ParseException("Found [answer], but no [radix] yet");
                                }
                                answer = LargeNumber.parseNumber(parts[2], radix);
                            }
                            // Do nothing for ordinary comments
                            break;
                        case "[radix]":
                            radix = Integer.parseInt(parts[1]);
                            break;
                        case "[add]":
                            operation = Operation.ADD;
                            break;
                        case "[subtract]":
                            operation = Operation.SUBTRACT;
                            break;
                        case "[multiply]":
                            operation = Operation.MULTIPLY;
                            break;
                        case "[karatsuba]":
                            operation = Operation.KARATSUBA;
                            break;
                        case "[x]":
                            if (radix == 0) {
                                throw new ParseException("Found [x], but no [radix] yet");
                            }
                            x = LargeNumber.parseNumber(parts[1], radix);
                            break;
                        case "[y]":
                            y = LargeNumber.parseNumber(parts[1], radix);
                            if (radix == 0) {
                                throw new ParseException("Found [y], but no [radix] yet");
                            }
                            break;
                        default:
                            throw new ParseException("Could not parse line: `" + String.join(" ", parts) + "`");
                    }
                });
        if (radix == 0) {
            throw new ParseException("No radix in input!");
        }
        if (operation == null) {
            throw new ParseException("No operation in input");
        }
        if (x == null) {
            throw new ParseException("No x in input");
        }
        if (y == null) {
            throw new ParseException("No y in input");
        }
        // Answer is optional
    }

    @Override
    public String toString() {
        String s = "Parsed data:\n";
        s += "radix: " + radix + "\n";
        s += "operation: " + operation + "\n";
        s += "x: " + x + "\n";
        s += "y:" + y + "\n";
        if (answer != null) {
            s += "answer: " + answer + "\n";
        }
        return s;
    }

    public LargeNumber getX() {
        return this.x;
    }

    public LargeNumber getY() {
        return this.y;
    }

    public LargeNumber getAnswer() {
        return this.answer;
    }

    public Operation getOperation() {
        return this.operation;
    }

    static class ParseException extends RuntimeException {

        ParseException(String message) {
            super(message);
        }
    }
}
