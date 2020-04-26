package map;

import java.sql.SQLOutput;
import java.util.Stack;

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
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        return helper2(matrix);
    }



    /**
     * 这个解法是基于做过的_84在直方图中找最大的矩形。
     * 我们读一行，然后0对应高度0， 1对应高度1， 计算最大矩形。
     * 然后接着读下一行，如果为0， 那么高度重置为0， 如果为1， 那么高度在原来的基础上加一
     * 然后每一行计算maxRectangle.  	7 ms  +	42.5 MB
     *
     */
    public int helper2(char[][] matrix) {
        int rn = matrix.length;
        int coln = matrix[0].length;

        int[] height = new int[coln];
        for (int i = 0; i < coln; i++) {
            if (matrix[0][i] == '0') {
                height[i] = 0;
            }  else height[i] = 1;
        }
        int result = largestInLine(height);
        for (int i = 1; i < rn; i++) {
            for (int j = 0; j < coln; j++) {
                if (matrix[i][j] == '0') {
                    height[j] = 0;
                } else height[j] = height[j] + 1;
            }
            result = Math.max(result, largestInLine(height));
        }
        return result;
    }

    /**
     * 参考_84_Largest_Rectangle, 计算height里最大的长方形。
     */
    public int largestInLine(int[] height) {
        if(height == null || height.length == 0) return 0;
        int len = height.length;
        Stack<Integer> s = new Stack<Integer>();
        int maxArea = 0;
        for(int i = 0; i <= len; i++){
            int h = (i == len ? 0 : height[i]);
            if(s.isEmpty() || h >= height[s.peek()]){
                s.push(i);
            }else{
                int tp = s.pop();
                // 长方形的面积在于，找到两个index，1是左边第一个比tp下小的index，2是右边第一个比tp小的index
                // width = （rightIndex - leftIndex + 1);
                maxArea = Math.max(maxArea, height[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
                i--;
            }
        }
        return maxArea;
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
