package mit_6006;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author:   softtwilight
 * Date:     2020/12/13 20:43
 *
 * https://leetcode.com/problems/path-with-maximum-probability/
 */
public class _1514_M_Path_with_Maximum_Probability {
    private static final _1514_M_Path_with_Maximum_Probability instance = new _1514_M_Path_with_Maximum_Probability();

    public static void main(String[] args) {

        int[][] edges = {{0, 1}};
        double[] succProb = {0.5, 0.5, 0.3};
        System.out.println(instance.maxProbability(3, edges, succProb, 0, 2));
    }

    /**
     * 这道题条件里的n很大， 接近edge的数， 所以用dijkstra遍历找最值很不划算， 最优解的答案是直接循环更新的。
     */
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {

        double[] nodes = new double[n];
        Arrays.fill(nodes, 0);
        nodes[start] = 1;
        List<List<double[]>> neighbors = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            neighbors.add(new ArrayList<>());
        }





        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            double pro = succProb[i];
            neighbors.get(edge[0]).add(new double[]{edge[1], pro});
            neighbors.get(edge[1]).add(new double[]{edge[0], pro});
        }


        while (true) {
            double max = 0;
            int index = -1;
            for (int i = 0; i < n; i++) {
                if (nodes[i] > max && nodes[i] != 2) {
                    max = nodes[i];
                    index = i;
                }
            }

            if (index == -1) return 0;
            List<double[]> neighbor = neighbors.get(index);
            double cur = nodes[index];
            if (index == end) return cur;
            for (double[] ne : neighbor) {
                int next = (int) ne[0];
                if (cur * ne[1] > nodes[next]) {
                    nodes[next] = cur * ne[1];
                }
            }
            nodes[index] = 2; // mark as reached.
        }
    }

    public double maxProbability2(int n, int[][] edges, double[] succProb, int start, int end) {
        double[] distance=new double[n];
        distance[start]=1.0d;

        for (int i=0;i<n-1;i++) {
            // 陷阱。。。注意，实际这是Bellmanfor的一个优化。。。提前结束循环
            boolean change=false;

            for (int j=0;j<edges.length;j++) {
                int[] edge=edges[j];
                if (distance[edge[1]]*succProb[j]>distance[edge[0]]) {
                    change=true;
                    distance[edge[0]]=distance[edge[1]]*succProb[j];
                }
                if (distance[edge[0]]*succProb[j]>distance[edge[1]]) {
                    change=true;
                    distance[edge[1]]=distance[edge[0]]*succProb[j];
                }
            }
            // 陷阱。。。注意，实际这是Bellmanfor的一个优化。。。提前结束循环
            if (!change) break;
        }
        return distance[end];
    }


}
