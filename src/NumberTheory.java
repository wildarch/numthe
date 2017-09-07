import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author Ruben Schellekens
 */
public class NumberTheory {

    public static void main(String[] args) {
        InputStream stream = null;
        try {
            Stream<String> input = Files.lines(Paths.get("example-test.txt"));
            InputParser parser = new InputParser(input);
            System.out.println(parser);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InputParser.ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
