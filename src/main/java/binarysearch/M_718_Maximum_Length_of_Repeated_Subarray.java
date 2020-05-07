package binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.
 *
 * Example 1:
 *
 * Input:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * Output: 3
 * Explanation:
 * The repeated subarray with maximum length is [3, 2, 1].
 *
 *
 * Note:
 *
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 *
 * Author:   softtwilight
 * Date:     2020/05/07 21:55
 */
public class M_718_Maximum_Length_of_Repeated_Subarray {
    static M_718_Maximum_Length_of_Repeated_Subarray instance = new M_718_Maximum_Length_of_Repeated_Subarray();

    public static void main(String[] args) {
        int[] A = {1,2,3,2,1};
        int[] B = {3,2,1,4,7,8, 2, 3, 2, 1};
        System.out.println(instance.findLength(A, B));
    }


    /**
     *　动态规划的解法。
     *  最长重复子串假设分别从i，j开始， i和j开始的的数组就具有最长的共同前缀。
     *  状态的含义是：
     *  memo[i][j]， A[i:] 和 B[j:]的最长共同前缀
     *  我们假设i, j 有A[i] = B[j]，那么memo[i][j] = memo[i+1][j+1] + 1;
     *
     *  这样我们bottom-up构建memo， 返回memo中的max值。
     *  51 ms  +  48.5 MB
     */
    public int findLength2(int[] A, int[] B) {
        int ans = 0;
        int[][] memo = new int[A.length + 1][B.length + 1];
        for (int i = A.length - 1; i >= 0; --i) {
            for (int j = B.length - 1; j >= 0; --j) {
                if (A[i] == B[j]) {
                    memo[i][j] = memo[i+1][j+1] + 1;
                    if (ans < memo[i][j]) ans = memo[i][j];
                }
            }
        }
        return ans;
    }

    /**
     * 2564 ms + 39.8 MB
     * naive one
     */
    public int findLength(int[] A, int[] B) {
        if (A.length == 0 || B.length == 0) {
            return 0;
        }
        if (A.length > B.length) {
            return findLength(B, A);
        }
        List[] bPositions = new List[100];
        for (int i = 0; i < B.length; i++) {
            int p = B[i];
            if (bPositions[p] == null) {
                bPositions[p] = new ArrayList();
            }
            bPositions[p].add(i);
        }

        int result = 0;
        for (int i = 0; i < A.length; i++) {
            if (bPositions[A[i]] != null) {
                List bs = bPositions[A[i]];
                for (Object a : bs) {
                    int count = 1;
                    int j = (int) a + 1;
                    int k = i + 1;
                    while (k < A.length && j < B.length && A[k++] == B[j++]) {
                        count++;
                    }
                    result = Math.max(result, count);
                }
            }
        }
        return result;
    }
}
