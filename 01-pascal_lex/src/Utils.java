import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static Map<String,String> reserve = new HashMap<>();
    public static Map<String,String> calculator = new HashMap<>();
    static {
        reserve.put("BEGIN","Begin");
        reserve.put("END","End");
        reserve.put("FOR","For");
        reserve.put("IF","If");
        reserve.put("THEN","Then");
        reserve.put("ELSE","Else");
        calculator.put(":","Colon");
        calculator.put("+","Plus");
        calculator.put("*","Star");
        calculator.put(",","Comma");
        calculator.put("(","LParenthesis");
        calculator.put(")","RParenthesis");
        calculator.put(":=","Assign");
    }
    public static boolean isLetter(char ch){
        return Character.isLetter(ch);
    }
    public static boolean isDigit(char ch){
        return Character.isDigit(ch);
    }
    public static boolean isReserve(String token){
        return reserve.containsKey(token);
    }
    public static String toReserve(String token){
        return reserve.get(token);
    }
    public static String toCalculator(String token){
        return calculator.get(token);
    }
    public static int Atoi(String token){
        return Integer.parseInt(token);
    }
}
