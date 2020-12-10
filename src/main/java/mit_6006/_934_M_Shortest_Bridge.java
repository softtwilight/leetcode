package mit_6006;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * Author:   softtwilight
 * Date:     2020/12/10 22:38
 *
 * BFS OR DFS search
 *
 * https://leetcode.com/problems/shortest-bridge/
 */
public class _934_M_Shortest_Bridge {
    private static final _934_M_Shortest_Bridge instance = new _934_M_Shortest_Bridge();

    public static void main(String[] args) {
        int[][] input = {{1,1,1,1,1},{1,0,0,0,1},{1,0,1,0,1},{1,0,0,0,1},{1,1,1,1,1}};
//        int[][] input = {{0,1},{1,0}};

        System.out.println(instance.shortestBridge(input));
    }

    /**
     * use the dfs to mark the first island to 2.
     * use the bfs to expand the first island until reach the 1;
     * the already #loop of bfs is the result.
     */
    public int shortestBridge(int[][] A) {
        int n = A.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 1) {
                    Queue<int[]> queue = new ArrayDeque<>();
                    dfs(A, i, j, queue);
                    return bfs(A, queue);
                }
            }
        }
        return -1;
    }

    private int bfs(int[][] a, Queue<int[]> queue) {
        int result = 0;
        while (queue.size() != 0) {
            int n = queue.size();
            for (int k = 0; k < n; k++) {
                int[] point = queue.poll();
                int i = point[0];
                int j = point[1];
                if (i >= 1)
                    if (check(a, i - 1, j, queue)) return result;
                if (i < a.length - 1)
                    if (check(a, i + 1, j, queue)) return result;
                if (j >= 1)
                    if (check(a, i, j - 1, queue)) return result;
                if (j < a.length - 1)
                    if (check(a, i, j + 1, queue)) return result;
            }
            result++;
        }
        return result;
    }

    private boolean check(int[][] a, int i, int j, Queue<int[]> queue) {
        if (a[i][j] == 1) {
            return true;
        }
        if (a[i][j] == 0) {
            a[i][j] = 2;
            queue.add(new int[] {i, j});
        }
        return false;
    }

    private void dfs(int[][] a, int i, int j, Queue<int[]> queue) {
        if (i < 0 || j < 0 || i >= a.length || j >= a.length) return;
        if (a[i][j] != 1) {
            return;
        }
        a[i][j] = 2;
        queue.add(new int[]{i, j});
        dfs(a, i - 1, j, queue);
        dfs(a, i + 1, j, queue);
        dfs(a, i, j - 1, queue);
        dfs(a, i, j + 1, queue);
    }


}
