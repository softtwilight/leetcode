package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author:   softtwilight
 * Date:     2021/01/07 22:06
 */
public class _51_H_NQueens {
    private static final _51_H_NQueens instance = new _51_H_NQueens();

    public static void main(String[] args) {
        System.out.println(instance.solveNQueens(9).size());
    }

    /**
     *
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        boolean[][] memo = new boolean[n][n];
        helper(result, memo, 0);
        return result;
    }

    private void helper(List<List<String>> result, boolean[][] memo, int row) {
        if (row == memo.length) {
            List<String> solution = new ArrayList<>();
            for (boolean[] rows : memo) {
                String rowStr = "";
                for (boolean cell : rows) {
                    rowStr += cell ? "Q" : ".";
                }
                solution.add(rowStr);
            }
            result.add(solution);
            return;
        }
        for (int i = 0; i < memo.length; i++) {
            if (isNotLegal(memo, row, i)) {
                continue;
            }
            memo[row][i] = true;
            helper(result, memo, row + 1);
            memo[row][i] = false;
        }
    }

    private boolean isNotLegal(boolean[][] memo, int row, int i) {
        int left = i - 1, right = i + 1;
        for (int k = row - 1; k >= 0; k--, left--, right++) {
            if(memo[k][i]) return true;
            if (left >= 0 && memo[k][left]) {
                return true;
            }
            if (right < memo.length && memo[k][right]) {
                return true;
            }
        }
        return false;
    }
}
