package top_k_elements;

import java.util.*;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/
 *
 * Author:   softtwilight
 * Date:     2020/06/17 22:35
 */
public class _347_M_Top_K_Frequent_Elements {
    private static final _347_M_Top_K_Frequent_Elements instance = new _347_M_Top_K_Frequent_Elements();

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        System.out.println(Arrays.toString(instance.topKFrequent(nums, 2)));
    }


    Map<Integer, Integer> frequency = new HashMap<>();
    Random ra = new Random();


    /**
     * 9 ms	42.1 MB
     * 基于quick-select的版本
     * 1. 统计频率
     * 2. 构建用用quick select的数组
     * 3. quick select
     *      a. 随机选择一个pivot
     *      b. partition
     *          - swap（pivot， hi）将pivot放到最右边， 记录最左边storeIndex = lo
     *          - 遍历，将频率小于pivot频率的放在左边，increment storeIndex。
     *          - 遍历结束， swap（storeIndex， hi）， 将pivot 放置到分割好的位置
     *          - return storeIndex
     *      c. 判断分割位置与k的关系，相等则分割位置右边刚好是k个最高频率的数字
     *          小于， quickSelect  分割位置右边
     *          大于， quickSelect  分割位置左边
     * 4. 返回数组的最后k个元素。
     *
     */
    public int[] topKFrequent(int[] nums, int k) {

        for (int i = 0; i < nums.length; i++) {
            int past = frequency.getOrDefault(nums[i], 0);
            frequency.put(nums[i], past + 1);
        }
        int[] keys = new int[frequency.size()];
        int i = 0;
        for (Integer key : frequency.keySet()) {
            keys[i] = key;
            i++;
        }
        quickSelect(keys, 0, i - 1, i - k);
        return Arrays.copyOfRange(keys, i - k, i);
    }

    private void quickSelect(int[] keys, int lo, int hi, int k) {
        if (lo == hi) return;
        int pivot = lo + ra.nextInt(hi - lo + 1);
        int partitionIndex = partition(keys, lo, hi, pivot);
        if (partitionIndex == k) return;
        if (partitionIndex < k) {
            quickSelect(keys, partitionIndex + 1, hi, k);
        } else {
            quickSelect(keys, lo, partitionIndex - 1,  k);
        }
    }

    private int partition(int[] keys, int lo, int hi, int pivot) {
        int storeIndex = lo;
        swap(keys, pivot, hi);
        int pivotVal = frequency.get(keys[hi]);
        for (int i = lo; i < hi; i++) {
            if (frequency.get(keys[i]) < pivotVal) {
                swap(keys, storeIndex, i);
                storeIndex++;
            }
        }
        swap(keys, storeIndex, hi);
        return storeIndex;
    }

    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    /**
     * 11 ms  +	42.3 MB
     * 先用map 统计频率，然后利用堆找最高频率的k个元素
     */
    public int[] topKFrequent2(int[] nums, int k) {

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
