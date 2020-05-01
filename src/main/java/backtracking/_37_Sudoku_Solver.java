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
        instance.solveSudoku3(input);
        System.out.println(Arrays.deepToString(input));
    }

    private Map<Integer, Integer> rowMap = new HashMap<>();
    private Map<Integer, Integer> colMap = new HashMap<>();
    private Map<Integer, Integer> blockMap = new HashMap<>();


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
     *  不用copy，而是让map重新设置值的方式实现了一版  14 ms + 40.1 MB
     *
     *  利用set实现一版本不知道会怎样？
     *  实现了一版，28 ms	+ 39.5 MB, 更慢了， 这里取并集的时间超过了循环判断isValid的时间。
     *
     *  所以在这个思路里，三种方法里，用二进制来判断的解法是最快的。但是并不明显。
     *  核心的思想是尽量从可选值更少的地方开始试错。
     *
     *
     */
    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            rowMap.put(i, 0);
            colMap.put(i, 0);
            blockMap.put(i, 0);
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
                    int bi = (r / 3) * 3 + col / 3;
                    int i = rowMap.get(r);
                    int j = colMap.get(col);
                    int k = blockMap.get(bi);
                    if ((i | j | k) >= 511) return false;
                    int sum = (i | j | k) ^ 511;
                    List<Integer> li = new ArrayList<>(3);
                    for (int x = 0; x < 9; x++) {
                        int temp = sum;
                        boolean has = ((temp >> x) & 1) == 1;
                        if (has) li.add(x);
                        if (li.size() == 3) break;
                    }

                    if (li.size() > 0 && li.size() <= 2) {
                        for (int candidate : li) {
                            board[r][col] = (char) (candidate + 49);
                            int s = 1 << candidate;
                            boolean notInr = (rowMap.get(r) & s) == 0;
                            boolean notInc = (colMap.get(col) & s) == 0;
                            boolean notInb = (blockMap.get(bi) & s) == 0;
                            if (notInr) rowMap.put(r, rowMap.get(r) + s);
                            if (notInc) colMap.put(col, colMap.get(col) + s);
                            if (notInb) blockMap.put(bi, blockMap.get(bi) + s);
                            if (solveWithMap(board)) {
                                return true;
                            }
                            board[r][col] = '.';
                            if (notInr) rowMap.put(r, rowMap.get(r) - s);
                            if (notInc) colMap.put(col, colMap.get(col) - s);
                            if (notInb) blockMap.put(bi, blockMap.get(bi) - s);
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
    private boolean solveSudoku2(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char k = '1'; k <= '9'; k++) {
                        if (isValid(board, i, j, k)) {
                            board[i][j] = k;
                            if (solveSudoku2(board)) {
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

    private Map<Integer, Set<Character>> rMap = new HashMap<>();
    private Map<Integer, Set<Character>> cMap = new HashMap<>();
    private Map<Integer, Set<Character>> bMap = new HashMap<>();

    public void solveSudoku3(char[][] board) {
        Set<Character> s = new HashSet<>();
        for (char i = '1'; i <= '9'; i++) {
            s.add(i);
        }
        for (int i = 0; i < 9; i++) {
            rMap.put(i, new HashSet<>(s));
            cMap.put(i, new HashSet<>(s));
            bMap.put(i, new HashSet<>(s));
        }
        for (int r = 0; r < 9; r++) {
            for (int col = 0; col < 9; col++) {
                char c = board[r][col];
                if (c != '.') {
                    rMap.get(r).remove(c);
                    cMap.get(col).remove(c);
                    int blockIndex = (r / 3) * 3 + col / 3;
                    bMap.get(blockIndex).remove(c);
                }
            }
        }
        solveWithMap2(board);
    }

    private boolean solveWithMap2(char[][] board) {
        for (int r = 0; r < 9; r++) {
            for (int col = 0; col < 9; col++) {
                char c = board[r][col];
                if (c == '.') {
                    int bi = (r / 3) * 3 + col / 3;
                    Set<Character> rs = rMap.get(r);
                    Set<Character> cs = cMap.get(col);
                    Set<Character> bs = bMap.get(bi);

                    Set<Character> inner = new HashSet<>(rs);
                    inner.retainAll(cs);
                    inner.retainAll(bs);
                    if (inner.isEmpty()) return false;

                    if (inner.size() < 3) {
                        for (char candidate : inner) {
                            board[r][col] = candidate;
                            boolean a = rs.contains(candidate);
                            boolean b = cs.contains(candidate);
                            boolean bf = bs.contains(candidate);
                            if (a) rs.remove(candidate);
                            if (b) cs.remove(candidate);
                            if (bf) bs.remove(candidate);
                            if (solveWithMap2(board)) {
                                return true;
                            }
                            board[r][col] = '.';
                            if (a) rs.add(candidate);
                            if (b) cs.add(candidate);
                            if (bf) bs.add(candidate);
                        }
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
