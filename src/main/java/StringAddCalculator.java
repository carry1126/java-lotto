import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringAddCalculator {

    public static final String COMMA = ",";
    public static final int NUM_LENGTH = 1;
    public static final String COLON = ":";
    public static final Pattern numPattern = Pattern.compile("(^[0-9]$)");
    public static final Pattern customPattern = Pattern.compile("//(.)\n(.*)");
    public static final String COMMA_COLON = ",|:";

    public int splitAndSum(String input) {
        int sum = 0;
        if (input == null || input.isEmpty()) {
            return sum = 0;
        }
        if (input.length() == 1) {
            return sum = Integer.parseInt(input);
        }
        if (input.length() > NUM_LENGTH && input.contains(COMMA)) {
            return calculatorColonCommaDelimitor(input);
        }
        Matcher m = customPattern.matcher(input);
        if (m.find()) {
            return calculateMatcherSum(m);
        }
        return sum;
    }

    private int calculatorColonCommaDelimitor(String input) {
        if (input.contains(COLON)) {
            String[] tokens = input.split(COMMA_COLON);
            return getSum(tokens);
        }

        return commaDelimitor(input);
    }

    private int commaDelimitor(String input) {
        int sum = 0;
        String[] numbers = input.split(COMMA);
        for (String num : numbers) {
            notNumberException(num);
            int number = Integer.parseInt(num);
            minusException(number);
            sum += number;
        }
        return sum;
    }

    private void notNumberException(String num) {
        Matcher m = numPattern.matcher(num);
        if (!m.find()) {
            throw new NumberFormatException("숫자 이외의 값이 전달되었습니다.");
        }
    }

    private void minusException(int num) {
        if (num < 0) {
            throw new RuntimeException("음수가 전달되었습니다.");
        }
    }

    private int calculateMatcherSum(Matcher m) {
        String customDelimiter = m.group(1);
        String[] tokens = m.group(2).split(customDelimiter);
        return getSum(tokens);
    }

    private int getSum(String[] tokens) {
        int sum = 0;
        for (String num : tokens) {
            sum += Integer.parseInt(num);
        }
        return sum;
    }
}
