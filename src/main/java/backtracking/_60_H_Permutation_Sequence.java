package backtracking;

/**
 * Author:   softtwilight
 * Date:     2021/01/08 21:11
 *
 * https://leetcode.com/problems/permutation-sequence/
 */
public class _60_H_Permutation_Sequence {
    private static final _60_H_Permutation_Sequence instance = new _60_H_Permutation_Sequence();

    public static void main(String[] args) {
        System.out.println(instance.getPermutation(6, 20));
    }

    /**
     *
     */
    public String getPermutation(int n, int k) {
        boolean[] memo = new boolean[n + 1];
        backtrack(memo, new StringBuffer(), k);
        return result;
    }

    String result = "";
    int nth = 0;
    boolean finish = false;

    private void backtrack(boolean[] memo, StringBuffer sb, int k) {
        if (finish) return;
        if (sb.length() == memo.length - 1) {
            nth++;
            if (nth == k) {
                result = sb.toString();
                finish = true;
            }
        }
        for (int i = 1; i < memo.length; i++) {
            if (!memo[i]) {
                memo[i] = true;
                sb.append(i);
                backtrack(memo, sb, k);
                memo[i] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

}
