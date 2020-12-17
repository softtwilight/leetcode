package mit_6006;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author:   softtwilight
 * Date:     2020/12/17 20:00
 *
 * https://leetcode.com/problems/course-schedule/
 *
 * topological-sort
 */
public class _207_M_Course_Schedule {
    private static final _207_M_Course_Schedule instance = new _207_M_Course_Schedule();

    public static void main(String[] args) {
        int[][] input = {{0,1},{3,1},{1,3},{3,2}};
        System.out.println(instance.canFinish(4, input));
    }

    /**
     * 这道题其实是cycle detect， 用boolean值有个缺陷，有个遍历节点还会在遍历一遍
     * 看到有用int 作为数组， 然后标记0， 1， 2 三种状态，然后visiting 标记为1， visited标记为2
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        //init
        List<List<Integer>> adjacency = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        for(int[] edge : prerequisites) {
            adjacency.get(edge[1]).add(edge[0]);
        }

        boolean[] traveled = new boolean[numCourses];
        for (int i = 0; i < numCourses ; i++) {

            if (!dfs2(i, adjacency, traveled)) {
                return false;
            }
        }
        return true;

    }

    private boolean dfs2(int n, List<List<Integer>> adjacency,  boolean[] traveled) {
        if (traveled[n] == true) return false;
        traveled[n] = true;
        for (int i : adjacency.get(n)) {
            if (!dfs2(i, adjacency, traveled)) {
                return false;
            }
        }
        traveled[n] = false;
        return true;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //init
        List<List<Integer>> adjacency = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        for(int[] edge : prerequisites) {
            adjacency.get(edge[1]).add(edge[0]);
        }

        int[] traveled = new int[numCourses];
        for (int i = 0; i < numCourses ; i++) {

            if (!dfs(i, adjacency, traveled)) {
                return false;
            }
        }
        return true;

    }

    private boolean dfs(int n, List<List<Integer>> adjacency,  int[] traveled) {
        if (traveled[n] == 1) return false;
        traveled[n] = 1;
        for (int i : adjacency.get(n)) {
            if (traveled[i] != 2) {
                if (!dfs(i, adjacency, traveled)) {
                    return false;
                }
            }
        }
        traveled[n] = 2;
        return true;
    }
}
