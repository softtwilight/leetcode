/**
 * Author:   softtwilight
 * Date:     2020/05/06 21:07
 */
public class InputUtils {

    public static void main(String[] args) {
        System.out.println(convertArrayString("[10,9,2,5,3,7,101,18]"));
    }

    public static String convertArrayString(String arrayStr) {
        arrayStr = arrayStr.replace('[', '{');
        arrayStr = arrayStr.replace(']', '}');
        return arrayStr;
    }
}
