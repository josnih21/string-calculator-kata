import java.util.*;
import java.util.stream.Collectors;


public class StringCalculator {
    String delimiter = ",";

    public String add(String text) {
        String errorMessage = "";
        if (text.isEmpty()) {
            return "0";
        }
        boolean doesntContainsComma = !text.contains(",");
        if (doesntContainsComma && !text.contains("\n")) {
            return Float.parseFloat(text) + "";
        }
        if (text.endsWith(delimiter)) {
            return "Number expected but EOF found";
        }
        if (text.contains(",,")) {
            int indexOfNumber = text.lastIndexOf(",,") + 1;
            errorMessage += "Number expected but ',' found at position " + indexOfNumber + ".";
            text = text.replaceAll(",,", ",");
        }
        String[] numbers = splittedArrayOfValues(text);
        List<Float> numbersListParsed = parseListOfString(numbers);
        String errors = handleError(errorMessage, numbersListParsed);
        if (errors != null) {
            return errors;
        }
        float sum = 0;
        sum += operateWithNumbers(numbersListParsed, Operation.ADDITION);
        return sum + "";
    }

    private List<Float> parseListOfString(String[] numbers) {
        List<String> numbersList = Arrays.asList(numbers);
        return numbersList.stream()
                .map(Float::parseFloat)
                .collect(Collectors.toList());
    }

    private String handleError(String errorMessage, List<Float> numbersListParsed) {
        if (!negativeNumbers(numbersListParsed).isEmpty()) {
            return "Negative numbers not allowed : " + negativeNumbers(numbersListParsed) + "\n" + errorMessage;
        } else if (!errorMessage.isEmpty()) {
            return errorMessage;
        }
        return null;
    }

    private double operateWithNumbers(List<Float> numbers, Operation operation) {
        if (numbers.size() == 1) {
            return numbers.get(0);
        }
        float sum = 0;
        switch (operation) {
            case ADDITION:
                sum += numbers.get(0);
                numbers.remove(0);
                sum += operateWithNumbers(numbers, operation);
                break;
            case MULTIPLICATION:
                sum = 1;
                sum *= numbers.get(0);
                numbers.remove(0);
                sum *= operateWithNumbers(numbers, operation);
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
            text = text.replaceAll("//+" + delimiter + "\n", "");
            return text.split("[" + delimiter + "\\n]");
        } else {
            return text.split("[,\\n]");
        }
    }
}