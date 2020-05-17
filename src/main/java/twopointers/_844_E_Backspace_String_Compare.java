package twopointers;

/**
 * Author:   softtwilight
 * Date:     2020/05/17 23:26
 */
public class _844_E_Backspace_String_Compare {
    private static final _844_E_Backspace_String_Compare instance = new _844_E_Backspace_String_Compare();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     *
     */
    public boolean backspaceCompare(String S, String T) {
        int s = S.length() - 1;
        int t = T.length() - 1;
        int sBack = 0, tBack = 0;
        while (s >= 0 || t >= 0) {
            // 找到S的第一个元素
            while (s >= 0 && (S.charAt(s) == '#' || sBack > 0)) {
                sBack += S.charAt(s) == '#' ? 1 : -1;
                s--;
            }
            // 找到T的第一个元素
            while (t >= 0 && (T.charAt(t) == '#' || tBack > 0)) {
                tBack += T.charAt(t) == '#' ? 1 : -1;
                t--;
            }

            // 如果两个元素都存在（也就是不是""），判断相等与否（return early）
            if (s >= 0 && t >= 0 && S.charAt(s) != T.charAt(t)) {
                return false;
            }
            // 如果一个元素存在，一个不存在，return false
            if ((s >= 0) != (t >= 0))
                return false;
            t--;
            s--;
        }
        return true;
    }
}
