package hashmap;

import java.sql.SQLOutput;
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
public class _37_Sudoku_Solver {

    public static void main(String[] args) {
        _37_Sudoku_Solver instance = new _37_Sudoku_Solver();

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

    private Map<Integer, Integer> rowMap = new HashMap<>();
    private Map<Integer, Integer> colMap = new HashMap<>();
    private Map<Integer, Integer> blockMap = new HashMap<>();
    private Map<Integer, Integer> bitMap = new HashMap<>();


    /**
     *  做了一个多小时，只完成了能通过部分case的解。
     *  先说一下思路，我们用三个map来保存行, 列, 块的拥有数字的情况。
     *  在某行，某列，或者某块拥有的数字用二进制表示。比如135表示为000，010，101。
     *  然后我们遍历数组，通过map取该坐标的三个值，然后取或， 然后和111，111，111取异或
     *  得到的数是在该位置可以选择的数，比如说000，000，100，表示在该位置只有一个可选:3。
     *  然后我们的解就是找只有一种可能性的解， 填入数组， 更新map， 然后接着重新遍历。
     *  这种解法只能对应比较简单的数独，当数独中每一个格子都不存在唯一可能，那么就解不出来。
     *
     *  要解决这个问题，可能的思路是，找出可能性最少的点，比如说有两个可能性，然后试其中一种可能，
     *  需要引入冲突检测机制，同时为了能回退回另一种可能，board 和 map能不能被修改，需要copy一份给尝试算法。
     *  艰难的完成了一版  17 ms  +	39.1 MB
     *
     *
     */
    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            rowMap.put(i, 0);
            colMap.put(i, 0);
            blockMap.put(i, 0);
//            bitMap.put(1 << i, i);
        }

        for (int r = 0; r < 9; r++) {
            for (int col = 0; col < 9; col++) {
                if (board[r][col] != '.') {
                    int n = 1 << ((int)board[r][col] - 49);
                    rowMap.put(r, rowMap.get(r) + n);
                    colMap.put(col, colMap.get(col) + n);
                    int blockIndex = (r / 3) * 3 + col / 3;
                    blockMap.put(blockIndex, blockMap.get(blockIndex) + n);
                }
            }
        }

        solveWithMap(board);

    }

    private boolean solveWithMap(char[][] board) {
        for (int r = 0; r < 9; r++) {
            for (int col = 0; col < 9; col++) {
                if (board[r][col] == '.') {
                    int blockIndex = (r / 3) * 3 + col / 3;
                    int i = rowMap.get(r);
                    int j = colMap.get(col);
                    int k = blockMap.get(blockIndex);
                    int sum = (i | j | k) ^ 511 ;
                    List<Integer> li = new ArrayList<>(9);
                    for (int x = 0; x < 9; x++) {
                        int temp = sum;
                        boolean has = ((temp >> x) & 1) == 1;
                        if (has) {
                            li.add(x);
                        }
                    }

                    if (li.size() > 0) {
                        System.out.println(li);
                        for (int candidate : li) {
                            char c = (char) (candidate + 49);
                            if (!isValid(board, r, col, c)) {
                                continue;
                            }
                            board[r][col] = (char) (candidate + 49);
//                            rowMap.put(r, rowMap.get(r) + 1 << candidate);
//                            colMap.put(col, colMap.get(col) + (1 << candidate));
//                            blockMap.put(blockIndex, blockMap.get(blockIndex) + (1 << candidate));
                            if (solveWithMap(board)) {
                                return true;
                            }
                            board[r][col] = '.';
//                            rowMap.put(r, rowMap.get(r) - 1 << candidate);
//                            colMap.put(col, colMap.get(col) - (1 << candidate));
//                            blockMap.put(blockIndex, blockMap.get(blockIndex) - (1 << candidate));

                        }
                        return false;
                    }
                }
            }
        }
        return true;
    }


    /**
     * 这个是简单暴力的解法。21 ms + 36.8 MB
     * 不使用map，直接在board上验证合法与否。
     *
     */
    private boolean solve(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char k = '1'; k <= '9'; k++) {
                        if (isValid(board, i, j, k)) {
                            board[i][j] = k;
                            if (solve(board)) {
                                return true;
                            }
                            board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int i, int j, char k) {

        for (int n = 0; n < 9; n++) {
            boolean a = board[i][n] == k;
            boolean b = board[n][j] == k;
            boolean c = board[(i / 3) * 3 + n / 3][(j / 3) * 3 + n % 3] == k;
            if (a || b || c) {
                return false;
            }
        }
        return true;
    }

}
