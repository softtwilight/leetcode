package backtracking;

import java.util.*;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 *
 * A sudoku solution must satisfy all of the following rules:
 *
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * Empty cells are indicated by the character '.'.
 *
 * Note:
 *
 * The given board contain only digits 1-9 and the character '.'.
 * You may assume that the given Sudoku puzzle will have a single unique solution.
 * The given board size is always 9x9.
 *
 * Author:   softtwilight
 * Date:     2020/04/22 20:18
 */
public class _37_Sudoku_Solver2 {

    public static void main(String[] args) {
        _37_Sudoku_Solver2 instance = new _37_Sudoku_Solver2();

        char[][] input =   {{'.','.','9','7','4','8','.','.','.'},
                {'7','.','.','.','.','.','.','.','.'},
                {'.','2','.','1','.','9','.','.','.'},
                {'.','.','7','.','.','.','2','4','.'},
                {'.','6','4','.','1','.','5','9','.'},
                {'.','9','8','.','.','.','3','.','.'},
                {'.','.','.','8','.','3','.','2','.'},
                {'.','.','.','.','.','.','.','.','6'},
                {'.','.','.','2','7','5','9','.','.'}};
        instance.solveSudoku(input);
        System.out.println(Arrays.deepToString(input));
    }

    /**
     * 接着延续之前的思路，用一个int来表示9个坐标的存在与否其实是多余的，
     * 我们可以直接用一个boolean数组来完成，这样少了很多移位的操作。
     * 其次就是用map来表示也是浪费的，多了hash计算，box，unbox等操作，因为其key是数字
     * 其实我们可以直接使用数组的。
     *
     * 所以试着从这两个方面来优化一下。
     * 	3 ms  +	37 MB
     *
     * 	加上带坐标的递归，减少已经重复遍历过的数
     * 	2 ms  +	36.9 MB
     */
    public void solveSudoku(char[][] board) {

        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] grids = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int bi = (i / 3) * 3 + j / 3;
                    int index = board[i][j] - '1';// 将char 转换为 数组下标
                    rows[i][index] = true;
                    cols[j][index] = true;
                    grids[bi][index] = true;
                }
            }
        }

        solveHelper(board, rows, cols, grids, 0, 0);
    }

    private boolean solveHelper(char[][] board, boolean[][] rows,
                                boolean[][] cols, boolean[][] grids, int ri, int ci) {

        if (ci == 9) {
            ci = 0;
            ri++;
        }

        for (int i = ri; i < 9; i++) {

            for (int j = ci; j < 9; j++) {
                if (board[i][j] == '.') {
                    int bi = (i / 3) * 3 + j / 3;
                    for (int k = 0; k < 9; k++) {
                        if(rows[i][k] || cols[j][k] || grids[bi][k]) {
                            continue;
                        }
                        board[i][j] = (char) ('1' + k);
                        rows[i][k] = true;
                        cols[j][k] = true;
                        grids[bi][k] = true;
                        if (solveHelper(board, rows, cols, grids, i, j + 1)) {
                            return true;
                        }
                        board[i][j] = '.';
                        rows[i][k] = false;
                        cols[j][k] = false;
                        grids[bi][k] = false;
                    }
                    return false;
                } else {
                    return solveHelper(board, rows, cols, grids, i, j + 1);
                }

            }
        }
        return true;
    }

}
