package dp;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:   softtwilight
 * Date:     2020/12/25 22:24
 *
 * https://leetcode.com/problems/palindrome-partitioning/
 */
public class _131_M_Palindrome_Partitioning {
    private static final _131_M_Palindrome_Partitioning instance = new _131_M_Palindrome_Partitioning();

    public static void main(String[] args) {

        String input = "aaabbcccccccccccccccccdddd";
        instance.partition(input)
                .forEach(a -> {
//                    a.stream().forEach(System.out::println);
//                    System.out.println();
                });
    }

    /**
     *
     */
    public List<List<String>> partition(String s) {
        List<List<String>>[] memo = new ArrayList[s.length()];
        return dp(0, s, memo);
    }

    private List<List<String>> dp(int i, String s, List<List<String>>[] memo) {
        List<List<String>> result = new ArrayList<>();
        if (i == s.length()) {
            result.add(new ArrayList<>());
            return result;
        }

        if (memo[i] != null) return memo[i];
        for (int k = i; k < s.length(); k++) {
            if (isPalindrome(i, k, s)) {

                // see someone use backtracking here
                List<List<String>> subResult = dp(k + 1, s, memo);
                for (List<String> sub : subResult) {

                    // so much copy and new here.
                    List<String> newSub = new ArrayList<>(sub.size() + 1);
                    newSub.add(s.substring(i, k + 1));
                    newSub.addAll(sub);
                    result.add(newSub);
                }
            }
        }
        memo[i] = result;
        return result;
    }

    boolean isPalindrome(int lo, int hi, String s) {
        while (lo < hi) {
            if (s.charAt(lo++) != s.charAt(hi--)) return false;
        }
        return true;
    }
}
