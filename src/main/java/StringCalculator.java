import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class StringCalculator {
    static String[] numbers;
    static String delimiter = ",";

    public static double add(String text) {
        if (text.isEmpty()) {
            return 0;
        }
        boolean doesntContainsComma = !text.contains(",");
        boolean doesntContainsNewLines = !text.contains("\n");
        if (doesntContainsComma && doesntContainsNewLines) {
            return Double.parseDouble(text);
        }

        if (text.contains("//")) {
            delimiter = text.charAt(2) + "";
            String replacedText = text.replaceAll("//.\n", "");
            numbers = replacedText.split("[.\\n]");
        } else {
            numbers = text.split(",|\\n");
        }

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