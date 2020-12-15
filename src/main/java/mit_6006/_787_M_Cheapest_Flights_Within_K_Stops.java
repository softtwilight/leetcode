package mit_6006;

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
        int[][] input = {{0,1,100},{1,2,100},{0,2,500}};
        System.out.println(instance.findCheapestPrice(3, input, 0, 2, 0));
    }

    /**
     * dijkstra
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
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
                    pq.add(new int[] {price + adj.get(a), a, stops - 1});
                }
            }
        }
        return -1;
    }
}
