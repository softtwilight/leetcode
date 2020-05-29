import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Author:   softtwilight
 * Date:     2020/05/06 21:07
 */
public class InputUtils {

    public static void main(String[] args) {
        String input = "[[0,2],[5,10],[13,23],[24,25]][1,5],[8,12],[15,24],[25,26]]";
        convertArrayString(input);

        String questionName = "199. Binary Tree Right Side View";
        String difficulty = "M";
        printClassName(questionName, difficulty);
    }

    public static void convertArrayString(String arrayStr) {
        arrayStr = arrayStr.replace('[', '{');
        arrayStr = arrayStr.replace(']', '}');
        System.out.println(arrayStr);
    }

    public static void printClassName(String name, String difficulty) {
        name = name.replace(".", " " + difficulty);
        String result = Arrays.stream(name.split(" "))
                .collect(Collectors.joining("_", "_", ""));
        System.out.println(result);
    }
}
