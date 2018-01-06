import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by MaksymMikitiuk on 04.01.2018.
 */
public class test {
    public static void main(String[] args) throws IOException {
        String fileName = "B:\\Desktop\\1.tsv";
        while (true) {
            System.out.print("Filter: ");
            String f = new Scanner(System.in).next();
            Predicate<String> filter = Pattern
                    .compile("^.*" + f + ".*$")
                    .asPredicate();

            List<String> row = Files.lines(Paths.get(fileName))
                    .filter(filter)
                    .collect(Collectors.toList());

            row.forEach(System.out::println);
        }
    }
}
