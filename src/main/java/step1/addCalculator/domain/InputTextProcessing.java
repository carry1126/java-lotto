package step1.addCalculator.domain;

import step1.addCalculator.util.ErrorMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * inputText의 값을 컨트롤하는 객체
 */
public class InputTextProcessing {

    private static final String CUSTOM_STRING_LIST = "//(.)\n(.*)";

    private static String inputText;
    private static String splitValue = ",|:";


    public InputTextProcessing(String inputText) {
        this.inputText = inputText;
    }

    public List<Integer> runProcessing() {
        return changeStrToIntList();
    }

    public boolean isNullOrEmptyCheck() {
        if (inputText == null || "".equals(inputText)) {
            return false;
        }

        return true;
    }

    private List<Integer> changeStrToIntList() {
        List<Integer> returnList = new ArrayList<>();
        List<String> beforeList = new ArrayList<>();

        Matcher m = Pattern.compile(CUSTOM_STRING_LIST).matcher(inputText);

        if (m.find()) {
            splitValue = splitValue + "|" + m.group(1);
            beforeList = makeStringArr(m.group(2));

            return changeStrToint(beforeList);
        }

        beforeList = makeStringArr(inputText);

        return changeStrToint(beforeList);
    }

    private List<String> makeStringArr(String paramValue) {
        String[] returnArr = paramValue.split(splitValue);
        return Arrays.asList(returnArr);
    }

    private List<Integer> changeStrToint(List<String> paramList) {
        List<Integer> returnList = new ArrayList<>();

        try {
            for (String str : paramList) {
                returnList.add(valueCheck(str));
            }

            return returnList;
        } catch (NumberFormatException e) {
            System.out.println(e.toString() + " : " + ErrorMessage.getNUMBERFORMATEXCEPTION());
        }

        return returnList;
    }

    private Integer valueCheck(String paramValue) {
        return isNegative(isNumber(paramValue));
    }

    private Integer isNegative(Integer paramValue) {
        if (paramValue < 0) {
            throw new RuntimeException(ErrorMessage.getNEGATIVEEXCEPTION());
        }

        return paramValue;
    }

    private Integer isNumber(String paramValue) {
        try {
            return Integer.parseInt(paramValue);
        } catch (NumberFormatException e) {
            System.out.println(e.toString() + " : " + ErrorMessage.getNUMBERFORMATEXCEPTION());
        }

        return Integer.parseInt(paramValue);
    }
}
