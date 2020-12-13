package mit_6006;

import java.util.*;

/**
 * Author:   softtwilight
 * Date:     2020/12/13 19:33
 *
 * https://leetcode.com/problems/network-delay-time/
 *
 * Dijkstra
 *
 */
public class _743_M_Network_Delay_Time {
    private static final _743_M_Network_Delay_Time instance = new _743_M_Network_Delay_Time();

    public static void main(String[] args) {
        int[][] input = {{2,1,1},{2,3,1},{3,4,1}, {1, 5, 2}, {4, 6, 3}};
        System.out.println(instance.networkDelayTime(input, 6, 2));
    }

    /**
     * 用了dijkstra的思想， 但是如果用priority queue 更新元素是很麻烦的， 索性直接用数组。
     *
     * 第一步是构建相邻数组。然后用相邻的数组去更新周围节点的时间，总是先选择用时最少的那个
     *
     * 然后在编列了所有节点之后， 最后一个节点累加的时间就是result
     *
     * 如果有节点不能到达，那么其时间将为Max, 那么返回-1
     */
    public int networkDelayTime(int[][] times, int N, int K) {
        List<List<int[]>> list = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < times.length; i++) {
            int[] time = times[i];
            list.get(time[0] - 1).add(time);
        }
        List<Integer> minHeap = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            minHeap.add(Integer.MAX_VALUE);
        }
        minHeap.set(K - 1, 0);

        int count = 0;
        int result = 0;
        while (true) {

            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int i = 0; i < N; i++) {
                if (minHeap.get(i) < min && minHeap.get(i) >= 0) {
                    min = minHeap.get(i);
                    minIndex = i;

                }
            }
            if (count == N) return result;
            if (minIndex == -1) return -1;
            count++;
            int old = minHeap.set(minIndex, -1);
            result = old;
            List<int[]> neighbors = list.get(minIndex);

            for (int i = 0; i < neighbors.size(); i++) {
                int[] neighbor = neighbors.get(i);
                if (minHeap.get(neighbor[1] - 1) > old + neighbor[2]) {
                    minHeap.set(neighbor[1] - 1, old + neighbor[2]);
                }
            }
        }
    }
}
