package slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Author:   softtwilight
 * Date:     2020/05/10 22:23
 */
public class _3_M_Longest_Substring_No_Repeating {
    private static final _3_M_Longest_Substring_No_Repeating instance = new _3_M_Longest_Substring_No_Repeating();

    public static void main(String[] args) {
        String input = "11111";
        System.out.println(instance.lengthOfLongestSubstring(input));
    }


    /**
     * 基于set的滑动窗口
     * 7 ms	+ 39.4 MB
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }
        Set<Character> memo = new HashSet<>();
        int lo = 0, result = 0;
        for (int i = 0; i < s.length(); i++) {
            while (memo.contains(s.charAt(i))) {
                memo.remove(s.charAt(lo++));
            }
            memo.add(s.charAt(i));
            result = Math.max(result, memo.size());
        }
        return result;
    }
}
