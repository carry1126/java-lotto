import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringAddCalculator {
    public int splitAndSum(String input) {
        int sum = 0;
        if (input == null) {
            return sum = 0;
        }
        if (input.isEmpty()) {
            return sum = 0;
        }
        if (input.length() == 1) {
            return sum = Integer.parseInt(input);
        }

        if (input.length() > 1 && input.contains(",")) {
            if (input.contains(":")) {
                String[] tokens = input.split(",|:");
                for (String num : tokens) {
                    sum += Integer.parseInt(num);
                }
                return sum;
            }

            String[] numbers = input.split(",");
            for (String num : numbers) {
                sum += Integer.parseInt(num);
            }
            return sum;
        }

        Matcher m = Pattern.compile("//(.)\n(.*)").matcher(input);
        if (m.find()) {
            String customDelimiter = m.group(1);
            String[] tokens = m.group(2).split(customDelimiter);
            for (String num:tokens) {
                sum += Integer.parseInt(num);
            }
            return sum;
        }

        return 0;
    }
}
