package mit_6006;

import java.util.Arrays;

/**
 * Author:   softtwilight
 * Date:     2020/10/23 21:46
 *
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 */
public class _240_M_Search_a_2D_Matrix {
    private static final _240_M_Search_a_2D_Matrix instance = new _240_M_Search_a_2D_Matrix();

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
        System.out.println(instance.searchMatrix(matrix, 15));
    }

    /**
     * 1. 用二分查找来找
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int lo = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int hi = m - 1;
        while (lo <= hi) {
            int mid = (hi - lo) / 2 + lo;
            if (matrix[mid][n - 1] == target) return true;
            if (matrix[mid][n - 1] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        if (lo == m) return false;
        for (int i = lo; i < m; i++) {
            int re = Arrays.binarySearch(matrix[i], 0, n, target);
            if (re >= 0) return true;
        }
        return false;
    }

    /**
     * We start search the matrix from top right corner, initialize the current position to top right corner,
     * if the target is greater than the value in current position,
     * then the target can not be in entire row of current position because the row is sorted,
     * if the target is less than the value in current position,
     * then the target can not in the entire column because the column is sorted too.
     * We can rule out one row or one column each time, so the time complexity is O(m+n).
     *
     * 有点向树，比target小了往右边走， 比target大了往左边走。
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;

        int n = matrix.length, m = matrix[0].length;
        int i = 0, j = m - 1;

        while (i < n && j >= 0) {
            int num = matrix[i][j];

            if (num == target)
                return true;
            else if (num > target)
                j--;
            else
                i++;
        }

        return false;
    }


//    public boolean searchMatrix(int[][] matrix, int target) {
//        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
//            return false;
//        }
//
//        int m = matrix.length;
//        int n = matrix[0].length;
//        if (m < n) {
//            if (matrix[m - 1][m - 1] < target) {
//                return searchMatrix(transverse(matrix), target);
//            }
//        }
//
//        int hi = Math.min(m, n) - 1;
//        int lo = 0;
//        while (lo < hi) {
//            int mid = (hi - lo) / 2 + lo;
//            if (matrix[mid][mid] > target) {
//                hi = mid;
//            } else if (matrix[mid][mid] < target) {
//                lo = mid + 1;
//            } else {
//                return true;
//            }
//        }
//        if (matrix[lo][lo] == target) return true;
//
//        // 不在对角线上， 对多出来的二分查找
//        if (matrix[lo][lo] < target) {
//            if (m == n) return false;
//            int[][] newMax = new int[m - lo - 1][n];
//            for (int i = 0; i < newMax.length; i++) {
//                newMax[i] = matrix[i + lo + 1];
//            }
//            return searchMatrix(newMax, target);
//        }
//
//
//        int re = Arrays.binarySearch(matrix[hi], 0, lo, target);
//        if (re >= 0) return true;
//        int[] cols = new int[hi + 1];
//        for (int i = 0; i <= hi; i++) {
//            cols[i] = matrix[i][lo];
//        }
//        return Arrays.binarySearch(cols, 0, hi, target) >= 0;
//
//    }

    private int[][] transverse(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] newMatrix = new int[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                newMatrix[j][i] = matrix[i][j];
            }
        }
        return newMatrix;
    }
}
