package binarysearch;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 *
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * Example 1:
 *
 * Input:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 3
 * Output: true
 *
 * target = 13
 * Output: false
 *
 * Author:   softtwilight
 * Date:     2020/04/29 21:35
 */
public class _74_Search_2D_Matrix {


    /**
     * 标准的二分查找，因为是m * n的矩阵，不用改变子数组变长的问题。
     * 我们可以计算出个数，然后知道下标的范围是0～length - 1；
     * 然后利用二分查找， 只需要讲mid转换为对应的二维数组下标即可。
     *
     * 0 ms	+ 38.9 MB
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int lo = 0, hi = matrix.length * matrix[0].length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int i = mid / matrix[0].length;
            int j = mid % matrix[0].length;
            int v = matrix[i][j];
            if (v == target) {
                return true;
            } else if (v < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }

        }
        return true;
    }
}
