package map;

/**
 *
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle
 * containing only 1's and return its area.
 *
 * Example:
 *
 * Input:
 * [
 *   ["1","0","1","0","0"],
 *   ["1","0","1","1","1"],
 *   ["1","1","1","1","1"],
 *   ["1","0","0","1","0"]
 * ]
 * Output: 6
 *
 * Author:   softtwilight
 * Date:     2020/04/24 22:45
 */
public class _85_Maximal_Rectangle {

    public static void main(String[] args) {
        _85_Maximal_Rectangle instance = new _85_Maximal_Rectangle();
        char[][] input = {
                {'1','0','1','1','0','1'},
                {'1','1','1','1','1','1'},
                {'0','1','1','0','1','1'},
                {'1','1','1','0','1','0'},
                {'0','1','1','1','1','1'},
                {'1','1','0','1','1','1'}};
        System.out.println(instance.maximalRectangle(input));
    }

    /**
     * 用了最暴力的解法算的，103 ms	+ 43.4 MB
     * 对每一个点遍历，如果是1，那么就一直往下找，直到遇到0。
     * 然后接着往右边移动，往下找的时候，传递两个column坐标，1开始的坐标，1结束的坐标。
     * 这样就可以保证遍历所以情况了。
     *
     * 这个过程会有很多重复的搜索。明天再优化吧；
     *
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        return helper(matrix);
    }

    public int helper(char[][] matrix) {
        int result = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int hij = j;
                int loj = j;
                while (hij < matrix[0].length) {
                    if (matrix[i][hij] == '0') {
                        hij++;
                        loj = hij;
                    } else {
                        int temp = lookDown(matrix, i, loj, hij);
                        if (temp > result) {
                            result = temp;
//                            j = hij;
                        }
                        hij++;
                    }
                }
            }
        }
        return result;
    }

    private int lookDown(char[][] matrix, int r, int loj, int hij) {
        int result = hij - loj + 1;
        for (int i = r + 1; i < matrix.length; i++) {
            for (int j = loj; j <= hij; j++) {
                if (matrix[i][j] == '0') {
                    return result;
                }
            }
            result += hij - loj + 1;
        }
        return result;
    }


}
