import java.util.*;
import java.util.stream.Collectors;


public class StringCalculator {
    static String delimiter = ",";

    public static String add(String text) {
        String[] numbers;
        if (text.isEmpty()) { return "0"; }
        boolean doesntContainsComma = !text.contains(",");
        if (doesntContainsComma && !text.contains("\n")) { return Float.parseFloat(text) + ""; }
        if (text.endsWith(delimiter)) { return "Number expected but EOF found"; }
        numbers = splittedArrayOfValues(text);
        List<String> numbersList = Arrays.asList(numbers);
        List<Float> numbersListParsed = numbersList.stream()
                .map(Float::parseFloat)
                .collect(Collectors.toList());
        if (!negativeNumbers(numbersListParsed).isEmpty()) {
            return "Negative numbers not allowed : " + negativeNumbers(numbersListParsed);
        }
        float sum = 0;
        sum += addNumbers(numbersListParsed);
        return sum + "";
    }

    private static double addNumbers(List<Float> numbers) {
        if (numbers.size() == 1) { return numbers.get(0); }
        float sum = 0;
        sum += numbers.get(0);
        numbers.remove(0);
        return sum += addNumbers(numbers);
    }

    private static String negativeNumbers(List<Float> numbers) {
        List<Float> negativeList = numbers.stream().filter(x -> x < 0).collect(Collectors.toList());
        return negativeList.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    private static String[] splittedArrayOfValues(String text){
        if (text.contains("//")) {
            delimiter = text.substring(2, text.indexOf("\n"));
            String replacedText = text.replaceAll("//+" + delimiter + "\n", "");
            return replacedText.split("[" + delimiter + "\\n]");
        } else {
            return text.split(delimiter + "|\\n");
        }
    }
}