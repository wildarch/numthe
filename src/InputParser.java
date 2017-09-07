import com.sun.org.apache.xpath.internal.operations.Mult;
import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Created by Daan de Graaf on 07/09/17.
 */
public class InputParser {

    private int radix;
    private Operation operation;
    private String x;
    private String y;
    private String answer;

    public InputParser(Stream<String> input) throws ParseException {
        input
            .map(String::trim)
            .filter(s -> !s.isEmpty())
            .map(s -> s.split(" "))
            .forEach(parts -> {
                switch(parts[0]) {
                    case "#":
                        if(parts.length > 2 && parts[1].equals("[answer]")) {
                            answer = parts[2];
                        }
                        // Do nothing for ordinary comments
                        break;
                    case "[radix]":
                        radix = Integer.parseInt(parts[1]);
                        break;
                    case "[add]":
                        operation = Operation.Add;
                        break;
                    case "[subtract]":
                        operation = Operation.Subtract;
                        break;
                    case "[multiply]":
                        operation = Operation.Multiply;
                        break;
                    case "[karatsuba]":
                        operation = Operation.Karatsuba;
                        break;
                    case "[x]":
                        x = parts[1];
                        break;
                    case "[y]":
                        y = parts[1];
                        break;
                    default:
                        throw new ParseException("Could not parse line: `" + String.join(" ", parts) + "`");
                }
            });
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

    public static class ParseException extends RuntimeException {
        private String message;

        public ParseException(String msg) {
            message = msg;
        }
    }

    public static enum Operation {
        Add,
        Subtract,
        Multiply,
        Karatsuba
    }
}
