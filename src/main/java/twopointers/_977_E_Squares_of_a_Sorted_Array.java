package twopointers;

/**
 * https://leetcode.com/problems/squares-of-a-sorted-array/
 *
 * Author:   softtwilight
 * Date:     2020/05/18 22:11
 */
public class _977_E_Squares_of_a_Sorted_Array {
    private static final _977_E_Squares_of_a_Sorted_Array instance = new _977_E_Squares_of_a_Sorted_Array();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 1 ms	41.2 MB
     * 双指针没什么好说的
     */
    public int[] sortedSquares(int[] A) {
        int[] result = new int[A.length];
        int lo = 0, hi = A.length - 1, index = A.length - 1;
        while (lo <= hi) {
            if (A[lo] * A[lo] >= A[hi] * A[hi]) {
                result[index--] = A[lo] * A[lo];
                lo++;
            } else {
                result[index--] = A[hi] * A[hi];
                hi--;
            }
        }
        return result;
    }
}
