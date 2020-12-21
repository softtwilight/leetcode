package dp;

import java.util.Arrays;

/**
 * Author:   softtwilight
 * Date:     2020/12/21 18:31
 *
 * https://leetcode.com/problems/scramble-string/
 */
public class _87_H_Scramble_String {
    private static final _87_H_Scramble_String instance = new _87_H_Scramble_String();

    public static void main(String[] args) {
        String s1 = "abcd", s2 = "cbad";
        System.out.println(instance.isScramble(s1, s2));
    }

    /**
     * not solved
     *
     * 这个看似是dp的问题， 但是是两个string 的sub String， 要存储节点的话， 是N ^ 4
     * 不太现实。
     *
     * 实际上解答里都是暴力解的。 不用DP 其实就挺好解的了。
     * 贴了一个答案， 指数复杂度
     *
     */
    public boolean isScramble2(String s1, String s2) {

        int l = s1.length();
        return dp(0, l, s1, s2);
    }

    private boolean dp(int i, int j, String s1, String s2) {
        if (s1.substring(i, j).equals(s2.substring(i, j))) return true;
        boolean result = false;
        for (int k = i + 1; k < j; k++) {
            if (dp(0, k, s1, s2) && dp(k, j, s1, s2)) {
                result = true;
                break;
            }
            String convert = s1.substring(0, i) + s1.substring(k, j) + s1.substring(i, k);
            if (j < s1.length()) convert += s1.substring(j);
            if (dp(0, k, convert, s2) && dp(k, j, convert, s2)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }
        int[] count = new int[26];
        for (int i = 0;i < s1.length();i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        for (int i = 0;i < 26;i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        for (int i = 1;i < s1.length();i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) {
                return true;
            }
            if (isScramble(s1.substring(0, i), s2.substring(s1.length() - i, s1.length())) && isScramble(s1.substring(i), s2.substring(0, s1.length() - i))) {
                return true;
            }
        }
        return false;
    }
}
