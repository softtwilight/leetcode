package dp;

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
     *
     */
    public int lenLongestFibSubseq(int[] arr) {

        int[][] memo = new int[arr.length][arr.length];
        int result = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            int max = 0;
            for (int j = i + 1; j < arr.length - 1; j++) {
                max = Math.max(nums(i, j, arr, memo), max);
            }
            result = Math.max(result, max);
        }
        return result == 0 ? 0 : result + 2;

    }

    private int nums(int i, int j, int[] arr, int[][] memo) {
        if (memo[i][j] != 0) return memo[i][j] - 1;

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
