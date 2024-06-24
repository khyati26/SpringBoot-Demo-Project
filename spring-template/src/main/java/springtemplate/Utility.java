package springtemplate;

import java.util.regex.Pattern;

public class Utility {

    private static Pattern numberPattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    private static Pattern alphabetPattern = Pattern.compile("[a-zA-Z]+");
    private static Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    private static Pattern phNoPattern = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        return numberPattern.matcher(str).matches();
    }

    public static boolean isAllAlphabet(String str) {
        if (str == null) {
            return false;
        }
        return alphabetPattern.matcher(str).matches();
    }

    public static boolean isEmail(String str) {
        if (str == null) {
            return false;
        }
        return emailPattern.matcher(str).matches();
    }

    public static boolean isPhNo(String str) {
        if (str == null) {
            return false;
        }
        return phNoPattern.matcher(str).matches();
    }
}
