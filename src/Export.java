import javax.sound.midi.Track;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * Runs the test cases in the file given as argument and outputs a CSV with the results
 *
 * Author: Daan de Graaf
 */
public class Export {
    public static void main(String[] args) {
        if(args.length == 0) {
            System.out.println("Missing argument <file.txt>");
            return;
        }

        List<String> results = new ArrayList<>();
        results.add("base, x, y, operation, answer, additions/subtractions, multiplications");

        try {
            List<String> input = Files.lines(Paths.get(args[0])).collect(Collectors.toList());
            List<List<String>> examples = new ArrayList<>();
            List<String> ex = new ArrayList<>();
            for (String line : input) {
                if (line.isEmpty()) {
                    examples.add(ex);
                    ex = new ArrayList<>();
                }
                else {
                    ex.add(line);
                }
            }

            for (List<String> example : examples) {
                try {
                    InputParser parser = new InputParser(example.stream());
                    LargeNumber answer = parser.getOperation().execute(parser.getX(), parser.getY());
                    results.add(
                            "" + parser.getX().getBase() + "," +
                            parser.getX() + "," +
                            parser.getY() + "," +
                            parser.getOperation() + "," +
                            answer + "," +
                            (Tracker.getAdditions() + Tracker.getSubtractions()) + "," +
                            (Tracker.getMultiplications())
                    );
                } catch (InputParser.ParseException e) {
                    if (!example.stream().allMatch(s -> s.startsWith("#"))) {
                        System.err.println("WARN: Could not parse example:" + e);
                    }
                }
            }

            for (String res : results) {
                System.out.println(res);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
