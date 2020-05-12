package slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a string S and a string T, find the minimum window in S which will
 * contain all the characters in T in complexity O(n).
 *
 * Example:
 *
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * Note:
 *
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 *
 * Author:   softtwilight
 * Date:     2020/05/11 23:03
 */
public class _76_H_Minimum_Window_Substring {
    private static final _76_H_Minimum_Window_Substring instance = new _76_H_Minimum_Window_Substring();

    public static void main(String[] args) {
        String S = "ADOBECODEBANC", T = "ABC";
        String S2 = "bba", T2 = "ab";
        System.out.println(instance.minWindow2(S, T));
        System.out.println(instance.minWindow2(S2, T2));
    }


    /**
     * 15 ms  +	39.9 MB
     */
    public String minWindow2(String s, String t) {

        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int lo = 0, hi = 0;

        // count是满足窗口的条件
        int count = t.length();
        int minLength = s.length() + 1;
        int begin = -1, end = -1;
        while (hi < s.length()) {
            char cur = s.charAt(hi);
            if (map.containsKey(cur)) {
                map.put(cur, map.get(cur) - 1);

                // 也就是刚刚减去的cur，是必须的（ 需要2个a， 那么第三个a就不是必须的）
                if (map.get(cur) >= 0) {
                    count--;
                }
            }

            // 当满足条件时， 缩小左边窗口。
            while (count == 0) {
                if (minLength > hi - lo + 1) {
                    minLength = hi - lo + 1;
                    begin = lo;
                    end = hi;
                }
                char toRemove = s.charAt(lo);
                if (map.containsKey(toRemove)) {
                    map.put(toRemove, map.get(toRemove) + 1);
                    // 同--的地方，当窗口释放的char是必须的， count++
                    if (map.get(toRemove) >= 1) {
                        count++;
                    }
                }
                lo++;
            }
            hi++;
        }
        return begin == -1 ? "" : s.substring(begin, end + 1);
    }

    /**
     * 没解出来，思路是ok的，但是实现上还是有问题，
     * 太多分支导致思维量太大，第二是用遍历map来决定循环条件
     *
     */
    public String minWindow(String s, String t) {

        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return "";
        }
        Map<Character, Integer> tc = new HashMap<>();
        for (char c : t.toCharArray()) {
            tc.put(c, tc.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> memo = new HashMap<>();
        int lo = 0;
        int count = 0;
        int result = s.length() + 1;
        int begin = -1, end = -1;
        for (int i = 0; i < s.length(); i++) {

            if (tc.containsKey(s.charAt(i))) {
                count++;
                memo.put(s.charAt(i), memo.getOrDefault(s.charAt(i), 0) + 1);
                while (count >= t.length()
                        && memo.getOrDefault(s.charAt(lo), 1) > memo.getOrDefault(s.charAt(lo), 0)) {
                    if (memo.containsKey(s.charAt(lo))) {
                        if (memo.entrySet().stream()
                                .allMatch(entry -> entry.getValue() >= tc.getOrDefault(entry.getKey(), 0))
                        ) {
                            if (result > (i - lo + 1)) {
                                result = i - lo + 1;
                                begin = lo;
                                end = i + 1;
                            }
                        }

                        int c = memo.get(s.charAt(lo));
                        if (count == 1) {
                            memo.remove(s.charAt(lo));
                        } else {
                            memo.put(s.charAt(lo), c - 1);
                        }
                        count--;

                    }
                    lo++;
                }
            } else if (memo.isEmpty()) {
                lo++;
            }
        }
        if (begin == -1) {
            return "";
        }
        return s.substring(begin, end);
    }
}
