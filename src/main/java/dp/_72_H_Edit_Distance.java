package dp;

/**
 * Author:   softtwilight
 * Date:     2020/12/20 21:50
 *
 * https://leetcode.com/problems/edit-distance/
 */
public class _72_H_Edit_Distance {
    private static final _72_H_Edit_Distance instance = new _72_H_Edit_Distance();

    public static void main(String[] args) {
        String word1 = "sea", word2 = "eat";
        System.out.println(instance.minDistance(word1, word2));
    }

    /**
     * recursive approach
     *
     * every recursive at least one of i and j will increase, add that make the recursive will end.
     * eg. not cyclic depend on sub problems.
     *
     * 诡异的是和最快的方法比几乎一模一样， 除了recursive 的坐标顺序， 从前还是从后开始。
     * 但是慢了5 ms
     */
    public int minDistance(String word1, String word2) {

        m = word1.length();
        n = word2.length();
        int[][] memo = new int[m][n];
        return helper(0, 0, word1, word2, memo);
    }

    private int helper(int i, int j, String word1, String word2, int[][] memo) {
        if (i == m) return n - j;
        if (j == n) return m - i;
        if (memo[i][j] != 0) return memo[i][j];
        if (word1.charAt(i) == word2.charAt(j)) return helper(i + 1, j + 1, word1,word2, memo);

        int change = helper(i + 1, j + 1, word1, word2, memo);
        int add = helper(i, j + 1, word1, word2, memo);
        int delete = helper(i + 1, j, word1, word2, memo);
        int result = Math.min(delete, Math.min(change, add)) + 1;
        memo[i][j] = result;
        return result;
    }

    int m;
    int n;

}
