package backtracking;

import java.util.LinkedList;

/**
 * Author:   softtwilight
 * Date:     2021/01/08 21:11
 *
 * https://leetcode.com/problems/permutation-sequence/
 */
public class _60_H_Permutation_Sequence {
    private static final _60_H_Permutation_Sequence instance = new _60_H_Permutation_Sequence();

    public static void main(String[] args) {
        System.out.println(instance.getPermutation(6, 250));
        System.out.println(instance.getPermutation(6, 250));
    }


    /**
     * use math to compute the permutation
     *
     * 1 2 3 4
     * 1 + (permutation of (2, 3, 4))
     *      2 + (permutation of (3, 4))
     *          ...
     *      3 + (permutation of (2, 4))
     *      4 + (permutation of (2, 3))
     * 2 + (permutation of (1, 3, 4))
     * ...
     */
    public String getPermutation(int n, int k) {
        LinkedList<Integer> nums = new LinkedList<>();
        int[] fact = new int[n + 1];
        fact[0] = 1;
        int base = 1;
        for (int i = 1; i <= n; i++) {
            base *= i;
            nums.add(i);
            fact[i] = base;
        }
        k--;
        char[] result = new char[n];
        for (int i = n - 1; i >= 0; i--) {
            int index = k / fact[i];
            result[n - 1 - i] = Character.forDigit(nums.remove(index), 10);
            k -= index * fact[i];
        }
        return new String(result);
    }
    /**
     *
     */
    public String getPermutation2(int n, int k) {
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
