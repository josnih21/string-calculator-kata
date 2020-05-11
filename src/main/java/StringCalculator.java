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
        List<Double> numbersListParsed = numbersList.stream()
                .map(s -> Double.parseDouble(s))
                .collect(Collectors.toList());
        int sum = 0;
        sum += addNumbers(numbersListParsed);
        return sum;
    }

    public static double addNumbers(List<Double> numbers) {
        if (numbers.size() == 1) {
            return numbers.get(0);
        }
        double sum = 0;
        sum += numbers.get(0);
        numbers.remove(0);
        return sum += addNumbers(numbers);
    }
}