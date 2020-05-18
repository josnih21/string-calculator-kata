import java.util.*;
import java.util.stream.Collectors;


public class StringCalculator {
    static String delimiter = ",";

    public String add(String text) {
        String[] numbers;
        if (text.isEmpty()) {
            return "0";
        }
        boolean doesntContainsComma = !text.contains(",");
        boolean doesntContainsNewLines = !text.contains("\n");
        if (doesntContainsComma && doesntContainsNewLines) {
            return Float.parseFloat(text) + "";
        }
        if (text.endsWith(delimiter)) {
            return "Number expected but EOF found";
        }
        return operateWithNumbers(text);
    }

    private String operateWithNumbers(String text) {
        String [] numbers = splittedArrayOfValues(text);
        List<Float> numbersListParsed = parseList(numbers);
        if (!negativeNumbers(numbersListParsed).isEmpty()) {
            return "Negative numbers not allowed : " + negativeNumbers(numbersListParsed);
        }
        float sum = 0;
        sum += addWithRecursion(numbersListParsed, Operation.ADDITION);
        return sum + "";
    }

    private List<Float> parseList(String[] numbers) {
        List<String> numbersList = Arrays.asList(numbers);
        return numbersList.stream()
                .map(Float::parseFloat)
                .collect(Collectors.toList());
    }

    private double addWithRecursion(List<Float> numbers, Operation operation) {
        if (numbers.size() == 1) {
            return numbers.get(0);
        }
        float sum = 0;
        switch (operation) {
            case ADDITION:
                sum += numbers.get(0);
                numbers.remove(0);
                sum += addWithRecursion(numbers, operation);
                break;
            case MULTIPLICATION:
                sum = 1;
                sum *= numbers.get(0);
                numbers.remove(0);
                sum *= addWithRecursion(numbers, operation);
        }
        return sum;
    }

    private String negativeNumbers(List<Float> numbers) {
        List<Float> negativeList = numbers.stream().filter(x -> x < 0).collect(Collectors.toList());
        return negativeList.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    private String[] splittedArrayOfValues(String text) {
        if (text.contains("//")) {
            delimiter = text.substring(2, text.indexOf("\n"));
            String replacedText = text.replaceAll("//+" + delimiter + "\n", "");
            return replacedText.split("[" + delimiter + "\\n]");
        } else {
            return text.split("[,\\n]");
        }
    }
}