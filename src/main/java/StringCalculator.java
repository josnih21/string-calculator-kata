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
        if (doesntContainsComma && !text.contains("\n")) {
            return Double.parseDouble(text);
        }

        if (text.contains("//")) {
            delimiter = text.substring(2,text.indexOf("\n"));
            System.out.println(delimiter);
            String replacedText = text.replaceAll("//+"+delimiter+"\n", "");
            numbers = replacedText.split("["+delimiter+"\\n]");
            System.out.println(Arrays.toString(numbers));
        } else {
            numbers = text.split(delimiter+"|\\n");
            System.out.println(Arrays.toString(numbers));
        }

        List<String> numbersList = Arrays.asList(numbers);
        List<Double> numbersListParsed = numbersList.stream()
                .map(Double::parseDouble)
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