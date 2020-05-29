package bfs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * https://leetcode.com/problems/number-of-islands/
 *
 * Author:   softtwilight
 * Date:     2020/05/29 22:50
 */
public class _200_M_Number_of_Islands {
    private static final _200_M_Number_of_Islands instance = new _200_M_Number_of_Islands();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 4 ms	42.3 MB
     * 利用BFS的方式实现， 任意一个点， 可以往4个方向搜索， 如果是1， 放入队列
     * 然后再下次进行搜索。
     *
     * 有个可以优化的地方是不需要用visited来标记，直接将值设为0就可以了。
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int result = 0;
        int n = grid.length, m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '0' || visited[i][j]) {
                    continue;
                }
                queue.offer(new int[]{i, j});
                visited[i][j] = true;
                while (!queue.isEmpty()) {
                    int size = queue.size();
                    for (int k = 0; k < size; k++) {
                        int[] point = queue.poll();
                        int row = point[0], col = point[1];
                        if (row + 1 < n && grid[row + 1][col] == '1' && !visited[row+1][col]) {
                            visited[row + 1][col] = true;
                            queue.offer(new int[] {row + 1, col});
                        }
                        if (col + 1 < m && grid[row][col + 1] == '1' && !visited[row][col + 1]) {
                            visited[row][col + 1] = true;
                            queue.offer(new int[] {row, col + 1});
                        }
                        if ( 0 <= row - 1 && grid[row - 1][col] == '1' && !visited[row - 1][col]) {
                            visited[row - 1][col] = true;
                            queue.offer(new int[] {row - 1, col});
                        }
                        if (0 <= col - 1 && grid[row][col - 1] == '1' && !visited[row][col - 1]) {
                            visited[row][col - 1] = true;
                            queue.offer(new int[] {row, col - 1});
                        }
                    }
                }
                result++;
            }
        }
        return result;
    }


    /**
     * 1 ms	42.1 MB
     * DFS  思路差不多， 遇到1的就用dfs visit 与他连接的点， 都标记为'0'
     * 代码少很多。
     */
    public int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int result = 0;
        int n = grid.length, m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    result++;
                }
            }
        }
        return result;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0
                || i >= grid.length || j >= grid[0].length
                || grid[i][j] == '0') return;
        grid[i][j] = '0';
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }
}
