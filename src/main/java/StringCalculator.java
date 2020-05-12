import java.util.*;
import java.util.stream.Collectors;


public class StringCalculator {
    static String delimiter = ",";
    public static String add(String text) {
        String[] numbers;
        String negativeList = "";
        if (text.isEmpty()) {
            return "0";
        }
        boolean doesntContainsComma = !text.contains(",");
        if (doesntContainsComma && !text.contains("\n")) {
            return Float.parseFloat(text)+"";
        }

        if(text.endsWith(delimiter)){
            return "Number expected but EOF found";
        }
        if (text.contains("//")) {
            delimiter = text.substring(2,text.indexOf("\n"));
            String replacedText = text.replaceAll("//+"+delimiter+"\n", "");
            numbers = replacedText.split("["+delimiter+"\\n]");
        } else {
            numbers = text.split(delimiter+"|\\n");
        }

        List<String> numbersList = Arrays.asList(numbers);
        List<Float> numbersListParsed = numbersList.stream()
                .map(Float::parseFloat)
                .collect(Collectors.toList());
        SortedSet<Float> sortedList = new TreeSet<>(numbersListParsed);
        if(sortedList.first() < 0){
            for (float number:numbersListParsed) {
                if(number < 0){
                    negativeList +=number+",";
                }
            }
            return "Negative numbers not allowed : "+negativeList;
        }
        float sum = 0;
        sum += addNumbers(numbersListParsed);
        return sum+"";
    }


    public static double addNumbers(List<Float> numbers) {
        if (numbers.size() == 1) {
            return numbers.get(0);
        }
        float sum = 0;
        sum += numbers.get(0);
        numbers.remove(0);
        return sum += addNumbers(numbers);
    }
}