package dp;

import java.util.HashSet;
import java.util.Set;

/**
 * Author:   softtwilight
 * Date:     2021/01/11 22:58
 *
 * https://leetcode.com/problems/length-of-longest-fibonacci-subsequence/
 */
public class _873_M_Length_of_Longest_Fibonacci_Subsequence {
    private static final _873_M_Length_of_Longest_Fibonacci_Subsequence instance = new _873_M_Length_of_Longest_Fibonacci_Subsequence();

    public static void main(String[] args) {
        int[] arr = {1,3,7,11,12,14,18};
        System.out.println(instance.lenLongestFibSubseq(arr));
    }



    /**
     * 子问题是变成了在一个有序数组中求 一个pair的sum和为n。
     */
    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        int max = 0;
        int[][] dp = new int[n][n];
        for (int i = 2; i < n; i++) {
            int l = 0, r = i - 1;
            while (l < r) {
                int sum = arr[l] + arr[r];
                if (sum > arr[i]) {
                    r--;
                } else if (sum < arr[i]) {
                    l++;
                } else {
                    dp[r][i] = dp[l][r] + 1;
                    max = Math.max(max, dp[r][i]);
                    r--;
                    l++;
                }
            }
        }
        return max == 0 ? 0 : max + 2;
    }

    /**
     * 这个解法也很有意思
     */
    public int lenLongestFibSubseq3(int[] A) {
        int N = A.length;
        Set<Integer> S = new HashSet();
        for (int x: A) S.add(x);

        int ans = 0;
        for (int i = 0; i < N; ++i)
            for (int j = i+1; j < N; ++j) {
                /* With the starting pair (A[i], A[j]),
                 * y represents the future expected value in
                 * the fibonacci subsequence, and x represents
                 * the most current value found. */
                int x = A[j], y = A[i] + A[j];
                int length = 2;
                while (S.contains(y)) {
                    // x, y -> y, x+y
                    int tmp = y;
                    y += x;
                    x = tmp;
                    ans = Math.max(ans, ++length);
                }
            }

        return ans >= 3 ? ans : 0;
    }

    /**
     *
     */
    public int lenLongestFibSubseq2(int[] arr) {

        int[][] memo = new int[arr.length][arr.length];
        int result = 0;
        int maxInArray = arr[arr.length - 1];
        for (int i = 0; i < arr.length - 2; i++) {
            int max = 0;
            for (int j = i + 1; j < arr.length - 1; j++) {
                if (arr[i] + arr[j] > maxInArray) break;
                max = Math.max(nums(i, j, arr, memo), max);
            }
            result = Math.max(result, max);
        }
        return result == 0 ? 0 : result + 2;

    }

    private int nums(int i, int j, int[] arr, int[][] memo) {
        if (memo[i][j] != 0) return memo[i][j] - 1;
        int maxInArray = arr[arr.length - 1];
        if (arr[i] + arr[j] > maxInArray) {
            return 0;
        }
        int result = 0;
        for (int k = j + 1; k < arr.length; k++) {

            if (arr[k] > arr[i] + arr[j]) break;
            if (arr[k] == arr[i] + arr[j]) {
                result = 1 + nums(j, k, arr, memo);
                break;
            }
        }
        memo[i][j] = result + 1;
        return result;
    }

}
