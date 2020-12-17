package mit_6006;

import com.sun.source.tree.DirectiveTree;
import org.w3c.dom.ls.LSOutput;

import java.util.*;

/**
 * Author:   softtwilight
 * Date:     2020/12/15 21:53
 *
 * https://leetcode.com/problems/cheapest-flights-within-k-stops/
 * dijkstra
 */
public class _787_M_Cheapest_Flights_Within_K_Stops {
    private static final _787_M_Cheapest_Flights_Within_K_Stops instance = new _787_M_Cheapest_Flights_Within_K_Stops();

    public static void main(String[] args) {
//        int[][] input = {{0,1,1},{0,2,5},{1,2,1}, {2, 3, 1}};
        int[][] input = {{0,3,7},{4,5,3},{6,4,8},{2,0,10},{6,5,6},{1,2,2},{2,5,9},{2,6,8},{3,6,3},{4,0,10},{4,6,8},{5,2,6},{1,4,3},{4,1,6},{0,5,10},{3,1,5},{4,3,1},{5,4,10},{0,1,6}}
                ;
        System.out.println(instance.findCheapestPrice(7, input, 2, 4, 1));
    }


    /**
     * bellman-ford 修改版的解法
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        int[] price = new int[n];
        // 1000001 是根据题意选择的
        Arrays.fill(price, 1000001);
        price[src] = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= k; i++) {
            map.clear(); // 保证更新的是基于上一层的
            for (int[] edge : flights) {
                if (price[edge[1]] > price[edge[0]] + edge[2]) {

                    // 同一层可能有不同路径到达形同的dst， 选择较小的。
                    int original = map.getOrDefault(edge[1], 1000001);
                    map.put(edge[1], Math.min(original, price[edge[0]] + edge[2]));
                }
            }
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                price[entry.getKey()] = entry.getValue();
            }
        }
        return price[dst] == 1000001 ? -1 : price[dst];
    }
    /**
     * dijkstra的变化版本
     */
    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
        for (int[] f : flights) {
            if (!prices.containsKey(f[0])) prices.put(f[0], new HashMap<>());
            prices.get(f[0]).put(f[1], f[2]);
        }
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[] {0, src, k + 1});
        while (!pq.isEmpty()) {
            int[] top = pq.remove();
            int price = top[0];
            int city = top[1];
            int stops = top[2];
            if (city == dst) return price;
            if (stops > 0) {
                Map<Integer, Integer> adj = prices.getOrDefault(city, new HashMap<>());
                for (int a : adj.keySet()) {
                    // 这个地方会把已经跑过的节点在放入到queue里去，这个queue和dijkstra的queue的含义不太一样
                    pq.add(new int[] {price + adj.get(a), a, stops - 1});
                }
            }
        }
        return -1;
    }
}
