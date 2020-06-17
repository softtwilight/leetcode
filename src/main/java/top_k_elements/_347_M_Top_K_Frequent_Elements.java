package top_k_elements;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/
 *
 * Author:   softtwilight
 * Date:     2020/06/17 22:35
 */
public class _347_M_Top_K_Frequent_Elements {
    private static final _347_M_Top_K_Frequent_Elements instance = new _347_M_Top_K_Frequent_Elements();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 11 ms  +	42.3 MB
     * 先用map 统计频率，然后利用堆找最高频率的k个元素
     */
    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> frequence = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int past = frequence.getOrDefault(nums[i], 0);
            frequence.put(nums[i], past + 1);
        }
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(k + 1,
                Comparator.comparingInt(a -> a[0]));
        frequence.forEach((key, val) -> {
            priorityQueue.offer(new int[] {val, key});
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        });
        int[] result = new int[k];
        int i = 0;
        while (i < k) {
            result[i] = priorityQueue.poll()[1];
            i++;
        }
        return result;
    }
}
