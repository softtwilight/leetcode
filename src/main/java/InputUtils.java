import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Author:   softtwilight
 * Date:     2020/05/06 21:07
 */
public class InputUtils {

    public static void main(String[] args) {
        String input = "[[1,2,3,4,5],[6,7,8,9,10],[11,12,13,14,15],[16,17,18,19,20],[21,22,23,24,25]]";
        convertArrayString(input);

        String questionName = "912. Sort an Array";
        String difficulty = "M";
        printClassName(questionName, difficulty);

    }

    public static void convertArrayString(String arrayStr) {
        arrayStr = arrayStr.replace('[', '{');
        arrayStr = arrayStr.replace('"', '\'');
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
