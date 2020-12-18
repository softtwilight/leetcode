package mit_6006;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author:   softtwilight
 * Date:     2020/12/17 21:13
 *
 * https://leetcode.com/problems/course-schedule-ii/
 */
public class _210_M_Course_Schedule_II {
    private static final _210_M_Course_Schedule_II instance = new _210_M_Course_Schedule_II();

    public static void main(String[] args) {
        int[][] input = {{1, 2}, {2, 3}, {3, 4}, {0, 4}, {1, 0}};
        System.out.println(Arrays.toString(instance.findOrder(5, input)));
    }

    /**
     * Topological Sort By DFS
     *
     * There may have bug, we start the dfs at vertex 0, but vertex may depend on another vertex
     *
     * So I think we should start with the vertex which has no predecessor.
     *
     * But when I add the predecessorMark, I miss the cycle check.
     *
     * So when i come back to see the algorithm, I find that the order to begin dfs isn't matter
     *
     * If we start the no predecessor vertex, it's fine.
     * otherwise we start the middle vertex, we transverse the final list,
     * so then predecessor will ensure have a lower instance to the beginning vertex.
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        // init
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
//        int[] predecessorMark = new int[numCourses];
        for(int[] edge : prerequisites) {
            graph.get(edge[1]).add(edge[0]);
//            predecessorMark[edge[0]] = 1;
        }

        // mark the visiting vertex to 1, finish vertex to 2
        int[] mark = new int[numCourses];

        // append the vertex in the dfs finish order
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
                if (!dfs(i, graph, mark, result)) {
                    return new int[0];
                }
        }

        // reverse the list
        int[] re = new int[result.size()];
        for (int i = 0; i < re.length; i++) {
            re[i] = result.get(re.length - 1 - i);
        }
        return re;
    }

    private boolean dfs(int i, List<List<Integer>> graph, int[] mark, List<Integer> result) {
        if (mark[i] == 1) return false;
        if (mark[i] == 2) return true;
        mark[i] = 1;
        List<Integer> adj = graph.get(i);
        for (Integer v : adj)
            if (mark[v] != 2)
                if (!dfs(v, graph, mark, result)) return false;
        mark[i] = 2;
        result.add(i);
        return true;
    }


}
