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

        String input = "a";
        instance.partition(input)
                .forEach(a -> {
                    a.stream().forEach(System.out::println);
                    System.out.println();
                });
    }

    /**
     *
     */
    public List<List<String>> partition(String s) {
        List<List<String>>[] memo = new ArrayList[s.length()];
        return dp(0, s, memo);
//        return result;
//        return reverse(result);
    }

//    private List<List<String>> reverse(List<List<String>> re) {
//        for (int i = 0; i < re.size(); i++) {
//            List<String> src = re.get(i);
//            List<String> dest = new ArrayList<>(src.size());
//            for (int j = src.size() -1; j >= 0; j--) {
//                dest.add(src.get(j));
//            }
//            re.set(i, dest);
//        }
//        return re;
//    }

    private List<List<String>> dp(int i, String s, List<List<String>>[] memo) {
        List<List<String>> result = new ArrayList<>();
        if (i == s.length()) {
            result.add(new ArrayList<>());
            return result;
        }

        if (memo[i] != null) return memo[i];
        for (int k = i; k < s.length(); k++) {
            if (isPalindrome(i, k, s)) {
                List<List<String>> subResult = dp(k + 1, s, memo);
                for (List<String> sub : subResult) {
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
