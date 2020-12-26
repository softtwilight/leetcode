package dp;

import java.util.*;

/**
 * Author:   softtwilight
 * Date:     2020/12/26 21:08
 *
 * https://leetcode.com/problems/word-break/
 */
public class _139_M_Word_Break {
    private static final _139_M_Word_Break instance = new _139_M_Word_Break();

    public static void main(String[] args) {
        String s = "catsandog";
        String[] wordDict = {"cats","and","dog"};
        System.out.println(instance.wordBreak(s, Arrays.asList(wordDict)));
    }

    /**
     *
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        if (wordDict.isEmpty()) return false;
        words = new HashSet<>(wordDict);
        maxLength = wordDict.stream().map(String::length).max(Comparator.comparingInt(a -> a)).get();
        int[] memo = new int[s.length()];
        return dp(0, s, memo);
    }

    int maxLength = 0;
    Set<String> words;

    private boolean dp(int i, String s, int[] memo) {
        if (i == s.length()) return true;
        if (memo[i] != 0) return false;
        for (int j = i + 1; j <= s.length() && j <= i + maxLength; j++) {
            String sub = s.substring(i, j);
            if (words.contains(sub)) {
                if (dp(j, s, memo)) {
                    return true;
                }
            }

        }
        memo[i] = 1;
        return false;
    }
}
