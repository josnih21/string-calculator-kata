import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class StringCalculator {


    public static double add(String text) {
        if (text.isEmpty()) {
            return 0;
        }
        boolean doesntContainsComma = !text.contains(",");
        if (doesntContainsComma) {
            return Double.parseDouble(text);
        }

        String[] numbers = text.split(",");
        List<String> numbersList = Arrays.asList(numbers);
        int sum = 0;
        for (String number : numbersList) {
            sum += Integer.parseInt(number);
        }
        return sum;
    }
}